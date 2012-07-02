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
			}

		// TODO other methods to draw other map features here

		public static byte getID(int rgb)
			{
				if (rgb == GRASS_MAP.getRGB())
					{
						int rand = Tools.randInt(1, 4);
						switch (rand)
							{
							case (1):
								return GRASS_1;
							case (2):
								return GRASS_2;
							case (3):
								return GRASS_3;
							case (4):
								return GRASS_4;
							}
					}
				return VOID_TILE;
			}

	}
