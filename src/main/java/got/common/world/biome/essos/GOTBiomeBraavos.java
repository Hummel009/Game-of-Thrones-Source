package got.common.world.biome.essos;

import got.client.sound.GOTBiomeMusic;
import got.client.sound.GOTBiomeMusic.MusicRegion;
import got.common.database.*;
import got.common.world.biome.GOTBiome;
import got.common.world.biome.variant.GOTBiomeVariant;
import got.common.world.map.GOTWaypoint;
import got.common.world.spawning.*;
import got.common.world.spawning.GOTBiomeSpawnList.SpawnListContainer;
import got.common.world.structure.essos.braavos.GOTStructureBraavosCity;

public class GOTBiomeBraavos extends GOTBiomeEssos {
	public GOTBiomeBraavos(int i, boolean major) {
		super(i, major);
		this.addBiomeVariant(GOTBiomeVariant.FIELD_CORN, 0.2f);
		this.addBiomeVariant(GOTBiomeVariant.ORCHARD_ORANGE, 0.2f);
		this.addBiomeVariant(GOTBiomeVariant.ORCHARD_LEMON, 0.2f);
		this.addBiomeVariant(GOTBiomeVariant.ORCHARD_LIME, 0.2f);
		this.addBiomeVariant(GOTBiomeVariant.ORCHARD_OLIVE, 0.2f);
		this.addBiomeVariant(GOTBiomeVariant.ORCHARD_ALMOND, 0.2f);
		this.addBiomeVariant(GOTBiomeVariant.ORCHARD_PLUM, 0.2f);
		this.addBiomeVariant(GOTBiomeVariant.ORCHARD_DATE, 0.2f);
		decorator.affix(new GOTStructureBraavosCity(this, 1.0f));
		invasionSpawns.addInvasion(GOTInvasions.VOLANTIS, GOTEventSpawner.EventChance.UNCOMMON);
		invasionSpawns.addInvasion(GOTInvasions.PENTOS, GOTEventSpawner.EventChance.UNCOMMON);
		SpawnListContainer[] container0 = new SpawnListContainer[2];
		container0[0] = GOTBiomeSpawnList.entry(GOTSpawnList.BRAAVOS_CIVILIAN, 10).setSpawnChance(GOTBiome.SPAWN);
		container0[1] = GOTBiomeSpawnList.entry(GOTSpawnList.BRAAVOS_MILITARY, 4).setSpawnChance(GOTBiome.SPAWN);
		npcSpawnList.newFactionList(10).add(container0);
		SpawnListContainer[] container1 = new SpawnListContainer[1];
		container1[0] = GOTBiomeSpawnList.entry(GOTSpawnList.VOLANTIS_MILITARY, 10).setSpawnChance(GOTBiome.CONQUEST_SPAWN);
		npcSpawnList.newFactionList(0).add(container1);
		SpawnListContainer[] container2 = new SpawnListContainer[1];
		container2[0] = GOTBiomeSpawnList.entry(GOTSpawnList.PENTOS_MILITARY, 10).setSpawnChance(GOTBiome.CONQUEST_SPAWN);
		npcSpawnList.newFactionList(0).add(container2);
		GOTStructureBraavosCity town = new GOTStructureBraavosCity(this, 0.0f).setIsTown();
		town.affix(GOTWaypoint.Braavos, 0, -1, 2);
		decorator.affix(town);
		
	}

	@Override
	public GOTAchievement getBiomeAchievement() {
		return GOTAchievement.VISIT_BRAAVOS;
	}

	@Override
	public MusicRegion getBiomeMusic() {
		return GOTBiomeMusic.ESSOS.getSubregion("braavos");
	}

}
