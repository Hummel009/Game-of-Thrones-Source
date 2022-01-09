package got.common.entity.essos.gold;

import got.common.database.*;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityGoldenWarrior extends GOTEntityGoldenMan {
	public GOTEntityGoldenWarrior(World world) {
		super(world);
		canBeMarried = false;
		addTargetTasks(true);
		spawnRidingHorse = false;
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(npcRangedAccuracy).setBaseValue(0.75);
	}

	@Override
	public float getAlignmentBonus() {
		return 2.0f;
	}

	@Override
	public String getNPCName() {
		return familyInfo.getName();
	}

	@Override
	public String getSpeechBank(EntityPlayer entityplayer) {
		if (isFriendly(entityplayer)) {
			if (hiredNPCInfo.getHiringPlayer() == entityplayer) {
				return "standart/civilized/hired_soldier";
			}
			return "standart/civilized/usual_friendly";
		}
		return "standart/civilized/usual_hostile";
	}

	@Override
	public int getTotalArmorValue() {
		return 15;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		super.onSpawnWithEgg(data);
		npcItemsInv.setMeleeWeapon(new ItemStack(GOTRegistry.westerosSword));
		npcItemsInv.setIdleItem(npcItemsInv.getMeleeWeapon());
		setCurrentItemOrArmor(1, new ItemStack(GOTRegistry.goldBoots));
		setCurrentItemOrArmor(2, new ItemStack(GOTRegistry.goldLeggings));
		setCurrentItemOrArmor(3, new ItemStack(GOTRegistry.goldChestplate));
		setCurrentItemOrArmor(4, new ItemStack(GOTRegistry.goldHelmet));
		return data;
	}

	@Override
	public void setupNPCGender() {
		familyInfo.setMale(true);
	}

	@Override
	public void setupNPCName() {
		familyInfo.setName(GOTNames.getEssosName(rand, familyInfo.isMale()));
	}
}
