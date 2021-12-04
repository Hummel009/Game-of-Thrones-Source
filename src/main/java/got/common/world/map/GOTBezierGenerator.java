package got.common.world.map;

import java.util.Random;

import com.google.common.math.IntMath;

import got.common.world.biome.GOTBiome;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GOTBezierGenerator {
	public static boolean generateBezier(World world, Random rand, int i, int k, GOTBiome biome, Block[] blocks, byte[] metadata, double[] heightNoise) {
		int chunkX = i & 0xF;
		int chunkZ = k & 0xF;
		int xzIndex = chunkX * 16 + chunkZ;
		int ySize = blocks.length / 256;
		GOTBezierType bezierType = biome.getRoadBlock();
		GOTBezierType.BridgeType bridgeType = biome.getBridgeBlock();
		GOTBezierType wallType = biome.getWallBlock();
		int wallTop = biome.getWallTop();

		if (GOTBezier.isBezierAt(i, k) && GOTBezier.isWall()) {
			int index;
			int j;
			for (j = wallTop; j > 62; --j) {
				index = xzIndex * ySize + j;
				boolean isTop = j == wallTop;
				GOTBezierType.BezierBlock wallblock = wallType.getBlock(rand, biome, isTop, false);
				blocks[index] = wallblock.block;
				metadata[index] = (byte) wallblock.meta;
			}
			return true;
		}
		if (GOTBezier.isBezierAt(i, k) && !GOTBezier.isWall()) {
			int index;
			int j;
			int indexLower;
			int bezierTop = 0;
			int bridgeBase = 0;
			boolean bridge = false;
			boolean bridgeSlab = false;
			for (j = ySize - 1; j > 0; --j) {
				index = xzIndex * ySize + j;
				Block block = blocks[index];
				if (block.isOpaqueCube()) {
					bezierTop = j;
					bridge = false;
					break;
				}
				if (!block.getMaterial().isLiquid()) {
					continue;
				}
				bridgeBase = bezierTop = j + 1;
				int maxBridgeTop = j + 6;
				float bridgeHeight = 0.0f;
				for (int j1 = j - 1; j1 > 0 && blocks[indexLower = xzIndex * ySize + j1].getMaterial().isLiquid(); --j1) {
					bridgeHeight += 0.5f;
				}
				int bridgeHeightInt = (int) Math.floor(bridgeHeight);
				bezierTop += bridgeHeightInt;
				if ((bezierTop = Math.min(bezierTop, maxBridgeTop)) >= maxBridgeTop) {
					bridgeSlab = true;
				} else {
					float bridgeHeightR = bridgeHeight - bridgeHeightInt;
					if (bridgeHeightR < 0.5f) {
						bridgeSlab = true;
					}
				}
				bridge = true;
				break;
			}
			if (bridge) {
				GOTBezierType.BezierBlock bridgeBlock = bridgeType.getBlock(rand, false);
				GOTBezierType.BezierBlock bridgeBlockSlab = bridgeType.getBlock(rand, true);
				GOTBezierType.BezierBlock bridgeEdge = bridgeType.getEdge(rand);
				GOTBezierType.BezierBlock bridgeFence = bridgeType.getFence(rand);
				boolean fence = GOTBezierGenerator.isFenceAt(i, k);
				int index2 = xzIndex * ySize + bezierTop;
				if (fence) {
					boolean pillar = GOTBezierGenerator.isPillarAt(i, k);
					if (pillar) {
						int pillarIndex;
						for (int j2 = bezierTop + 4; j2 > 0 && !blocks[pillarIndex = xzIndex * ySize + j2].isOpaqueCube(); --j2) {
							if (j2 >= bezierTop + 4) {
								blocks[pillarIndex] = bridgeFence.block;
								metadata[pillarIndex] = (byte) bridgeFence.meta;
								continue;
							}
							if (j2 >= bezierTop + 3) {
								blocks[pillarIndex] = bridgeBlock.block;
								metadata[pillarIndex] = (byte) bridgeBlock.meta;
								continue;
							}
							blocks[pillarIndex] = bridgeEdge.block;
							metadata[pillarIndex] = (byte) bridgeEdge.meta;
						}
					} else {
						int support;
						blocks[index2] = bridgeEdge.block;
						metadata[index2] = (byte) bridgeEdge.meta;
						int indexUpper = index2 + 1;
						blocks[indexUpper] = bridgeFence.block;
						metadata[indexUpper] = (byte) bridgeFence.meta;
						if (bezierTop > bridgeBase) {
							int indexLower2 = index2 - 1;
							blocks[indexLower2] = bridgeEdge.block;
							metadata[indexLower2] = (byte) bridgeEdge.meta;
						}
						if (bezierTop - 1 > (support = bridgeBase + 2)) {
							int indexSupport = xzIndex * ySize + support;
							blocks[indexSupport] = bridgeFence.block;
							metadata[indexSupport] = (byte) bridgeFence.meta;
						}
					}
				} else {
					if (bridgeSlab) {
						blocks[index2] = bridgeBlockSlab.block;
						metadata[index2] = (byte) bridgeBlockSlab.meta;
					} else {
						blocks[index2] = bridgeBlock.block;
						metadata[index2] = (byte) bridgeBlock.meta;
					}
					if (bezierTop > bridgeBase) {
						indexLower = index2 - 1;
						blocks[indexLower] = bridgeBlock.block;
						metadata[indexLower] = (byte) bridgeBlock.meta;
					}
				}
			} else {
				for (j = bezierTop; j > bezierTop - 4 && j > 0; --j) {
					index = xzIndex * ySize + j;
					float repair = bezierType.getRepair();
					if (rand.nextFloat() >= repair) {
						continue;
					}
					boolean isTop = j == bezierTop;
					boolean isSlab = false;
					if (isTop && j >= 63) {
						double avgNoise = (heightNoise[index] + heightNoise[index + 1]) / 2.0;
						isSlab = avgNoise < 0.0;
					}
					GOTBezierType.BezierBlock bezierblock = bezierType.getBlock(rand, biome, isTop, isSlab);
					blocks[index] = bezierblock.block;
					metadata[index] = (byte) bezierblock.meta;
				}
			}
			return true;
		}
		if (bezierType.hasFlowers()) {
			int index;
			int i1;
			int bezierTop = 0;
			for (int j = ySize - 1; j > 0; --j) {
				index = xzIndex * ySize + j;
				Block block = blocks[index];
				if (!block.isOpaqueCube()) {
					continue;
				}
				bezierTop = j;
				break;
			}
			boolean adjBezier = false;
			block5: for (i1 = -2; i1 <= 2; ++i1) {
				for (int k1 = -2; k1 <= 2; ++k1) {
					if (i1 == 0 && k1 == 0 || !GOTBezier.isBezierAt(i + i1, k + k1)) {
						continue;
					}
					adjBezier = true;
					break block5;
				}
			}
			if (adjBezier) {
				index = xzIndex * ySize + bezierTop + 1;
				BiomeGenBase.FlowerEntry flower = biome.getRandomFlower(rand);
				blocks[index] = flower.block;
				metadata[index] = (byte) flower.metadata;
			} else {
				adjBezier = false;
				block7: for (i1 = -3; i1 <= 3; ++i1) {
					for (int k1 = -3; k1 <= 3; ++k1) {
						if (Math.abs(i1) <= 2 && Math.abs(k1) <= 2 || !GOTBezier.isBezierAt(i + i1, k + k1)) {
							continue;
						}
						adjBezier = true;
						break block7;
					}
				}
				if (adjBezier) {
					index = xzIndex * ySize + bezierTop + 1;
					blocks[index] = Blocks.leaves;
					metadata[index] = 4;
				}
			}
			return true;
		}
		return false;
	}

	public static boolean isBridgeEdgePillar(int i, int k) {
		return GOTBezier.isBezierAt(i, k) && GOTBezierGenerator.isFenceAt(i, k) && GOTBezierGenerator.isPillarAt(i, k);
	}

	public static boolean isFenceAt(int i, int k) {
		for (int i1 = -1; i1 <= 1; ++i1) {
			for (int k1 = -1; k1 <= 1; ++k1) {
				if (i1 == 0 && k1 == 0 || GOTBezier.isBezierAt(i + i1, k + k1)) {
					continue;
				}
				return true;
			}
		}
		return false;
	}

	public static boolean isPillarAt(int i, int k) {
		int pRange = 8;
		int xmod = IntMath.mod(i, pRange);
		if (IntMath.mod(xmod + IntMath.mod(k, pRange), pRange) == 0) {
			return !GOTBezierGenerator.isBridgeEdgePillar(i + 1, k - 1) && !GOTBezierGenerator.isBridgeEdgePillar(i + 1, k + 1);
		}
		return false;
	}
}
