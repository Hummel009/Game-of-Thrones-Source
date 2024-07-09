package got.common.world.biome.essos;

import got.common.world.map.GOTWaypoint;
import got.common.world.spawning.GOTEventSpawner;

public class GOTBiomeEssosForest extends GOTBiomeEssosBase {
	public GOTBiomeEssosForest(int i, boolean major) {
		super(i, major);
		preseter.setupForestView();
		preseter.setupForestFlora();
		preseter.setupForestFauna();
		preseter.setupSouthernTrees(true);

		setupRuinedStructures(false);

		biomeWaypoints = GOTWaypoint.Region.VALYRIA;
		banditChance = GOTEventSpawner.EventChance.NEVER;
	}
}