package got.common.entity.essos.ghiscar;

import got.common.database.GOTAchievement;
import got.common.database.GOTItems;
import got.common.database.GOTShields;
import got.common.entity.ai.GOTEntityAINearestAttackableTargetBasic;
import got.common.item.other.GOTItemRobes;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityGhiscarGladiator extends GOTEntityGhiscarMan {
	private static final ItemStack[] WEAPONS = {new ItemStack(GOTItems.essosSword), new ItemStack(GOTItems.essosDagger), new ItemStack(GOTItems.essosDaggerPoisoned), new ItemStack(GOTItems.essosHammer)};
	private static final int[] TURBAN_COLORS = {1643539, 6309443, 7014914, 7809314, 5978155};

	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntityGhiscarGladiator(World world) {
		super(world);
		addTargetTasks(true);
		shield = GOTShields.GHISCAR;
		int target = addTargetTasks(false);
		targetTasks.addTask(target + 1, new GOTEntityAINearestAttackableTargetBasic(this, GOTEntityGhiscarGladiator.class, 0, true));
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(NPC_RANGED_ACCURACY).setBaseValue(0.75);
	}

	@Override
	public void dropFewItems(boolean flag, int i) {
	}

	@Override
	public float getAlignmentBonus() {
		return 0.0f;
	}

	@Override
	public GOTAchievement getKillAchievement() {
		return GOTAchievement.killGladiator;
	}

	@Override
	public String getSpeechBank(EntityPlayer entityplayer) {
		return "standard/special/gladiator";
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData entityData = super.onSpawnWithEgg(data);
		int i = rand.nextInt(WEAPONS.length);
		npcItemsInv.setMeleeWeapon(WEAPONS[i].copy());
		npcItemsInv.setIdleItem(npcItemsInv.getMeleeWeapon());
		setCurrentItemOrArmor(1, new ItemStack(GOTItems.ghiscarBoots));
		setCurrentItemOrArmor(2, new ItemStack(GOTItems.ghiscarLeggings));
		setCurrentItemOrArmor(3, new ItemStack(GOTItems.ghiscarChestplate));
		ItemStack turban = new ItemStack(GOTItems.robesHelmet);
		int robeColor = TURBAN_COLORS[rand.nextInt(TURBAN_COLORS.length)];
		GOTItemRobes.setRobesColor(turban, robeColor);
		setCurrentItemOrArmor(4, turban);
		return entityData;
	}

	@Override
	public void setupNPCGender() {
		familyInfo.setMale(true);
	}
}