package got.common.world.biome.sothoryos;

import got.client.sound.GOTMusicRegion;
import got.common.database.GOTAchievement;
import got.common.database.GOTBlocks;
import got.common.database.GOTInvasions;
import got.common.database.GOTSpawnList;
import got.common.world.biome.GOTBiome;
import got.common.world.map.GOTWaypoint;
import got.common.world.spawning.GOTBiomeSpawnList;
import got.common.world.spawning.GOTEventSpawner;
import got.common.world.spawning.GOTSpawnListContainer;
import got.common.world.structure.other.GOTStructureBurntHouse;
import got.common.world.structure.other.GOTStructureRottenHouse;
import got.common.world.structure.other.GOTStructureRuinedHouse;
import got.common.world.structure.sothoryos.summer.GOTStructureSummerSettlement;

import java.util.ArrayList;
import java.util.Collection;

public class GOTBiomeSummerIslands extends GOTBiome {
	public GOTBiomeSummerIslands(int i, boolean major) {
		super(i, major);
		topBlock = GOTBlocks.mudGrass;
		fillerBlock = GOTBlocks.mud;

		preseter.setupJungleView();
		preseter.setupJungleFlora();
		preseter.setupJungleFauna();
		preseter.setupJungleTrees();

		decorator.setTreesPerChunk(2);

		decorator.addSettlement(new GOTStructureSummerSettlement(this, 1.0f));

		decorator.addStructure(new GOTStructureRuinedHouse(false), 2000);
		decorator.addStructure(new GOTStructureBurntHouse(false), 2000);
		decorator.addStructure(new GOTStructureRottenHouse(false), 4000);

		invasionSpawns.addInvasion(GOTInvasions.GHISCAR, GOTEventSpawner.EventChance.COMMON);

		Collection<GOTSpawnListContainer> c0 = new ArrayList<>();
		c0.add(GOTBiomeSpawnList.entry(GOTSpawnList.SUMMER_MILITARY, 4).setSpawnChance(SPAWN));
		npcSpawnList.newFactionList(8).add(c0);
		Collection<GOTSpawnListContainer> c1 = new ArrayList<>();
		c1.add(GOTBiomeSpawnList.entry(GOTSpawnList.GHISCAR_CONQUEST, 10).setSpawnChance(CONQUEST_SPAWN));
		npcSpawnList.newFactionList(0).add(c1);
		Collection<GOTSpawnListContainer> c2 = new ArrayList<>();
		c2.add(GOTBiomeSpawnList.entry(GOTSpawnList.MANTICORE, 10).setSpawnChance(CONQUEST_SPAWN));
		npcSpawnList.newFactionList(2).add(c2);
	}

	@Override
	public GOTAchievement getBiomeAchievement() {
		return GOTAchievement.enterSummerIslands;
	}

	@Override
	public GOTMusicRegion.Sub getBiomeMusic() {
		return GOTMusicRegion.SOTHORYOS.getSubregion(biomeName);
	}

	@Override
	public GOTWaypoint.Region getBiomeWaypoints() {
		return GOTWaypoint.Region.SUMMER;
	}
}