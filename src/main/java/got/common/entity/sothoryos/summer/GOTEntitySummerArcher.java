package got.common.entity.sothoryos.summer;

import got.common.database.GOTItems;
import got.common.entity.ai.GOTEntityAIRangedAttack;
import got.common.entity.other.GOTEntityNPC;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntitySummerArcher extends GOTEntitySummerWarrior {
	public GOTEntitySummerArcher(World world) {
		super(world);
		canBeMarried = false;
		spawnRidingHorse = false;
	}

	@Override
	public EntityAIBase createSummerAttackAI() {
		return new GOTEntityAIRangedAttack(this, 1.25, 30, 50, 16.0f);
	}

	@Override
	public void dropFewItems(boolean flag, int i) {
		super.dropFewItems(flag, i);
		dropNPCArrows(i);
	}

	@Override
	public void onAttackModeChange(GOTEntityNPC.AttackMode mode, boolean mounted) {
		if (mode == GOTEntityNPC.AttackMode.IDLE) {
			setCurrentItemOrArmor(0, npcItemsInv.getIdleItem());
		} else {
			setCurrentItemOrArmor(0, npcItemsInv.getRangedWeapon());
		}
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		data = super.onSpawnWithEgg(data);
		npcItemsInv.setRangedWeapon(new ItemStack(GOTItems.essosBow));
		npcItemsInv.setIdleItem(npcItemsInv.getRangedWeapon());
		return data;
	}
}