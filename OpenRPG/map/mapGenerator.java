package OpenRPG.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import TroysCode.Constants;
import TroysCode.Tools;

public class mapGenerator implements mapGenConst, Constants
	{
		private static BufferedImage map;
		private static Graphics g;

		private static int width;
		private static int height;

		public static BufferedImage newMap()
			{
				width = Region.xSize;
				height = Region.ySize;
				createImage();
				drawToImage();

				return map;
			}

		private static void createImage()
			{
				map = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				g = map.getGraphics();
			}

		private static void drawToImage()
			{
				// First set as all grass
				g.setColor(GRASS_MAP);
				g.fillRect(0, 0, width, height);

				drawMountains();

				drawForests();

				//startBlock();

				// call last
				ringFence();
			}

		private static void drawMountains()
			{

				for (int mountainRanges = 0; mountainRanges < NUM_MOUNTAINS; mountainRanges++)
					{
						int centerX = Tools.randInt(-50, 1050);
						int centerY = Tools.randInt(-50, 1050);

						int diameter = Tools.randInt(BASE_MOUNTAIN_SIZE - RANGE_MOUNTAIN_SIZE, BASE_MOUNTAIN_SIZE + RANGE_MOUNTAIN_SIZE);

						for (int i = 0; i < Tools.randInt(MOUNTAIN_LOBES_BASE - MOUNTAIN_LOBES_RANGE, MOUNTAIN_LOBES_BASE + MOUNTAIN_LOBES_RANGE); i++)
							{
								int offsetX = centerX + Tools.randInt(-MOUNTAIN_LOBES_SPREAD, MOUNTAIN_LOBES_SPREAD);
								int offsetY = centerY + Tools.randInt(-MOUNTAIN_LOBES_SPREAD, MOUNTAIN_LOBES_SPREAD);

								float offsetDiameter = diameter + Tools.randInt(-15, 15);

								float mountainDensity = BASE_MOUNTAIN_DENSITY + Tools.randInt(1, (int) BASE_MOUNTAIN_DENSITY);

								for (int x = (int) (offsetX - offsetDiameter); x < offsetX + offsetDiameter; x++)
									for (int y = (int) (offsetY - offsetDiameter); y < offsetY + offsetDiameter; y++)
										if (x >= 0 && y >= 0 && x < width && y < height)
											if (Tools.randFloat(0, offsetDiameter * mountainDensity) < offsetDiameter
													- Tools.getVectorLength(offsetX, offsetY, x, y))
												if (map.getRGB(x, y) == COBBLESTONE_MAP.getRGB())
													{
														g.setColor(BOULDER_MAP);
														g.fillRect(x, y, 1, 1);
													}
												else
													{
														g.setColor(COBBLESTONE_MAP);
														g.fillRect(x, y, 1, 1);
													}
											else if (Tools.getVectorLength(offsetX, offsetY, x, y) < offsetDiameter && map.getRGB(x, y) == GRASS_MAP.getRGB())
												if (Tools.randPercent() + 30 > (Tools.getVectorLength(offsetX, offsetY, x, y) / (offsetDiameter / 100)))
													{
														g.setColor(STONEGRASS_MAP);
														g.fillRect(x, y, 1, 1);
													}
							}
					}
			}

		private static void drawForests()
			{
				// First cover map in a few tree's
				g.setColor(TREE_MAP);

				for (int x = 0; x < width; x++)
					for (int y = 0; y < height; y++)
						if (Tools.randPercent() == 100 && Tools.randPercent() > 50)
							g.fillRect(x, y, 1, 1);

				// Second draw a few denser patches with solid patches in the
				// middle
				for (int forests = 0; forests < NUM_FORESTS; forests++)
					{
						int centerX = Tools.randInt(-50, 1050);
						int centerY = Tools.randInt(-50, 1050);

						int diameter = Tools.randInt(BASE_FOREST_SIZE - RANGE_FOREST_SIZE, BASE_FOREST_SIZE + RANGE_FOREST_SIZE);

						for (int i = 0; i < Tools.randInt(FOREST_LOBES_BASE - FOREST_LOBES_RANGE, FOREST_LOBES_BASE + FOREST_LOBES_RANGE); i++)
							{
								int offsetX = centerX + Tools.randInt(-FOREST_LOBES_SPREAD, FOREST_LOBES_SPREAD);
								int offsetY = centerY + Tools.randInt(-FOREST_LOBES_SPREAD, FOREST_LOBES_SPREAD);

								int offsetDiameter = diameter + Tools.randInt(-15, 15);

								float forestDensity = BASE_FOREST_DENSITY + Tools.randInt(1, (int) BASE_FOREST_DENSITY);

								for (int x = offsetX - offsetDiameter; x < offsetX + offsetDiameter; x++)
									for (int y = offsetY - offsetDiameter; y < offsetY + offsetDiameter; y++)
										if (x >= 0 && y >= 0 && x < width && y < height)
											if (Tools.randFloat(0, offsetDiameter * forestDensity) < offsetDiameter
													- Tools.getVectorLength(offsetX, offsetY, x, y))
												if (map.getRGB(x, y) == GRASS_MAP.getRGB())
													g.fillRect(x, y, 1, 1);
							}
					}
			}

		// creates a little starting area
		private static void startBlock()
			{
				g.setColor(GRASS_MAP);
				g.fillRect(495, 495, 10, 10);
				g.setColor(BOULDER_MAP);
				g.drawRect(495, 495, 10, 10);
			}

		// Creates inpenetrable barrier around edge of map
		private static void ringFence()
			{
				g.setColor(DEBUG_MAP);
				g.drawRect(0, 0, width - 1, height - 1);
			}

		// TODO other methods to draw other map features here

		public static byte[] getID(int rgb)
			{
				int grassNum = Tools.randInt(1, 4);

				if (rgb == GRASS_MAP.getRGB())
					{
						if (grassNum == 1)
							return GRASS;
						else if (grassNum == 2)
							return GRASS1;
						else if (grassNum == 3)
							return GRASS2;
						else if (grassNum == 4)
							return GRASS3;
					}
				else if (rgb == BOULDER_MAP.getRGB())
					{
						if (grassNum == 1)
							return BOULDER;
						else if (grassNum == 2)
							return BOULDER2;
						else if (grassNum == 3)
							return BOULDER3;
						else if (grassNum == 4)
							return BOULDER4;
					}
				else if (rgb == TREE_MAP.getRGB())
					{
						if (grassNum == 1)
							return TREE;
						else if (grassNum == 2)
							return TREE1;
						else if (grassNum == 3)
							return TREE2;
						else if (grassNum == 4)
							return TREE3;
					}
				return TILE_DEBUG;
			}

	}
