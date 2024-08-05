package got.common.world.biome.essos;

import got.common.database.GOTAchievement;
import got.common.world.map.GOTWaypoint;
import got.common.world.spawning.GOTEventSpawner;

public class GOTBiomeDothrakiSeaForest extends GOTBiomeEssosBase {
	public GOTBiomeDothrakiSeaForest(int i, boolean major) {
		super(i, major);
		preseter.setupSavannahView();
		preseter.setupSavannahFlora();
		preseter.setupSavannahFauna();
		preseter.setupSavannahTrees();

		decorator.setTreesPerChunk(8);
	}

	@Override
	public GOTWaypoint.Region getBiomeWaypoints() {
		return GOTWaypoint.Region.DOTHRAKI;
	}

	@Override
	public GOTAchievement getBiomeAchievement() {
		return GOTAchievement.enterDothrakiSea;
	}

	@Override
	public GOTEventSpawner.EventChance getBanditChance() {
		return GOTEventSpawner.EventChance.NEVER;
	}
}