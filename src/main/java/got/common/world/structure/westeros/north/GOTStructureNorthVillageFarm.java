package got.common.world.structure.westeros.north;

import got.common.world.structure.westeros.common.GOTStructureWesterosVillageFarm;

public abstract class GOTStructureNorthVillageFarm extends GOTStructureWesterosVillageFarm {
	public GOTStructureNorthVillageFarm(boolean flag) {
		super(flag);
		isNorth = true;
	}
}