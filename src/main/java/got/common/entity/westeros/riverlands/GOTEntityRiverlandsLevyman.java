package got.common.entity.westeros.riverlands;

import got.common.database.GOTItems;
import got.common.entity.other.utils.GOTEntityUtils;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityRiverlandsLevyman extends GOTEntityRiverlandsMan {
	private static final ItemStack[] WEAPONS = {new ItemStack(GOTItems.westerosSword), new ItemStack(GOTItems.westerosHammer), new ItemStack(GOTItems.westerosPike), new ItemStack(Items.iron_sword), new ItemStack(Items.iron_axe), new ItemStack(GOTItems.ironBattleaxe), new ItemStack(GOTItems.ironPike), new ItemStack(GOTItems.bronzeSword), new ItemStack(GOTItems.bronzeAxe), new ItemStack(GOTItems.bronzeBattleaxe)};

	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntityRiverlandsLevyman(World world) {
		super(world);
		addTargetTasks(true);
	}

	@Override
	public float getAlignmentBonus() {
		return 2.0f;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData entityData = super.onSpawnWithEgg(data);

		int i = rand.nextInt(WEAPONS.length);
		npcItemsInv.setMeleeWeapon(WEAPONS[i].copy());
		npcItemsInv.setIdleItem(npcItemsInv.getMeleeWeapon());

		GOTEntityUtils.setupWesterosLevymanArmor(this, rand);

		return entityData;
	}

	@Override
	public void setupNPCGender() {
		familyInfo.setMale(true);
	}
}
