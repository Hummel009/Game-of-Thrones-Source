package got.common.world.biome.essos;

import got.common.database.GOTAchievement;
import got.common.database.GOTSpawnList;
import got.common.world.biome.GOTBiome;
import got.common.world.map.GOTBezierType;
import got.common.world.map.GOTWaypoint;
import got.common.world.spawning.GOTBiomeSpawnList;
import got.common.world.spawning.GOTSpawnListContainer;

import java.util.ArrayList;
import java.util.Collection;

public class GOTBiomeYiTiMarshes extends GOTBiomeEssosBase implements GOTBiome.Marshes {
	public GOTBiomeYiTiMarshes(int i, boolean major) {
		super(i, major);
		preseter.setupMarshesView();
		preseter.setupMarshesFlora();
		preseter.setupMarshesFauna();
		preseter.setupSouthernTrees(false);

		Collection<GOTSpawnListContainer> c0 = new ArrayList<>();
		c0.add(GOTBiomeSpawnList.entry(GOTSpawnList.CROCODILE, 10).setSpawnChance(CONQUEST_SPAWN));
		npcSpawnList.newFactionList(10).add(c0);

		biomeWaypoints = GOTWaypoint.Region.YI_TI;
		biomeAchievement = GOTAchievement.enterYiTi;
		enableRiver = false;
		wallBlock = GOTBezierType.WALL_YI_TI;
		roadBlock = GOTBezierType.PATH_PAVING;
	}
}