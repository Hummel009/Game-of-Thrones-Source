package got.common.world.structure.westeros.stormlands;

import com.google.common.math.IntMath;
import got.common.entity.westeros.stormlands.*;
import got.common.world.structure.other.GOTStructureBase;
import got.common.world.structure.westeros.common.GOTStructureWesterosMarketStall;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public abstract class GOTStructureStormlandsMarketStall extends GOTStructureWesterosMarketStall {
	private static final Class<? extends GOTStructureBase>[] STALLS = new Class[]{Goldsmith.class, Miner.class, Lumber.class, Mason.class, Brewer.class, Flowers.class, Butcher.class, Fish.class, Farmer.class, Blacksmith.class, Baker.class};

	protected GOTStructureStormlandsMarketStall(boolean flag) {
		super(flag);
		kingdom = Kingdom.STORMLANDS;
	}

	public static GOTStructureBase getRandomStall(Random random, boolean flag) {
		try {
			Class<? extends GOTStructureBase> cls = STALLS[random.nextInt(STALLS.length)];
			return cls.getConstructor(Boolean.TYPE).newInstance(flag);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Baker extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Baker(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsBaker(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int k2 = Math.abs(k1);
			if (k2 % 2 == 0) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 1);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 12);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Blacksmith extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Blacksmith(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsBlacksmith(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			if (i2 == Math.abs(k1)) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 15);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 7);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Brewer extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Brewer(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsBrewer(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			if (i2 % 2 == 0) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 12);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 4);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Butcher extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Butcher(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsButcher(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			int k2 = Math.abs(k1);
			if (i2 == 2 || k2 == 2) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 6);
			} else if (i2 == 1 || k2 == 1) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 14);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 0);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Farmer extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Farmer(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsFarmer(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int k2;
			int i2 = Math.abs(i1);
			if (IntMath.mod(i2 + (k2 = Math.abs(k1)), 2) == 0) {
				if (Integer.signum(i1) != -Integer.signum(k1) && i2 + k2 == 2) {
					setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 4);
				} else {
					setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 13);
				}
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 12);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Fish extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Fish(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsFishmonger(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			int k2 = Math.abs(k1);
			if (i2 % 2 == 0) {
				if (k2 == 2) {
					setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 0);
				} else {
					setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 3);
				}
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 11);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Flowers extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Flowers(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsFlorist(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			if (i2 == Math.abs(k1)) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 4);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 13);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Goldsmith extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Goldsmith(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsGoldsmith(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			if (i2 == Math.abs(k1)) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 15);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 7);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Lumber extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Lumber(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsLumberman(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			int k2 = Math.abs(k1);
			if ((i2 == 2 || k2 == 2) && IntMath.mod(i2 + k2, 2) == 0) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 13);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 12);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Mason extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Mason(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsMason(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			int k2 = Math.abs(k1);
			if (i2 == 2 || k2 == 2 || i2 != 1 && k2 != 1) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 7);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 8);
			}
		}
	}

	@SuppressWarnings("WeakerAccess")
	public static class Miner extends GOTStructureStormlandsMarketStall {
		@SuppressWarnings("unused")
		public Miner(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityStormlandsMan createTrader(World world) {
			return new GOTEntityStormlandsMiner(world);
		}

		@Override
		public void generateRoof(World world, int i1, int j1, int k1) {
			int i2 = Math.abs(i1);
			if (IntMath.mod(i2 + Math.abs(k1), 2) == 0) {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 14);
			} else {
				setBlockAndMetadata(world, i1, j1, k1, Blocks.wool, 5);
			}
		}
	}
}