package OpenRPG.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import OpenRPG.map.entities.Entity;
import OpenRPG.utils.Camera;
import TroysCode.Constants;
import TroysCode.hub;

public class Region implements Constants, Serializable
	{
		private static final long serialVersionUID = 1L;

		private Tile[][] tiles;

		public static final int xSize = 100;
		public static final int ySize = 100;

		public Region()
			{
				createRegion();
			}

		public void createRegion()
			{
				BufferedImage map = mapGenerator.newMap();

				tiles = new Tile[xSize][ySize];
				for (int x = 0; x < xSize; x++)
					for (int y = 0; y < ySize; y++)
						{
							tiles[x][y] = new Tile(mapGenerator.getID(map.getRGB(x, y)), x, y);
						}
			}

		public void render(Graphics g, Camera cam)
			{
				int tileX = (int) cam.x / Constants.TILE_SIZE;
				int tileY = (int) cam.y / Constants.TILE_SIZE;

				for (int x = tileX; x < tileX + 17; x++)
					for (int y = tileY; y < tileY + 11; y++)
						{
							g.drawImage(hub.images.terrain[tiles[x][y].id], Math.round((x * Constants.TILE_SIZE) - cam.x), Math.round((y * Constants.TILE_SIZE) - cam.y),
									hub.renderer);
							
							for (Entity e : tiles[x][y].getEntities())
								e.render(g, cam);

							if (hub.DEBUG)
								if (tiles[x][y].isOccupied())
									g.fillRect((int) (x * Constants.TILE_SIZE - cam.x), (int) (y * Constants.TILE_SIZE - cam.y), 40, 40);
						}
			}

		public synchronized Tile[][] getTiles()
			{
				return tiles;
			}

		public synchronized Tile getTile(int x, int y)
			{
				if (x < 0 || y < 0 || x >= xSize || y >= ySize)
					return new Tile(VOID_TILE, -1, -1);

				if (x == xSize || y == ySize)
					return new Tile(VOID_TILE, -1, -1);

				return tiles[x][y];
			}

		public boolean isTileOccupied(int x, int y)
			{
				if (x < 0 || y < 0 || x >= xSize - 1 || y >= ySize - 2)
					return true;

				return tiles[x][y].isOccupied();
			}
	}
