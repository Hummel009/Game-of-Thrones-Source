package got.common.world.biome.westeros;

import got.common.database.GOTAchievement;
import got.common.world.map.GOTWaypoint;

public class GOTBiomeRiverlandsForest extends GOTBiomeWesterosBase {
	public GOTBiomeRiverlandsForest(int i, boolean major) {
		super(i, major);
		preseter.setupForestView();
		preseter.setupForestFlora();
		preseter.setupForestFauna();
		preseter.setupMiderateTrees();

		setupRuinedStructures(false);

		biomeWaypoints = GOTWaypoint.Region.RIVERLANDS;
		biomeAchievement = GOTAchievement.enterRiverlands;
	}
}