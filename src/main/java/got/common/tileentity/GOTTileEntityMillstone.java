package got.common.tileentity;

import cpw.mods.fml.relauncher.*;
import got.common.block.other.GOTBlockMillstone;
import got.common.recipe.GOTRecipeMillstone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

public class GOTTileEntityMillstone extends TileEntity implements IInventory, ISidedInventory {
	private ItemStack[] inventory = new ItemStack[2];
	private String specialMillstoneName;
	private boolean isMilling;
	private int currentMillTime = 0;

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		if (side == 0) {
		}
		return true;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
		return isItemValidForSlot(slot, itemstack);
	}

	private boolean canMill() {
		ItemStack itemstack = inventory[0];
		if (itemstack == null) {
			return false;
		}
		GOTRecipeMillstone.MillstoneResult result = GOTRecipeMillstone.getMillingResult(itemstack);
		if (result == null) {
			return false;
		}
		ItemStack resultItem = result.getResultItem();
		ItemStack inResultSlot = inventory[1];
		if (inResultSlot == null) {
			return true;
		}
		if (!inResultSlot.isItemEqual(resultItem)) {
			return false;
		}
		int resultSize = inResultSlot.stackSize + resultItem.stackSize;
		return resultSize <= getInventoryStackLimit() && resultSize <= resultItem.getMaxStackSize();
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (inventory[i] != null) {
			if (inventory[i].stackSize <= j) {
				ItemStack itemstack = inventory[i];
				inventory[i] = null;
				return itemstack;
			}
			ItemStack itemstack = inventory[i].splitStack(j);
			if (inventory[i].stackSize == 0) {
				inventory[i] = null;
			}
			return itemstack;
		}
		return null;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if (side == 0) {
			return new int[] { 1 };
		}
		if (side == 1) {
		}
		return new int[] { 0 };
	}

	public int getCurrentMillTime() {
		return currentMillTime;
	}

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? specialMillstoneName : StatCollector.translateToLocal("got.container.millstone");
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(value = Side.CLIENT)
	public int getMillProgressScaled(int i) {
		return getCurrentMillTime() * i / 200;
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (inventory[i] != null) {
			ItemStack itemstack = inventory[i];
			inventory[i] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return specialMillstoneName != null && specialMillstoneName.length() > 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		if (slot == 0) {
			return itemstack != null && GOTRecipeMillstone.getMillingResult(itemstack) != null;
		}
		return false;
	}

	public boolean isMilling() {
		return isMilling;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64.0;
	}

	private void millItem() {
		if (canMill()) {
			ItemStack itemstack = inventory[0];
			GOTRecipeMillstone.MillstoneResult result = GOTRecipeMillstone.getMillingResult(itemstack);
			ItemStack resultItem = result.getResultItem();
			float chance = result.getChance();
			if (worldObj.rand.nextFloat() < chance) {
				ItemStack inResultSlot = inventory[1];
				if (inResultSlot == null) {
					inResultSlot = resultItem.copy();
				} else if (inResultSlot.isItemEqual(resultItem)) {
					inResultSlot.stackSize += resultItem.stackSize;
				}
				inventory[1] = inResultSlot;
			}
			--itemstack.stackSize;
			if (itemstack.stackSize <= 0) {
				inventory[0] = null;
			}
		}
	}

	@Override
	public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
		if (packet.func_148857_g() != null && packet.func_148857_g().hasKey("CustomName")) {
			specialMillstoneName = packet.func_148857_g().getString("CustomName");
		}
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList items = nbt.getTagList("Items", 10);
		inventory = new ItemStack[getSizeInventory()];
		for (int i = 0; i < items.tagCount(); ++i) {
			NBTTagCompound itemData = items.getCompoundTagAt(i);
			byte slot = itemData.getByte("Slot");
			if (slot < 0 || slot >= inventory.length) {
				continue;
			}
			inventory[slot] = ItemStack.loadItemStackFromNBT(itemData);
		}
		setMilling(nbt.getBoolean("Milling"));
		setCurrentMillTime(nbt.getInteger("MillTime"));
		if (nbt.hasKey("CustomName")) {
			specialMillstoneName = nbt.getString("CustomName");
		}
	}

	public void setCurrentMillTime(int currentMillTime) {
		this.currentMillTime = currentMillTime;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inventory[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	public void setMilling(boolean isMilling) {
		this.isMilling = isMilling;
	}

	public void setSpecialMillstoneName(String s) {
		specialMillstoneName = s;
	}

	private void toggleMillstoneActive() {
		GOTBlockMillstone.toggleMillstoneActive(worldObj, xCoord, yCoord, zCoord);
	}

	@Override
	public void updateEntity() {
		boolean needUpdate = false;
		if (!worldObj.isRemote) {
			boolean powered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
			if (powered && !isMilling()) {
				setMilling(true);
				setCurrentMillTime(0);
				needUpdate = true;
				toggleMillstoneActive();
			} else if (!powered && isMilling()) {
				setMilling(false);
				setCurrentMillTime(0);
				needUpdate = true;
				toggleMillstoneActive();
			}
			if (isMilling() && canMill()) {
				setCurrentMillTime(getCurrentMillTime() + 1);
				if (getCurrentMillTime() == 200) {
					setCurrentMillTime(0);
					millItem();
					needUpdate = true;
				}
			} else if (getCurrentMillTime() != 0) {
				setCurrentMillTime(0);
				needUpdate = true;
			}
		}
		if (needUpdate) {
			markDirty();
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < inventory.length; ++i) {
			if (inventory[i] == null) {
				continue;
			}
			NBTTagCompound itemData = new NBTTagCompound();
			itemData.setByte("Slot", (byte) i);
			inventory[i].writeToNBT(itemData);
			items.appendTag(itemData);
		}
		nbt.setTag("Items", items);
		nbt.setBoolean("Milling", isMilling());
		nbt.setInteger("MillTime", getCurrentMillTime());
		if (hasCustomInventoryName()) {
			nbt.setString("CustomName", specialMillstoneName);
		}
	}
}
