package got.common.entity.westeros.riverlands;

import got.common.database.GOTTradeEntries;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityRiverlandsButcher extends GOTEntityRiverlandsMarketTrader {
	public GOTEntityRiverlandsButcher(World world) {
		super(world);
		canBeMarried = false;
	}

	@Override
	public GOTTradeEntries getBuyPool() {
		return GOTTradeEntries.C_BUTCHER_BUY;
	}

	@Override
	public GOTTradeEntries getSellPool() {
		return GOTTradeEntries.C_BUTCHER_SELL;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData data1 = super.onSpawnWithEgg(data);
		npcItemsInv.setIdleItem(new ItemStack(Items.porkchop));
		return data1;
	}
}
