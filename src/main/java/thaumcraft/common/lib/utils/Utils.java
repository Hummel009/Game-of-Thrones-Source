package thaumcraft.common.lib.utils;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.internal.WeightedRandomLoot;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.equipment.ItemElementalAxe;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXVisDrain;
import thaumcraft.common.lib.network.misc.PacketBiomeChange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Utils {
	public static final String[] colorNames = {"White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};
	public static final int[] colors = {15790320, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 4408131, 10526880, 2651799, 8073150, 2437522, 5320730, 3887386, 11743532, 1973019};
	public static HashMap<List<Object>, ItemStack> specialMiningResult = new HashMap<List<Object>, ItemStack>();
	public static HashMap<List<Object>, Float> specialMiningChance = new HashMap<List<Object>, Float>();
	public static HashMap<WorldCoordinates, Long> effectBuffer = new HashMap<WorldCoordinates, Long>();

	public static boolean isChunkLoaded(World world, int x, int z) {
		int xx = x >> 4;
		int zz = z >> 4;
		return world.getChunkProvider().chunkExists(xx, zz);
	}

	public static boolean useBonemealAtLoc(World world, EntityPlayer player, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}
		if (event.getResult() == Event.Result.ALLOW) {
			return true;
		}
		if (block instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) block;
			if (igrowable.func_149851_a(world, x, y, z, world.isRemote)) {
				if (!world.isRemote && igrowable.func_149852_a(world, world.rand, x, y, z)) {
					igrowable.func_149853_b(world, world.rand, x, y, z);
				}
				return true;
			}
		}
		return false;
	}

	public static boolean hasColor(byte[] colors) {
		for (byte col : colors) {
			if (col >= 0) {
				return true;
			}
		}
		return false;
	}

	public static int getFirstUncoveredY(World world, int par1, int par2) {
		int var3;
		for (var3 = 5; !world.isAirBlock(par1, var3 + 1, par2); var3++) {
		}
		return var3;
	}

	public static boolean isEETransmutionItem(Item item) {
		try {
			String itemClass = "com.pahimar.ee3.item.ITransmutationStone";
			Class<?> ee = Class.forName(itemClass);
			if (ee.isAssignableFrom(item.getClass())) {
				return true;
			}
		} catch (Exception ex) {
		}
		return false;
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0L, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static int getFirstUncoveredBlockHeight(World world, int par1, int par2) {
		int var3;
		for (var3 = 10; !world.isAirBlock(par1, var3 + 1, par2) || var3 > 250; var3++) {
		}
		return var3;
	}

	public static void addSpecialMiningResult(ItemStack in, ItemStack out, float chance) {
		specialMiningResult.put(Arrays.asList(in.getItem(), in.getItemDamage()), out);
		specialMiningChance.put(Arrays.asList(in.getItem(), in.getItemDamage()), Float.valueOf(chance));
	}

	public static ItemStack findSpecialMiningResult(ItemStack is, float chance, Random rand) {
		ItemStack dropped = is.copy();
		float r = rand.nextFloat();
		List ik = Arrays.asList(is.getItem(), Integer.valueOf(is.getItemDamage()));
		if (specialMiningResult.containsKey(ik) && r <= chance * specialMiningChance.get(ik).floatValue()) {
			dropped = specialMiningResult.get(ik).copy();
			dropped.stackSize *= is.stackSize;
		}
		return dropped;
	}

	public static float clamp_float(float par0, float par1, float par2) {
		return par0 < par1 ? par1 : par0 > par2 ? par2 : par0;
	}

	public static void setBiomeAt(World world, int x, int z, BiomeGenBase biome) {
		if (biome == null) {
			return;
		}
		Chunk chunk = world.getChunkFromBlockCoords(x, z);
		byte[] array = chunk.getBiomeArray();
		array[(z & 0xF) << 4 | x & 0xF] = (byte) (biome.biomeID & 0xFF);
		chunk.setBiomeArray(array);
		if (!world.isRemote) {
			PacketHandler.INSTANCE.sendToAllAround(new PacketBiomeChange(x, z, (short) biome.biomeID), new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, world.getHeightValue(x, z), z, 32.0D));
		}
	}

	public static boolean isWoodLog(IBlockAccess world, int x, int y, int z) {
		Block bi = world.getBlock(x, y, z);
		int md = world.getBlockMetadata(x, y, z);
		return bi != Blocks.air && (bi.canSustainLeaves(world, x, y, z) || ItemElementalAxe.oreDictLogs.contains(Arrays.asList(bi, Integer.valueOf(md))));
	}

	public static void resetFloatCounter(EntityPlayerMP player) {
		try {
			ObfuscationReflectionHelper.setPrivateValue(NetHandlerPlayServer.class, player.playerNetServerHandler, Integer.valueOf(0), "floatingTickCount", "field_147365_f");
		} catch (Exception e) {
		}
	}

	public static boolean getBit(int value, int bit) {
		return (value & 1 << bit) != 0;
	}

	public static int setBit(int value, int bit) {
		return value | 1 << bit;
	}

	public static int clearBit(int value, int bit) {
		return value & (1 << bit ^ 0xFFFFFFFF);
	}

	public static int toggleBit(int value, int bit) {
		return value ^ 1 << bit;
	}

	public static byte pack(boolean[] vals) {
		byte result = 0;
		for (boolean bit : vals) {
			result = (byte) (result << 1 | (bit ? 1 : 0) & 0x1);
		}
		return result;
	}

	public static boolean[] unpack(byte val) {
		boolean[] result = new boolean[8];
		for (int i = 0; i < 8; i++) {
			result[i] = (byte) (val >> 7 - i & 0x1) == 1;
		}
		return result;
	}

	public static Object getNBTDataFromId(NBTTagCompound nbt, byte id, String key) {
		switch (id) {
			case 1:
				return Byte.valueOf(nbt.getByte(key));
			case 2:
				return Short.valueOf(nbt.getShort(key));
			case 3:
				return Integer.valueOf(nbt.getInteger(key));
			case 4:
				return Long.valueOf(nbt.getLong(key));
			case 5:
				return Float.valueOf(nbt.getFloat(key));
			case 6:
				return Double.valueOf(nbt.getDouble(key));
			case 7:
				return nbt.getByteArray(key);
			case 8:
				return nbt.getString(key);
			case 9:
				return nbt.getTagList(key, 10);
			case 10:
				return nbt.getTag(key);
			case 11:
				return nbt.getIntArray(key);
		}
		return null;
	}

	public static void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color) {
		WorldCoordinates wc = new WorldCoordinates(x, y, z, dim);
		Long time = Long.valueOf(System.currentTimeMillis());
		Random rand = new Random(time.longValue());
		if (effectBuffer.containsKey(wc)) {
			if (effectBuffer.get(wc).longValue() < time.longValue()) {
				effectBuffer.remove(wc);
			}
		} else {
			effectBuffer.put(wc, Long.valueOf(time.longValue() + 500L + rand.nextInt(100)));
			PacketHandler.INSTANCE.sendToAllAround(new PacketFXVisDrain(x, y, z, x2, y2, z2, color), new NetworkRegistry.TargetPoint(dim, x, y, z, 64.0D));
		}
	}

	public static <T, E> void setPrivateFinalValue(Class<? super T> classToAccess, T instance, E value, String... fieldNames) {
		Field field = ReflectionHelper.findField(classToAccess, ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));
		try {
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
			field.set(instance, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isLyingInCone(double[] x, double[] t, double[] b, float aperture) {
		double halfAperture = aperture / 2.0F;
		double[] apexToXVect = dif(t, x);
		double[] axisVect = dif(t, b);
		boolean isInInfiniteCone = dotProd(apexToXVect, axisVect) / magn(apexToXVect) / magn(axisVect) > Math.cos(halfAperture);
		if (!isInInfiniteCone) {
			return false;
		}
		boolean isUnderRoundCap = dotProd(apexToXVect, axisVect) / magn(axisVect) < magn(axisVect);
		return isUnderRoundCap;
	}

	public static double dotProd(double[] a, double[] b) {
		return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
	}

	public static double[] dif(double[] a, double[] b) {
		return new double[]{a[0] - b[0], a[1] - b[1], a[2] - b[2]};
	}

	public static double magn(double[] a) {
		return Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2]);
	}

	public static Vec3 calculateVelocity(Vec3 from, Vec3 to, double heightGain, double gravity) {
		double endGain = to.yCoord - from.yCoord;
		double horizDist = Math.sqrt(distanceSquared2d(from, to));
		double gain = heightGain;
		double maxGain = gain > endGain + gain ? gain : endGain + gain;
		double a = -horizDist * horizDist / 4.0D * maxGain;
		double b = horizDist;
		double c = -endGain;
		double slope = -b / 2.0D * a - Math.sqrt(b * b - 4.0D * a * c) / 2.0D * a;
		double vy = Math.sqrt(maxGain * gravity);
		double vh = vy / slope;
		double dx = to.xCoord - from.xCoord;
		double dz = to.zCoord - from.zCoord;
		double mag = Math.sqrt(dx * dx + dz * dz);
		double dirx = dx / mag;
		double dirz = dz / mag;
		double vx = vh * dirx;
		double vz = vh * dirz;
		return Vec3.createVectorHelper(vx, vy, vz);
	}

	public static double distanceSquared2d(Vec3 from, Vec3 to) {
		double dx = to.xCoord - from.xCoord;
		double dz = to.zCoord - from.zCoord;
		return dx * dx + dz * dz;
	}

	public static double distanceSquared3d(Vec3 from, Vec3 to) {
		double dx = to.xCoord - from.xCoord;
		double dy = to.yCoord - from.yCoord;
		double dz = to.zCoord - from.zCoord;
		return dx * dx + dy * dy + dz * dz;
	}

	public static ItemStack generateLoot(int rarity, Random rand) {
		ItemStack is = null;
		if (rarity > 0 && rand.nextFloat() < 0.025F * rarity) {
			is = genGear(rarity, rand);
			if (is == null) {
				is = generateLoot(rarity, rand);
			}
		} else {
			switch (rarity) {
				case 1:
					is = ((WeightedRandomLoot) WeightedRandom.getRandomItem(rand, WeightedRandomLoot.lootBagUncommon)).item;
					break;
				case 2:
					is = ((WeightedRandomLoot) WeightedRandom.getRandomItem(rand, WeightedRandomLoot.lootBagRare)).item;
					break;
				default:
					is = ((WeightedRandomLoot) WeightedRandom.getRandomItem(rand, WeightedRandomLoot.lootBagCommon)).item;
					break;
			}
		}
		if (is.getItem() == Items.book) {
			EnchantmentHelper.addRandomEnchantment(rand, is, (int) (5.0F + rarity * 0.75F * rand.nextInt(18)));
		}
		return is.copy();
	}

	private static ItemStack genGear(int rarity, Random rand) {
		ItemStack is = null;
		int quality = rand.nextInt(2);
		if (rand.nextFloat() < 0.2F) {
			quality++;
		}
		if (rand.nextFloat() < 0.15F) {
			quality++;
		}
		if (rand.nextFloat() < 0.1F) {
			quality++;
		}
		if (rand.nextFloat() < 0.095F) {
			quality++;
		}
		if (rand.nextFloat() < 0.095F) {
			quality++;
		}
		Item item = getGearItemForSlot(rand.nextInt(5), quality);
		if (item != null) {
			is = new ItemStack(item, 1, rand.nextInt(1 + item.getMaxDamage() / 6));
		} else {
			return null;
		}
		if (rand.nextInt(4) < rarity) {
			EnchantmentHelper.addRandomEnchantment(rand, is, (int) (5.0F + rarity * 0.75F * rand.nextInt(18)));
		}
		return is.copy();
	}

	private static Item getGearItemForSlot(int slot, int quality) {
		switch (slot) {
			case 4:
				if (quality == 0) {
					return Items.leather_helmet;
				}
				if (quality == 1) {
					return Items.golden_helmet;
				}
				if (quality == 2) {
					return Items.chainmail_helmet;
				}
				if (quality == 3) {
					return Items.iron_helmet;
				}
				if (quality == 4) {
					return ConfigItems.itemHelmetThaumium;
				}
				if (quality == 5) {
					return Items.diamond_helmet;
				}
				if (quality == 6) {
					return ConfigItems.itemHelmetVoid;
				}
			case 3:
				if (quality == 0) {
					return Items.leather_chestplate;
				}
				if (quality == 1) {
					return Items.golden_chestplate;
				}
				if (quality == 2) {
					return Items.chainmail_chestplate;
				}
				if (quality == 3) {
					return Items.iron_chestplate;
				}
				if (quality == 4) {
					return ConfigItems.itemChestThaumium;
				}
				if (quality == 5) {
					return Items.diamond_chestplate;
				}
				if (quality == 6) {
					return ConfigItems.itemChestVoid;
				}
			case 2:
				if (quality == 0) {
					return Items.leather_leggings;
				}
				if (quality == 1) {
					return Items.golden_leggings;
				}
				if (quality == 2) {
					return Items.chainmail_leggings;
				}
				if (quality == 3) {
					return Items.iron_leggings;
				}
				if (quality == 4) {
					return ConfigItems.itemLegsThaumium;
				}
				if (quality == 5) {
					return Items.diamond_leggings;
				}
				if (quality == 6) {
					return ConfigItems.itemLegsVoid;
				}
			case 1:
				if (quality == 0) {
					return Items.leather_boots;
				}
				if (quality == 1) {
					return Items.golden_boots;
				}
				if (quality == 2) {
					return Items.chainmail_boots;
				}
				if (quality == 3) {
					return Items.iron_boots;
				}
				if (quality == 4) {
					return ConfigItems.itemBootsThaumium;
				}
				if (quality == 5) {
					return Items.diamond_boots;
				}
				if (quality == 6) {
					return ConfigItems.itemBootsVoid;
				}
			case 0:
				if (quality == 0) {
					return Items.iron_axe;
				}
				if (quality == 1) {
					return Items.iron_sword;
				}
				if (quality == 2) {
					return Items.golden_axe;
				}
				if (quality == 3) {
					return Items.golden_sword;
				}
				if (quality == 4) {
					return ConfigItems.itemSwordThaumium;
				}
				if (quality == 5) {
					return Items.diamond_sword;
				}
				if (quality == 6) {
					return ConfigItems.itemSwordVoid;
				}
				break;
		}
		return null;
	}
}

