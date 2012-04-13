package OpenRPG.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import TroysCode.Constants;
import TroysCode.hub;


public class Region implements Constants, Serializable
	{
		private static final long serialVersionUID = 1L;

		private Tile[][] tiles;

		public static final int xSize = 1000;
		public static final int ySize = 1000;

		public Region()
			{
			}

		public void createRegion()
			{
				BufferedImage map = mapGenerator.newMap();

				tiles = new Tile[xSize][ySize];
				for (int x = 0; x < xSize; x++)
					for (int y = 0; y < ySize; y++)
						{
							tiles[x][y] = new Tile(mapGenerator.getID(map.getRGB(x, y)));
						}
			}

		public void renderRegion(Graphics g, float camX, float camY)
			{
				int tileX = (int) camX / 50;
				int tileY = (int) camY / 50;

				for (int x = tileX; x < tileX + 17; x++)
					for (int y = tileY; y < tileY + 11; y++)
						g.drawImage(hub.images.terrain[tiles[x][y].meta[X]][tiles[x][y].meta[Y]], Math.round((x * 50) - camX), Math.round((y * 50) - camY),
								hub.renderer);
			}

		public boolean isFree(int x, int y)
			{
				if (x < 0 || y < 0)
					return false;

				if (x >= xSize || y >= ySize)
					return false;

				if (tiles[x][y].meta[STATE] == SOLID)
					return false;

				if (tiles[x][y].mob != null)
					return false;

				return true;
			}

		public Tile getTile(int x, int y)
			{
				if (x < 0 || y < 0)
					return null;

				if (x == xSize || y == ySize)
					return null;

				return tiles[x][y];
			}
	}
