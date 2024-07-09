package got.common.world.biome.westeros;

import got.common.database.GOTAchievement;
import got.common.world.map.GOTWaypoint;

public class GOTBiomeArrynForest extends GOTBiomeWesterosBase {
	public GOTBiomeArrynForest(int i, boolean major) {
		super(i, major);
		preseter.setupForestView();
		preseter.setupForestFlora();
		preseter.setupForestFauna();
		preseter.setupMiderateTrees();

		setupRuinedStructures(false);

		biomeWaypoints = GOTWaypoint.Region.ARRYN;
		biomeAchievement = GOTAchievement.enterArryn;
	}
}