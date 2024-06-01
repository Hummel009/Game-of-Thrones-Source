package got.common.entity.essos.tyrosh;

import got.common.database.GOTCapes;
import got.common.database.GOTItems;
import got.common.database.GOTShields;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityTyroshSoldier extends GOTEntityTyroshLevyman {
	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntityTyroshSoldier(World world) {
		super(world);
		spawnRidingHorse = rand.nextInt(10) == 0;
		cape = GOTCapes.TYROSH;
		shield = GOTShields.TYROSH;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData entityData = super.onSpawnWithEgg(data);
		int i = rand.nextInt(9);
		switch (i) {
			case 0:
				npcItemsInv.setMeleeWeapon(new ItemStack(GOTItems.essosHammer));
				break;
			case 1:
				npcItemsInv.setMeleeWeapon(new ItemStack(GOTItems.essosPike));
				break;
			case 2:
				npcItemsInv.setMeleeWeapon(new ItemStack(GOTItems.essosPolearm));
				break;
			default:
				npcItemsInv.setMeleeWeapon(new ItemStack(GOTItems.essosSword));
				break;
		}
		if (rand.nextInt(3) == 0) {
			npcItemsInv.setMeleeWeaponMounted(new ItemStack(GOTItems.essosPike));
		} else {
			npcItemsInv.setMeleeWeaponMounted(npcItemsInv.getMeleeWeapon());
		}
		if (rand.nextInt(5) == 0) {
			npcItemsInv.setSpearBackup(npcItemsInv.getMeleeWeapon());
			npcItemsInv.setMeleeWeapon(new ItemStack(GOTItems.essosSpear));
		}
		npcItemsInv.setIdleItem(npcItemsInv.getMeleeWeapon());
		npcItemsInv.setIdleItemMounted(npcItemsInv.getMeleeWeaponMounted());
		setCurrentItemOrArmor(1, new ItemStack(GOTItems.tyroshBoots));
		setCurrentItemOrArmor(2, new ItemStack(GOTItems.tyroshLeggings));
		setCurrentItemOrArmor(3, new ItemStack(GOTItems.tyroshChestplate));
		setCurrentItemOrArmor(4, new ItemStack(GOTItems.tyroshHelmet));
		return entityData;
	}
}