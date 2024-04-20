package got.common.entity.westeros.wildling;

import got.common.database.GOTAchievement;
import got.common.database.GOTItems;
import got.common.entity.other.GOTEntityGiantBase;
import got.common.faction.GOTFaction;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class GOTEntityGiant extends GOTEntityGiantBase {
	public GOTEntityGiant(World world) {
		super(world);
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0);
	}

	@Override
	public GOTAchievement getKillAchievement() {
		return GOTAchievement.killGiant;
	}

	@Override
	public void dropFewItems(boolean flag, int i) {
		dropItem(Items.beef, 10);
		dropItem(GOTItems.fur, 10);
	}

	@Override
	public float getAlignmentBonus() {
		return 5.0f;
	}

	@Override
	public GOTFaction getFaction() {
		return GOTFaction.WILDLING;
	}

	@Override
	public String getSpeechBank(EntityPlayer entityplayer) {
		if (isFriendly(entityplayer)) {
			return "standard/special/giant_friendly";
		}
		return "standard/special/giant_hostile";
	}
}