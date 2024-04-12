package thaumcraft.common.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.research.ScanResult;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.items.ItemCompassStone;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TileNode extends TileThaumcraft implements INode, IWandable {
	public static HashMap<String, ArrayList<Integer>> locations = new HashMap<String, ArrayList<Integer>>();
	public Entity drainEntity;
	public MovingObjectPosition drainCollision;
	public int drainColor = 16777215;
	public Color targetColor = new Color(16777215);
	public Color color = new Color(16777215);
	long lastActive;
	AspectList aspects = new AspectList();
	AspectList aspectsBase = new AspectList();
	int count;
	int regeneration = -1;
	int wait;
	String id;
	byte nodeLock;
	boolean catchUp;
	private NodeType nodeType = NodeType.NORMAL;
	private NodeModifier nodeModifier;

	@Override
	public String getId() {
		if (id == null) {
			id = generateId();
		}
		return id;
	}

	public String generateId() {
		id = worldObj.provider.dimensionId + ":" + xCoord + ':' + yCoord + ':' + zCoord;
		if (worldObj != null && locations != null) {
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add(Integer.valueOf(worldObj.provider.dimensionId));
			t.add(Integer.valueOf(xCoord));
			t.add(Integer.valueOf(yCoord));
			t.add(Integer.valueOf(zCoord));
			locations.put(id, t);
		}
		return id;
	}

	@Override
	public void onChunkUnload() {
		if (locations != null) {
			locations.remove(id);
		}
		super.onChunkUnload();
	}

	@Override
	public void validate() {
		super.validate();
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (id == null) {
			generateId();
		}
		boolean change = false;
		change = handleHungryNodeFirst(change);
		count++;
		checkLock();
		if (!worldObj.isRemote) {
			change = handleDischarge(change);
			change = handleRecharge(change);
			change = handleTaintNode(change);
			change = handleNodeStability(change);
			change = handleDarkNode(change);
			change = handlePureNode(change);
			change = handleHungryNodeSecond(change);
			if (change) {
				markDirty();
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		} else if (nodeType == NodeType.DARK && count % 50 == 0) {
			ItemCompassStone.sinisterNodes.put(new WorldCoordinates(this), Long.valueOf(System.currentTimeMillis()));
		}
	}

	public void nodeChange() {
		regeneration = -1;
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public double getDistanceTo(double par1, double par3, double par5) {
		double var7 = xCoord + 0.5D - par1;
		double var9 = yCoord + 0.5D - par3;
		double var11 = zCoord + 0.5D - par5;
		return var7 * var7 + var9 * var9 + var11 * var11;
	}

	@Override
	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
		return -1;
	}

	@Override
	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
		player.setItemInUse(wandstack, 2147483647);
		ItemWandCasting wand = (ItemWandCasting) wandstack.getItem();
		wand.setObjectInUse(wandstack, xCoord, yCoord, zCoord);
		return wandstack;
	}

	@Override
	public AspectList getAspects() {
		return aspects;
	}

	@Override
	public void setAspects(AspectList aspects) {
		this.aspects = aspects;
		aspectsBase = aspects.copy();
	}

	@Override
	public AspectList getAspectsBase() {
		return aspectsBase;
	}

	@Override
	public int addToContainer(Aspect aspect, int amount) {
		int left = amount + aspects.getAmount(aspect) - aspectsBase.getAmount(aspect);
		left = left > 0 ? left : 0;
		aspects.add(aspect, amount - left);
		return left;
	}

	@Override
	public boolean takeFromContainer(Aspect aspect, int amount) {
		return aspects.reduce(aspect, amount);
	}

	public Aspect takeRandomPrimalFromSource() {
		Aspect[] primals = aspects.getPrimalAspects();
		Aspect asp = primals[worldObj.rand.nextInt(primals.length)];
		if (asp != null && aspects.reduce(asp, 1)) {
			return asp;
		}
		return null;
	}

	public Aspect chooseRandomFilteredFromSource(AspectList filter, boolean preserve) {
		int min = preserve ? 1 : 0;
		ArrayList<Aspect> validaspects = new ArrayList<Aspect>();
		for (Aspect prim : aspects.getAspects()) {
			if (filter.getAmount(prim) > 0 && aspects.getAmount(prim) > min) {
				validaspects.add(prim);
			}
		}
		if (validaspects.size() == 0) {
			return null;
		}
		Aspect asp = validaspects.get(worldObj.rand.nextInt(validaspects.size()));
		if (asp != null && aspects.getAmount(asp) > min) {
			return asp;
		}
		return null;
	}

	@Override
	public NodeType getNodeType() {
		return nodeType;
	}

	@Override
	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public NodeModifier getNodeModifier() {
		return nodeModifier;
	}

	@Override
	public void setNodeModifier(NodeModifier nodeModifier) {
		this.nodeModifier = nodeModifier;
	}

	@Override
	public int getNodeVisBase(Aspect aspect) {
		return aspectsBase.getAmount(aspect);
	}

	@Override
	public void setNodeVisBase(Aspect aspect, short nodeVisBase) {
		if (aspectsBase.getAmount(aspect) < nodeVisBase) {
			aspectsBase.merge(aspect, nodeVisBase);
		} else {
			aspectsBase.reduce(aspect, aspectsBase.getAmount(aspect) - nodeVisBase);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		lastActive = nbttagcompound.getLong("lastActive");
		AspectList al = new AspectList();
		NBTTagList tlist = nbttagcompound.getTagList("AspectsBase", 10);
		for (int j = 0; j < tlist.tagCount(); j++) {
			NBTTagCompound rs = tlist.getCompoundTagAt(j);
			if (rs.hasKey("key")) {
				al.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
			}
		}
		Short oldBase = Short.valueOf(nbttagcompound.getShort("nodeVisBase"));
		aspectsBase = new AspectList();
		if (oldBase.shortValue() > 0 && al.size() == 0) {
			for (Aspect a : aspects.getAspects()) {
				aspectsBase.merge(a, oldBase.shortValue());
			}
		} else {
			aspectsBase = al.copy();
		}
		int regen = 600;
		if (nodeModifier != null) {
			switch (nodeModifier) {
				case BRIGHT:
					regen = 400;
					break;
				case PALE:
					regen = 900;
					break;
				case FADING:
					regen = 0;
					break;
			}
		}
		long ct = System.currentTimeMillis();
		int inc = regen * 75;
		if (regen > 0 && lastActive > 0L && ct > lastActive + inc) {
			catchUp = true;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setLong("lastActive", lastActive);
		NBTTagList tlist = new NBTTagList();
		nbttagcompound.setTag("AspectsBase", tlist);
		for (Aspect aspect : aspectsBase.getAspects()) {
			if (aspect != null) {
				NBTTagCompound f = new NBTTagCompound();
				f.setString("key", aspect.getTag());
				f.setInteger("amount", aspectsBase.getAmount(aspect));
				tlist.appendTag(f);
			}
		}
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound) {
		id = nbttagcompound.getString("nodeId");
		if (worldObj != null && locations != null) {
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add(Integer.valueOf(worldObj.provider.dimensionId));
			t.add(Integer.valueOf(xCoord));
			t.add(Integer.valueOf(yCoord));
			t.add(Integer.valueOf(zCoord));
			locations.put(id, t);
		}
		nodeType = NodeType.values()[nbttagcompound.getByte("type")];
		byte mod = nbttagcompound.getByte("modifier");
		if (mod >= 0) {
			nodeModifier = NodeModifier.values()[mod];
		} else {
			nodeModifier = null;
		}
		aspects.readFromNBT(nbttagcompound);
		String de = nbttagcompound.getString("drainer");
		if (de != null && de.length() > 0 && getWorldObj() != null) {
			drainEntity = getWorldObj().getPlayerEntityByName(de);
			if (drainEntity != null) {
				drainCollision = new MovingObjectPosition(xCoord, yCoord, zCoord, 0, Vec3.createVectorHelper(drainEntity.posX, drainEntity.posY, drainEntity.posZ));
			}
		}
		drainColor = nbttagcompound.getInteger("draincolor");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound) {
		if (id == null) {
			id = generateId();
		}
		if (worldObj != null && locations != null) {
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add(Integer.valueOf(worldObj.provider.dimensionId));
			t.add(Integer.valueOf(xCoord));
			t.add(Integer.valueOf(yCoord));
			t.add(Integer.valueOf(zCoord));
			locations.put(id, t);
		}
		nbttagcompound.setString("nodeId", id);
		nbttagcompound.setByte("type", (byte) nodeType.ordinal());
		nbttagcompound.setByte("modifier", nodeModifier == null ? -1 : (byte) nodeModifier.ordinal());
		aspects.writeToNBT(nbttagcompound);
		if (drainEntity != null && drainEntity instanceof EntityPlayer) {
			nbttagcompound.setString("drainer", drainEntity.getCommandSenderName());
		}
		nbttagcompound.setInteger("draincolor", drainColor);
	}

	@Override
	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
		boolean mfu = false;
		ItemWandCasting wand = (ItemWandCasting) wandstack.getItem();
		MovingObjectPosition movingobjectposition = EntityUtils.getMovingObjectPositionFromPlayer(worldObj, player, true);
		if (movingobjectposition == null || movingobjectposition.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
			player.stopUsingItem();
		} else {
			int i = movingobjectposition.blockX;
			int j = movingobjectposition.blockY;
			int k = movingobjectposition.blockZ;
			if (i != xCoord || j != yCoord || k != zCoord) {
				player.stopUsingItem();
			}
		}
		if (count % 5 == 0) {
			int tap = 1;
			if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODETAPPER1")) {
				tap++;
			}
			if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODETAPPER2")) {
				tap++;
			}
			boolean preserve = !player.isSneaking() && ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODEPRESERVE") && !"wood".equals(wand.getRod(wandstack).getTag()) && !"iron".equals(wand.getCap(wandstack).getTag());
			boolean success = false;
			Aspect aspect = null;
			if ((aspect = chooseRandomFilteredFromSource(wand.getAspectsWithRoom(wandstack), preserve)) != null) {
				int amt = aspects.getAmount(aspect);
				if (tap > amt) {
					tap = amt;
				}
				if (preserve && tap == amt) {
					tap--;
				}
				if (tap > 0) {
					int rem = wand.addVis(wandstack, aspect, tap, !worldObj.isRemote);
					if (rem < tap) {
						drainColor = aspect.getColor();
						if (!worldObj.isRemote) {
							takeFromContainer(aspect, tap - rem);
							mfu = true;
						}
						success = true;
					}
				}
			}
			if (success) {
				drainEntity = player;
				drainCollision = movingobjectposition;
				targetColor = new Color(drainColor);
			} else {
				drainEntity = null;
				drainCollision = null;
			}
			if (mfu) {
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				markDirty();
			}
		}
		if (player.worldObj.isRemote) {
			int r = targetColor.getRed();
			int g = targetColor.getGreen();
			int b = targetColor.getBlue();
			int r2 = color.getRed() * 4;
			int g2 = color.getGreen() * 4;
			int b2 = color.getBlue() * 4;
			color = new Color((r + r2) / 5, (g + g2) / 5, (b + b2) / 5);
		}
	}

	@Override
	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
		drainEntity = null;
		drainCollision = null;
	}

	@Override
	public boolean receiveClientEvent(int i, int j) {
		return super.receiveClientEvent(i, j);
	}

	@Override
	public boolean takeFromContainer(AspectList ot) {
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tag, int amount) {
		return false;
	}

	@Override
	public boolean doesContainerContain(AspectList ot) {
		return false;
	}

	@Override
	public int containerContains(Aspect tag) {
		return 0;
	}

	@Override
	public boolean doesContainerAccept(Aspect tag) {
		return true;
	}

	private boolean handleHungryNodeFirst(boolean change) {
		if (nodeType == NodeType.HUNGRY) {
			if (worldObj.isRemote) {
				for (int a = 0; a < Thaumcraft.proxy.particleCount(1); a++) {
					int tx = xCoord + worldObj.rand.nextInt(16) - worldObj.rand.nextInt(16);
					int ty = yCoord + worldObj.rand.nextInt(16) - worldObj.rand.nextInt(16);
					int tz = zCoord + worldObj.rand.nextInt(16) - worldObj.rand.nextInt(16);
					if (ty > worldObj.getHeightValue(tx, tz)) {
						ty = worldObj.getHeightValue(tx, tz);
					}
					Vec3 v1 = Vec3.createVectorHelper(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D);
					Vec3 v2 = Vec3.createVectorHelper(tx + 0.5D, ty + 0.5D, tz + 0.5D);
					MovingObjectPosition mop = ThaumcraftApiHelper.rayTraceIgnoringSource(worldObj, v1, v2, true, false, false);
					if (mop != null && getDistanceFrom(mop.blockX, mop.blockY, mop.blockZ) < 256.0D) {
						tx = mop.blockX;
						ty = mop.blockY;
						tz = mop.blockZ;
						Block bi = worldObj.getBlock(tx, ty, tz);
						int md = worldObj.getBlockMetadata(tx, ty, tz);
						if (!bi.isAir(worldObj, tx, ty, tz)) {
							Thaumcraft.proxy.hungryNodeFX(worldObj, tx, ty, tz, xCoord, yCoord, zCoord, bi, md);
						}
					}
				}
			}
			if (Config.hardNode) {
				List ents = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(15.0D, 15.0D, 15.0D));
				if (ents != null && ents.size() > 0) {
					for (Object ent : ents) {
						Entity eo = (Entity) ent;
						if (eo instanceof EntityPlayer && ((EntityPlayer) eo).capabilities.disableDamage) {
							continue;
						}
						if (eo.isEntityAlive() && !eo.isEntityInvulnerable()) {
							double d = getDistanceTo(eo.posX, eo.posY, eo.posZ);
							if (d < 2.0D) {
								eo.attackEntityFrom(DamageSource.outOfWorld, 1.0F);
								if (!eo.isEntityAlive() && !worldObj.isRemote) {
									ScanResult scan = new ScanResult((byte) 2, 0, 0, eo, "");
									AspectList al = ScanManager.getScanAspects(scan, worldObj);
									if (al != null && al.size() > 0) {
										al = ResearchManager.reduceToPrimals(al.copy());
										if (al != null && al.size() > 0) {
											Aspect a = al.getAspects()[worldObj.rand.nextInt(al.size())];
											if (aspects.getAmount(a) < getNodeVisBase(a)) {
												addToContainer(a, 1);
												change = true;
											} else if (worldObj.rand.nextInt(1 + getNodeVisBase(a) * 2) < al.getAmount(a)) {
												aspectsBase.add(a, 1);
												change = true;
											}
										}
									}
								}
							}
						}
						double var3 = (xCoord + 0.5D - eo.posX) / 15.0D;
						double var5 = (yCoord + 0.5D - eo.posY) / 15.0D;
						double var7 = (zCoord + 0.5D - eo.posZ) / 15.0D;
						double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
						double var11 = 1.0D - var9;
						if (var11 > 0.0D) {
							var11 *= var11;
							eo.motionX += var3 / var9 * var11 * 0.15D;
							eo.motionY += var5 / var9 * var11 * 0.25D;
							eo.motionZ += var7 / var9 * var11 * 0.15D;
						}
					}
				}
			}
		}
		return change;
	}

	private boolean handleDischarge(boolean change) {
		if (worldObj.getBlock(xCoord, yCoord, zCoord) != ConfigBlocks.blockAiry || nodeLock == 1) {
			return change;
		}
		if (nodeModifier == NodeModifier.FADING) {
			return change;
		}
		boolean shiny = nodeType == NodeType.HUNGRY || nodeModifier == NodeModifier.BRIGHT;
		int inc = nodeModifier == null ? 2 : shiny ? 1 : nodeModifier == NodeModifier.PALE ? 3 : 2;
		if (count % inc != 0) {
			return change;
		}
		int x = worldObj.rand.nextInt(5) - worldObj.rand.nextInt(5);
		int y = worldObj.rand.nextInt(5) - worldObj.rand.nextInt(5);
		int z = worldObj.rand.nextInt(5) - worldObj.rand.nextInt(5);
		if (nodeModifier == NodeModifier.PALE && worldObj.rand.nextBoolean()) {
			return change;
		}
		if (x != 0 || y != 0 || z != 0) {
			TileEntity te = worldObj.getTileEntity(xCoord + x, yCoord + y, zCoord + z);
			if (te != null && te instanceof INode && worldObj.getBlock(xCoord + x, yCoord + y, zCoord + z) == ConfigBlocks.blockAiry) {
				if (te instanceof TileNode && ((TileNode) te).nodeLock > 0) {
					return change;
				}
				INode nd = (INode) te;
				int ndavg = (nd.getAspects().visSize() + nd.getAspectsBase().visSize()) / 2;
				int thisavg = (aspects.visSize() + aspectsBase.visSize()) / 2;
				if (ndavg < thisavg && nd.getAspects().size() > 0) {
					Aspect a = nd.getAspects().getAspects()[worldObj.rand.nextInt(nd.getAspects().size())];
					boolean u = false;
					if (aspects.getAmount(a) < getNodeVisBase(a) && nd.takeFromContainer(a, 1)) {
						addToContainer(a, 1);
						u = true;
					} else if (nd.takeFromContainer(a, 1)) {
						if (worldObj.rand.nextInt(1 + (int) (getNodeVisBase(a) / (shiny ? 1.5D : 1.0D))) == 0) {
							aspectsBase.add(a, 1);
							if (nodeModifier == NodeModifier.PALE && worldObj.rand.nextInt(100) == 0) {
								nodeModifier = null;
								regeneration = -1;
							}
							if (worldObj.rand.nextInt(3) == 0) {
								nd.setNodeVisBase(a, (short) (nd.getNodeVisBase(a) - 1));
							}
						}
						u = true;
					}
					if (u) {
						((TileNode) te).wait = ((TileNode) te).regeneration / 2;
						worldObj.markBlockForUpdate(xCoord + x, yCoord + y, zCoord + z);
						te.markDirty();
						change = true;
						PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockZap((xCoord + x) + 0.5F, (yCoord + y) + 0.5F, (zCoord + z) + 0.5F, xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 32.0D));
					}
				}
			}
		}
		return change;
	}

	private boolean handleRecharge(boolean change) {
		if (regeneration < 0) {
			regeneration = 600;
			if (nodeModifier != null) {
				switch (nodeModifier) {
					case BRIGHT:
						regeneration = 400;
						break;
					case PALE:
						regeneration = 900;
						break;
					case FADING:
						regeneration = 0;
						break;
				}
			}
			if (nodeLock == 1) {
				regeneration *= 2;
			}
			if (nodeLock == 2) {
				regeneration *= 20;
			}
		}
		if (catchUp) {
			catchUp = false;
			long ct = System.currentTimeMillis();
			int inc = regeneration * 75;
			int amt = inc > 0 ? (int) ((ct - lastActive) / inc) : 0;
			if (amt > 0) {
				for (int a = 0; a < Math.min(amt, aspectsBase.visSize()); a++) {
					AspectList al = new AspectList();
					for (Aspect aspect : aspects.getAspects()) {
						if (aspects.getAmount(aspect) < getNodeVisBase(aspect)) {
							al.add(aspect, 1);
						}
					}
					if (al.size() > 0) {
						addToContainer(al.getAspects()[worldObj.rand.nextInt(al.size())], 1);
					}
				}
			}
		}
		if (count % 1200 == 0) {
			for (Aspect aspect : aspects.getAspects()) {
				if (aspects.getAmount(aspect) <= 0) {
					setNodeVisBase(aspect, (short) (getNodeVisBase(aspect) - 1));
					if (worldObj.rand.nextInt(20) == 0 || getNodeVisBase(aspect) <= 0) {
						aspects.remove(aspect);
						if (worldObj.rand.nextInt(5) == 0) {
							if (nodeModifier == NodeModifier.BRIGHT) {
								nodeModifier = null;
							} else if (nodeModifier == null) {
								nodeModifier = NodeModifier.PALE;
							}
							if (nodeModifier == NodeModifier.PALE && worldObj.rand.nextInt(5) == 0) {
								nodeModifier = NodeModifier.FADING;
							}
						}
						nodeChange();
						break;
					}
					nodeChange();
				}
			}
			if (aspects.size() <= 0) {
				invalidate();
				if (getBlockType() == ConfigBlocks.blockAiry) {
					worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				} else if (getBlockType() == ConfigBlocks.blockMagicalLog) {
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, worldObj.getBlockMetadata(xCoord, yCoord, zCoord) - 1, 3);
				}
			}
		}
		if (wait > 0) {
			wait--;
		}
		if (regeneration > 0 && wait == 0 && count % regeneration == 0) {
			lastActive = System.currentTimeMillis();
			AspectList al = new AspectList();
			for (Aspect aspect : aspects.getAspects()) {
				if (aspects.getAmount(aspect) < getNodeVisBase(aspect)) {
					al.add(aspect, 1);
				}
			}
			if (al.size() > 0) {
				addToContainer(al.getAspects()[worldObj.rand.nextInt(al.size())], 1);
				change = true;
			}
		}
		return change;
	}

	private boolean handleTaintNode(boolean change) {
		return change;
	}

	private boolean handleNodeStability(boolean change) {
		if (count % 100 == 0) {
			if (nodeType == NodeType.UNSTABLE && worldObj.rand.nextBoolean()) {
				if (nodeLock == 0) {
					Aspect aspect = null;
					if ((aspect = takeRandomPrimalFromSource()) != null) {
						EntityAspectOrb orb = new EntityAspectOrb(worldObj, xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, aspect, 1);
						worldObj.spawnEntityInWorld(orb);
						change = true;
					}
				} else if (worldObj.rand.nextInt(10000 / nodeLock) == 42) {
					nodeType = NodeType.NORMAL;
					change = true;
				}
			}
			if (nodeModifier == NodeModifier.FADING && nodeLock > 0 && worldObj.rand.nextInt(12500 / nodeLock) == 69) {
				nodeModifier = NodeModifier.PALE;
				change = true;
			}
		}
		return change;
	}

	private boolean handlePureNode(boolean change) {
		int dimbl = ThaumcraftWorldGenerator.getDimBlacklist(worldObj.provider.dimensionId);
		if (worldObj.provider.dimensionId != -1 && worldObj.provider.dimensionId != 1 && dimbl != 0 && dimbl != 2 && nodeType == NodeType.PURE && count % 50 == 0) {
			int x = xCoord + worldObj.rand.nextInt(8) - worldObj.rand.nextInt(8);
			int z = zCoord + worldObj.rand.nextInt(8) - worldObj.rand.nextInt(8);
			BiomeGenBase bg = worldObj.getBiomeGenForCoords(x, z);
			int biobl = ThaumcraftWorldGenerator.getBiomeBlacklist(bg.biomeID);
			if (biobl != 0 && biobl != 2 && bg.biomeID != ThaumcraftWorldGenerator.biomeMagicalForest.biomeID) {
				if (bg.biomeID == ThaumcraftWorldGenerator.biomeTaint.biomeID) {
					Utils.setBiomeAt(worldObj, x, z, ThaumcraftWorldGenerator.biomeMagicalForest);
				} else if (worldObj.getBlock(xCoord, yCoord, zCoord) == ConfigBlocks.blockMagicalLog) {
					Utils.setBiomeAt(worldObj, x, z, ThaumcraftWorldGenerator.biomeMagicalForest);
				}
			}
		}
		return change;
	}

	private boolean handleDarkNode(boolean change) {
		int dimbl = ThaumcraftWorldGenerator.getDimBlacklist(worldObj.provider.dimensionId);
		int biobl = ThaumcraftWorldGenerator.getBiomeBlacklist(worldObj.getBiomeGenForCoords(xCoord, zCoord).biomeID);
		if (biobl != 0 && biobl != 2 && worldObj.provider.dimensionId != -1 && worldObj.provider.dimensionId != 1 && dimbl != 0 && dimbl != 2 && nodeType == NodeType.DARK && count % 50 == 0) {
			if (Config.hardNode && worldObj.rand.nextBoolean() && worldObj.getClosestPlayer(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, 24.0D) != null) {
				EntityGiantBrainyZombie entity = new EntityGiantBrainyZombie(worldObj);
				if (entity != null) {
					int j = worldObj.getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(10.0D, 6.0D, 10.0D)).size();
					if (j <= 3) {
						double d0 = xCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 5.0D;
						double d3 = yCoord + worldObj.rand.nextInt(3) - 1;
						double d4 = zCoord + (worldObj.rand.nextDouble() - worldObj.rand.nextDouble()) * 5.0D;
						EntityGiantBrainyZombie entityGiantBrainyZombie = entity instanceof EntityLiving ? entity : null;
						entity.setLocationAndAngles(d0, d3, d4, worldObj.rand.nextFloat() * 360.0F, 0.0F);
						if (entityGiantBrainyZombie == null || entityGiantBrainyZombie.getCanSpawnHere()) {
							worldObj.spawnEntityInWorld(entityGiantBrainyZombie);
							worldObj.playAuxSFX(2004, xCoord, yCoord, zCoord, 0);
							if (entityGiantBrainyZombie != null) {
								entityGiantBrainyZombie.spawnExplosionParticle();
							}
						}
					}
				}
			}
		}
		return change;
	}

	private boolean handleHungryNodeSecond(boolean change) {
		if (nodeType == NodeType.HUNGRY && count % 50 == 0) {
			int tx = xCoord + worldObj.rand.nextInt(16) - worldObj.rand.nextInt(16);
			int ty = yCoord + worldObj.rand.nextInt(16) - worldObj.rand.nextInt(16);
			int tz = zCoord + worldObj.rand.nextInt(16) - worldObj.rand.nextInt(16);
			if (ty > worldObj.getHeightValue(tx, tz)) {
				ty = worldObj.getHeightValue(tx, tz);
			}
			Vec3 v1 = Vec3.createVectorHelper(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D);
			Vec3 v2 = Vec3.createVectorHelper(tx + 0.5D, ty + 0.5D, tz + 0.5D);
			MovingObjectPosition mop = ThaumcraftApiHelper.rayTraceIgnoringSource(worldObj, v1, v2, true, false, false);
			if (mop != null && getDistanceFrom(mop.blockX, mop.blockY, mop.blockZ) < 256.0D) {
				tx = mop.blockX;
				ty = mop.blockY;
				tz = mop.blockZ;
				Block bi = worldObj.getBlock(tx, ty, tz);
				int md = worldObj.getBlockMetadata(tx, ty, tz);
				if (!bi.isAir(worldObj, tx, ty, tz)) {
					float h = bi.getBlockHardness(worldObj, tx, ty, tz);
					if (h >= 0.0F && h < 5.0F) {
						worldObj.func_147480_a(tx, ty, tz, true);
					}
				}
			}
		}
		return change;
	}

	public byte getLock() {
		return nodeLock;
	}

	public void checkLock() {
		if ((count <= 1 || count % 50 == 0) && yCoord > 0 && getBlockType() == ConfigBlocks.blockAiry) {
			byte oldLock = nodeLock;
			nodeLock = 0;
			if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord - 1, zCoord) &&
					worldObj.getBlock(xCoord, yCoord - 1, zCoord) == ConfigBlocks.blockStoneDevice) {
				if (worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord) == 9) {
					nodeLock = 1;
				} else if (worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord) == 10) {
					nodeLock = 2;
				}
			}
			if (oldLock != nodeLock) {
				regeneration = -1;
			}
		}
	}
}
