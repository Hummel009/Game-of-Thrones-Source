package got.common.entity.sothoryos.summer;

import got.common.database.GOTItems;
import got.common.database.GOTTradeEntries;
import got.common.entity.other.iface.GOTTradeable;
import got.common.entity.other.utils.GOTEntityUtils;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntitySummerBrewer extends GOTEntitySummerMan implements GOTTradeable {
	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntitySummerBrewer(World world) {
		super(world);
	}

	@Override
	public float getAlignmentBonus() {
		return 2.0f;
	}

	@Override
	public GOTTradeEntries getSellsPool() {
		return GOTTradeEntries.BREWER_SELLS;
	}

	@Override
	public GOTTradeEntries getBuysPool() {
		return GOTTradeEntries.BREWER_BUYS;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData entityData = super.onSpawnWithEgg(data);

		npcItemsInv.setIdleItem(new ItemStack(GOTItems.mugAle));

		GOTEntityUtils.setupTurban(this, rand);

		return entityData;
	}
}