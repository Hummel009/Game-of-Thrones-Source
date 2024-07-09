package got.common.world.biome.westeros;

import got.client.sound.GOTMusicRegion;
import got.common.world.biome.GOTBiome;
import got.common.world.map.GOTBezierType;
import got.common.world.structure.other.*;

public abstract class GOTBiomeWesterosBase extends GOTBiome {
	protected GOTBiomeWesterosBase(int i, boolean major) {
		super(i, major);
		biomeMusic = GOTMusicRegion.WESTEROS.getSubregion(biomeName);
		wallBlock = GOTBezierType.WALL_ICE;
		wallTop = 150;
	}

	protected void setupRuinedStructures(boolean sand) {
		decorator.addStructure(new GOTStructureRuinedHouse(false), 2000);
		decorator.addStructure(new GOTStructureBurntHouse(false), 2000);
		decorator.addStructure(new GOTStructureRottenHouse(false), 4000);
		if (sand) {
			decorator.addStructure(new GOTStructureStoneRuin.RuinSandstone(1, 4), 400);
		} else {
			decorator.addStructure(new GOTStructureStoneRuin.RuinStone(1, 4), 400);
			decorator.addStructure(new GOTStructureSmallStoneRuin(false), 500);
		}
	}
}