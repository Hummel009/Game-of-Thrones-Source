package got.common.entity.essos.braavos;

import got.common.database.GOTTradeEntries;
import got.common.entity.other.iface.GOTTradeable;
import got.common.entity.other.utils.GOTEntityUtils;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityBraavosLumberman extends GOTEntityBraavosMan implements GOTTradeable {
	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntityBraavosLumberman(World world) {
		super(world);
	}

	@Override
	public float getReputationBonus() {
		return 2.0f;
	}

	@Override
	public GOTTradeEntries getSellsPool() {
		return GOTTradeEntries.LUMBERMAN_EXOTIC_SELLS;
	}

	@Override
	public GOTTradeEntries getBuysPool() {
		return GOTTradeEntries.LUMBERMAN_EXOTIC_BUYS;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData entityData = super.onSpawnWithEgg(data);

		npcItemsInv.setMeleeWeapon(new ItemStack(Items.iron_axe));
		npcItemsInv.setIdleItem(npcItemsInv.getMeleeWeapon());

		GOTEntityUtils.setupTurban(this, rand);

		return entityData;
	}
}