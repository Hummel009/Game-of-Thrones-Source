package got.common.inventory;

import got.common.GOTLevelData;
import got.common.database.GOTAchievement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class GOTSlotAlloyResult extends SlotFurnace {
	private final InventoryPlayer inv;

	public GOTSlotAlloyResult(InventoryPlayer inv, IInventory forge, int i) {
		super(inv.player, forge, i + 8, 53 + i * 18, 85);
		this.inv = inv;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack) {
		super.onPickupFromSlot(entityplayer, itemstack);
		if (!inv.player.worldObj.isRemote) {
			GOTLevelData.getData(inv.player).addAchievement(GOTAchievement.useAlloyForge);
		}
	}
}