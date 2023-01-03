package got.common.entity.essos.braavos;

import got.common.database.*;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityBraavosMason extends GOTEntityBraavosTrader {
	public GOTEntityBraavosMason(World world) {
		super(world);
		canBeMarried = false;
	}

	@Override
	public GOTTradeEntries getBuyPool() {
		return GOTTradeEntries.NORD_MASON_BUY;
	}

	@Override
	public GOTTradeEntries getSellPool() {
		return GOTTradeEntries.C_MINER_SELL;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		data = super.onSpawnWithEgg(data);
		npcItemsInv.setMeleeWeapon(new ItemStack(Items.iron_pickaxe));
		npcItemsInv.setIdleItem(new ItemStack(Blocks.stone));
		return data;
	}
}
