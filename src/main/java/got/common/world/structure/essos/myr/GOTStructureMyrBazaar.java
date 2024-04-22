package got.common.world.structure.essos.myr;

import got.common.database.GOTBlocks;
import got.common.database.GOTFoods;
import got.common.database.GOTItems;
import got.common.entity.essos.myr.*;
import got.common.world.structure.essos.common.GOTStructureEssosBazaar;
import got.common.world.structure.other.GOTStructureBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class GOTStructureMyrBazaar extends GOTStructureEssosBazaar {
	private static final Class<? extends GOTStructureBase>[] STALLS = new Class[]{Lumber.class, Mason.class, Fish.class, Baker.class, Goldsmith.class, Farmer.class, Blacksmith.class, Brewer.class, Miner.class, Florist.class, Butcher.class};

	public GOTStructureMyrBazaar(boolean flag) {
		super(flag);
		city = City.MYR;
	}

	@Override
	public Class<? extends GOTStructureBase>[] getStallClasses() {
		return STALLS;
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Baker extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Baker(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, 0, 1, 1, Blocks.furnace, 2);
			setBlockAndMetadata(world, -1, 1, 1, GOTBlocks.planks2, 2);
			setBlockAndMetadata(world, 1, 1, 1, GOTBlocks.planks2, 2);
			placePlateItem(world, random, -1, 2, 1, GOTBlocks.ceramicPlate, new ItemStack(Items.bread, 1 + random.nextInt(3)), true);
			placePlateItem(world, random, 1, 2, 1, GOTBlocks.ceramicPlate, new ItemStack(GOTItems.oliveBread, 1 + random.nextInt(3)), true);
			placeFlowerPot(world, random.nextBoolean() ? -2 : 2, 2, 0, getRandomFlower(world, random));
			GOTEntityMyrBaker trader = new GOTEntityMyrBaker(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Blacksmith extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Blacksmith(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, -1, 1, 1, Blocks.anvil, 3);
			placeWeaponRack(world, -2, 2, 0, 1, getRandomWeapon(random));
			placeWeaponRack(world, 2, 2, 0, 3, getRandomWeapon(random));
			GOTEntityMyrBlacksmith trader = new GOTEntityMyrBlacksmith(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Brewer extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Brewer(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, -1, 1, 1, GOTBlocks.stairsCedar, 6);
			setBlockAndMetadata(world, -1, 2, 1, GOTBlocks.barrel, 2);
			setBlockAndMetadata(world, 1, 1, 1, GOTBlocks.stairsCedar, 6);
			setBlockAndMetadata(world, 1, 2, 1, GOTBlocks.barrel, 2);
			placeMug(world, random, -2, 2, 0, 1, GOTFoods.ESSOS_DRINK);
			placeMug(world, random, 2, 2, 0, 1, GOTFoods.ESSOS_DRINK);
			GOTEntityMyrBrewer trader = new GOTEntityMyrBrewer(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Butcher extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Butcher(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, 0, 1, 1, Blocks.furnace, 2);
			placeKebabStand(world, random, 0, 2, 1, GOTBlocks.kebabStand, 3);
			placePlateItem(world, random, -2, 2, 0, GOTBlocks.ceramicPlate, new ItemStack(GOTItems.muttonRaw, 1 + random.nextInt(3), 0), true);
			placePlateItem(world, random, 2, 2, 0, GOTBlocks.ceramicPlate, new ItemStack(GOTItems.camelRaw, 1 + random.nextInt(3), 1), true);
			GOTEntityMyrButcher trader = new GOTEntityMyrButcher(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Farmer extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Farmer(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, -1, 1, 1, Blocks.cauldron, 3);
			setBlockAndMetadata(world, 1, 1, 1, Blocks.hay_block, 0);
			setBlockAndMetadata(world, -1, 1, -1, Blocks.hay_block, 0);
			placePlateItem(world, random, -2, 2, 0, GOTBlocks.woodPlate, new ItemStack(GOTItems.orange, 1 + random.nextInt(3), 0), true);
			placePlateItem(world, random, 2, 2, 0, GOTBlocks.woodPlate, new ItemStack(GOTItems.lettuce, 1 + random.nextInt(3), 1), true);
			GOTEntityMyrSlaver trader = new GOTEntityMyrSlaver(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Fish extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Fish(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, 1, 1, 1, Blocks.cauldron, 3);
			setBlockAndMetadata(world, -1, 1, -1, Blocks.sponge, 0);
			placePlateItem(world, random, -2, 2, 0, GOTBlocks.ceramicPlate, new ItemStack(Items.fish, 1 + random.nextInt(3), 0), true);
			placePlateItem(world, random, 2, 2, 0, GOTBlocks.ceramicPlate, new ItemStack(Items.fish, 1 + random.nextInt(3), 1), true);
			GOTEntityMyrFishmonger trader = new GOTEntityMyrFishmonger(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Florist extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Florist(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			placeFlowerPot(world, -2, 2, 0, getRandomFlower(world, random));
			placeFlowerPot(world, 2, 2, 0, getRandomFlower(world, random));
			setBlockAndMetadata(world, -1, 0, 1, Blocks.grass, 0);
			setBlockAndMetadata(world, -1, 1, 1, GOTBlocks.doubleFlower, 3);
			setBlockAndMetadata(world, -1, 2, 1, GOTBlocks.doubleFlower, 11);
			setBlockAndMetadata(world, 1, 1, 1, Blocks.grass, 0);
			plantFlower(world, random, 1, 2, 1);
			setBlockAndMetadata(world, 1, 1, 0, Blocks.trapdoor, 12);
			setBlockAndMetadata(world, 0, 1, 1, Blocks.trapdoor, 15);
			GOTEntityMyrFlorist trader = new GOTEntityMyrFlorist(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Goldsmith extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Goldsmith(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, -1, 1, -1, GOTBlocks.goldBars, 0);
			setBlockAndMetadata(world, 1, 1, -1, GOTBlocks.goldBars, 0);
			setBlockAndMetadata(world, -1, 1, 1, GOTBlocks.goldBars, 0);
			setBlockAndMetadata(world, 1, 1, 1, GOTBlocks.goldBars, 0);
			setBlockAndMetadata(world, random.nextBoolean() ? -1 : 1, 2, -1, GOTBlocks.birdCage, 2);
			setBlockAndMetadata(world, random.nextBoolean() ? -1 : 1, 2, 1, GOTBlocks.birdCage, 3);
			GOTEntityMyrGoldsmith trader = new GOTEntityMyrGoldsmith(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Lumber extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Lumber(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, -1, 1, 1, GOTBlocks.wood4, 10);
			setBlockAndMetadata(world, 1, 1, 1, GOTBlocks.wood4, 2);
			setBlockAndMetadata(world, 1, 2, 1, GOTBlocks.wood4, 2);
			placeFlowerPot(world, -2, 2, 0, new ItemStack(GOTBlocks.sapling4, 1, 2));
			placeFlowerPot(world, 2, 2, 0, new ItemStack(GOTBlocks.sapling8, 1, 3));
			GOTEntityMyrLumberman trader = new GOTEntityMyrLumberman(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Mason extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Mason(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, -1, 1, 1, GOTBlocks.brick1, 1);
			setBlockAndMetadata(world, -1, 2, 1, GOTBlocks.slabSingle1, 3);
			setBlockAndMetadata(world, 1, 1, 1, GOTBlocks.brick1, 5);
			placeWeaponRack(world, 1, 2, 1, 2, new ItemStack(GOTItems.bronzePickaxe));
			GOTEntityMyrMason trader = new GOTEntityMyrMason(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}

	@SuppressWarnings({"WeakerAccess", "unused"})
	public static class Miner extends GOTStructureBase {
		@SuppressWarnings({"WeakerAccess", "unused"})
		public Miner(boolean flag) {
			super(flag);
		}

		@Override
		public boolean generate(World world, Random random, int i, int j, int k, int rotation) {
			setOriginAndRotation(world, i, j, k, rotation, 0);
			setBlockAndMetadata(world, -1, 1, 1, GOTBlocks.oreTin, 0);
			setBlockAndMetadata(world, -1, 2, 1, GOTBlocks.oreCopper, 0);
			setBlockAndMetadata(world, 1, 1, 1, GOTBlocks.oreCopper, 0);
			placeWeaponRack(world, 1, 2, 1, 2, new ItemStack(GOTItems.bronzePickaxe));
			GOTEntityMyrMiner trader = new GOTEntityMyrMiner(world);
			spawnNPCAndSetHome(trader, world, 0, 1, 0, 4);
			return true;
		}
	}
}