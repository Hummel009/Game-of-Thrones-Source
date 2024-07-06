package got.common.entity.other.iface;

import got.common.GOTLevelData;
import got.common.database.GOTAchievement;
import got.common.faction.GOTFaction;
import net.minecraft.entity.player.EntityPlayer;

public interface GOTHireableBase extends GOTTradeCondition {
	GOTFaction getFaction();

	String getNPCName();

	default void onUnitTrade(EntityPlayer entityPlayer) {
		GOTLevelData.getData(entityPlayer).addAchievement(GOTAchievement.trade);
	}
}