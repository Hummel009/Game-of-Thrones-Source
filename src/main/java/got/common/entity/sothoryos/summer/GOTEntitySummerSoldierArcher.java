package got.common.entity.sothoryos.summer;

import got.common.entity.ai.GOTEntityAIRangedAttack;
import got.common.entity.other.utils.GOTEntityUtils;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntitySummerSoldierArcher extends GOTEntitySummerSoldier {
	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntitySummerSoldierArcher(World world) {
		super(world);
	}

	@Override
	public void onAttackModeChange(AttackMode mode, boolean mounted) {
		GOTEntityUtils.setupRangedAttackModeChange(this, mode);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData entityData = super.onSpawnWithEgg(data);

		npcItemsInv.setRangedWeapon(new ItemStack(Items.bow));
		npcItemsInv.setIdleItem(npcItemsInv.getRangedWeapon());

		return entityData;
	}

	@Override
	public EntityAIBase getAttackAI() {
		return new GOTEntityAIRangedAttack(this, 1.25, 30, 50, 20.0f);
	}

	@Override
	public void dropFewItems(boolean flag, int i) {
		super.dropFewItems(flag, i);
		dropNPCArrows(i);
	}
}