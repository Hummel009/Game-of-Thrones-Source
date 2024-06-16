package got.common.entity.westeros.north;

import got.common.database.GOTChestContents;
import got.common.database.GOTItems;
import got.common.database.GOTNames;
import got.common.entity.animal.GOTEntityHorse;
import got.common.entity.other.GOTEntityHumanBase;
import got.common.entity.other.iface.GOTNPCMount;
import got.common.faction.GOTFaction;
import got.common.quest.GOTMiniQuestFactory;
import got.common.world.biome.GOTBiome;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GOTEntityNorthMan extends GOTEntityHumanBase implements GOTBiome.ImmuneToFrost {
	private static final ItemStack[] WEAPONS = {new ItemStack(GOTItems.westerosDagger), new ItemStack(GOTItems.ironDagger), new ItemStack(GOTItems.bronzeDagger), new ItemStack(Items.iron_axe), new ItemStack(GOTItems.bronzeAxe)};

	@SuppressWarnings({"WeakerAccess", "unused"})
	public GOTEntityNorthMan(World world) {
		super(world);
	}

	protected void setupFactionArmor(boolean noHelmet) {
		setCurrentItemOrArmor(1, new ItemStack(GOTItems.northBoots));
		setCurrentItemOrArmor(2, new ItemStack(GOTItems.northLeggings));
		setCurrentItemOrArmor(3, new ItemStack(GOTItems.northChestplate));
		setCurrentItemOrArmor(4, noHelmet ? null : new ItemStack(GOTItems.northHelmet));
	}

	protected void setupFactionArmorElite() {
		setCurrentItemOrArmor(1, new ItemStack(GOTItems.northguardBoots));
		setCurrentItemOrArmor(2, new ItemStack(GOTItems.northguardLeggings));
		setCurrentItemOrArmor(3, new ItemStack(GOTItems.northguardChestplate));
		setCurrentItemOrArmor(4, new ItemStack(GOTItems.northguardHelmet));
	}

	@Override
	public GOTNPCMount createMountToRide() {
		GOTEntityHorse horse = new GOTEntityHorse(worldObj);
		horse.setMountArmor(new ItemStack(GOTItems.westerosHorseArmor));
		return horse;
	}

	@Override
	public void dropFewItems(boolean flag, int i) {
		super.dropFewItems(flag, i);
		int bones = rand.nextInt(2) + rand.nextInt(i + 1);
		for (int l = 0; l < bones; ++l) {
			dropItem(Items.bone, 1);
		}
		if (rand.nextInt(6) == 0) {
			dropChestContents(GOTChestContents.NORTH, 1, 2 + i);
		}
	}

	@Override
	public float getAlignmentBonus() {
		return 1.0f;
	}

	@Override
	public GOTMiniQuestFactory getMiniQuestFactory() {
		return GOTMiniQuestFactory.NORTH;
	}

	@Override
	public GOTFaction getFaction() {
		return GOTFaction.NORTH;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		IEntityLivingData entityData = super.onSpawnWithEgg(data);

		int i = rand.nextInt(WEAPONS.length);
		npcItemsInv.setMeleeWeapon(WEAPONS[i].copy());
		npcItemsInv.setIdleItem(null);

		return entityData;
	}

	@Override
	public void setupNPCName() {
		familyInfo.setName(GOTNames.getWesterosName(rand, familyInfo.isMale()));
	}
}
