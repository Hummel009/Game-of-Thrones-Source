package got.common.entity.essos.yiti;

import got.common.database.GOTItems;
import got.common.database.GOTTradeEntries;
import got.common.item.other.GOTItemRobes;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityYiTiButcher extends GOTEntityYiTiMarketTrader {
	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntityYiTiButcher(World world) {
		super(world);
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
		IEntityLivingData entityData = super.onSpawnWithEgg(data);
		npcItemsInv.setIdleItem(new ItemStack(GOTItems.rabbitRaw));
		int robeColor = 14509656;
		ItemStack body = new ItemStack(GOTItems.kaftanChestplate);
		ItemStack legs = new ItemStack(GOTItems.kaftanLeggings);
		GOTItemRobes.setRobesColor(body, robeColor);
		GOTItemRobes.setRobesColor(legs, robeColor);
		setCurrentItemOrArmor(3, body);
		setCurrentItemOrArmor(2, legs);
		return entityData;
	}
}