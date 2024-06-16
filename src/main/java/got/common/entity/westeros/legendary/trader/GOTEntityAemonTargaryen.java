package got.common.entity.westeros.legendary.trader;

import got.common.database.GOTTradeEntries;
import got.common.entity.other.GOTEntityHumanBase;
import got.common.entity.other.iface.GOTTradeable;
import got.common.faction.GOTFaction;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GOTEntityAemonTargaryen extends GOTEntityHumanBase implements GOTTradeable {
	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntityAemonTargaryen(World world) {
		super(world);
		setupLegendaryNPC(true);
	}

	@Override
	public EntityAIBase getAttackAI() {
		return new EntityAIPanic(this, 1.4);
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.05);
	}

	@Override
	public boolean canTradeWith(EntityPlayer entityPlayer) {
		return isFriendlyAndAligned(entityPlayer);
	}

	@Override
	public float getAlignmentBonus() {
		return 100.0f;
	}

	@Override
	public GOTTradeEntries getBuyPool() {
		return GOTTradeEntries.C_MAESTER_BUY;
	}

	@Override
	public GOTFaction getFaction() {
		return GOTFaction.NIGHT_WATCH;
	}

	@Override
	public GOTTradeEntries getSellPool() {
		return GOTTradeEntries.C_MAESTER_SELL;
	}

	@Override
	public void setupNPCGender() {
		familyInfo.setMale(true);
	}
}