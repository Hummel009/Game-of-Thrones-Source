package got.common.world.biome.ulthos;

import got.common.database.GOTAchievement;
import got.common.database.GOTSpawnList;
import got.common.world.biome.variant.GOTBiomeVariant;
import got.common.world.spawning.GOTBiomeSpawnList;
import got.common.world.spawning.GOTSpawnListContainer;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.Collection;

public class GOTBiomeUlthosFrost extends GOTBiomeUlthosTaiga {
	public GOTBiomeUlthosFrost(int i, boolean major) {
		super(i, major);
		setupFrostFauna();
		biomeVariants.clear();
		biomeVariants.add(GOTBiomeVariant.HILLS, 1.0f);
		topBlock = Blocks.snow;
		Collection<GOTSpawnListContainer> c0 = new ArrayList<>();
		c0.add(GOTBiomeSpawnList.entry(GOTSpawnList.WALKERS_BLIZZARD, 10).setSpawnChance(CONQUEST_SPAWN / 2));
		npcSpawnList.newFactionList(10).add(c0);
	}

	@Override
	public GOTAchievement getBiomeAchievement() {
		return GOTAchievement.enterUlthosFrost;
	}

	@Override
	public boolean getEnableRiver() {
		return false;
	}
}