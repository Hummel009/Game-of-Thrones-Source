package got.common.entity.other;

import got.GOT;
import got.common.faction.GOTFaction;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

public class GOTNPCSelectByFaction implements IEntitySelector {
	public GOTFaction faction;

	public GOTNPCSelectByFaction(GOTFaction f) {
		faction = f;
	}

	@Override
	public boolean isEntityApplicable(Entity entity) {
		return entity.isEntityAlive() && GOT.getNPCFaction(entity) == faction;
	}
}