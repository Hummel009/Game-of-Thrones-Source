package got.common.world.structure.westeros.dragonstone;

import com.google.common.math.IntMath;
import got.common.entity.westeros.dragonstone.*;
import got.common.world.structure.other.GOTStructureBase;
import got.common.world.structure.westeros.common.GOTStructureWesterosMarketStall;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public abstract class GOTStructureDragonstoneMarketStall extends GOTStructureWesterosMarketStall {
	private static final Class<? extends GOTStructureBase>[] STALLS = new Class[]{Goldsmith.class, Miner.class, Lumber.class, Mason.class, Brewer.class, Flowers.class, Butcher.class, Fish.class, Farmer.class, Blacksmith.class, Baker.class};

	protected GOTStructureDragonstoneMarketStall(boolean flag) {
		super(flag);
		kingdom = Kingdom.DRAGONSTONE;
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

	private static class Baker extends GOTStructureDragonstoneMarketStall {
		protected Baker(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneBaker(world);
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

	private static class Blacksmith extends GOTStructureDragonstoneMarketStall {
		protected Blacksmith(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneBlacksmith(world);
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

	private static class Brewer extends GOTStructureDragonstoneMarketStall {
		protected Brewer(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneBrewer(world);
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

	private static class Butcher extends GOTStructureDragonstoneMarketStall {
		protected Butcher(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneButcher(world);
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

	private static class Farmer extends GOTStructureDragonstoneMarketStall {
		protected Farmer(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneFarmer(world);
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

	private static class Fish extends GOTStructureDragonstoneMarketStall {
		protected Fish(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneFishmonger(world);
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

	private static class Flowers extends GOTStructureDragonstoneMarketStall {
		protected Flowers(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneFlorist(world);
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

	private static class Goldsmith extends GOTStructureDragonstoneMarketStall {
		protected Goldsmith(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneGoldsmith(world);
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

	private static class Lumber extends GOTStructureDragonstoneMarketStall {
		protected Lumber(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneLumberman(world);
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

	private static class Mason extends GOTStructureDragonstoneMarketStall {
		protected Mason(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneMason(world);
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

	private static class Miner extends GOTStructureDragonstoneMarketStall {
		protected Miner(boolean flag) {
			super(flag);
		}

		@Override
		public GOTEntityDragonstoneMan createTrader(World world) {
			return new GOTEntityDragonstoneMiner(world);
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
