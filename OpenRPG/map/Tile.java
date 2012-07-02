package OpenRPG.map;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import OpenRPG.map.entities.Entity;
import TroysCode.Constants;
import TroysCode.hub;

public class Tile implements Constants, Serializable
	{
		private static final long serialVersionUID = 1L;

		public byte id;
		private int mapX;
		private int mapY;

		private ArrayList<Entity> entities = new ArrayList<Entity>();

		public Tile(byte id, int mapX, int mapY)
			{
				this.id = id;
				this.mapX = mapX;
				this.mapY = mapY;
			}

		public synchronized Entity[] getEntities()
			{
				Entity[] entitiesCopy = new Entity[entities.size()];
				entities.toArray(entitiesCopy);
				return entitiesCopy;
			}

		public synchronized void addEntity(Entity e)
			{
				if (!entities.contains(e))
					{
						entities.add(e);
					}
			}

		public synchronized void removeEntity(Entity e)
			{
				entities.remove(e);
			}

		public final boolean isOccupied()
			{
				Rectangle tileRect = new Rectangle(mapX * TILE_SIZE, mapY * TILE_SIZE, TILE_SIZE, TILE_SIZE);

				for (int x = -1; x < 2; x++)
					for (int y = -1; y < 2; y++)
						for (Entity entity : hub.game.region.getTile(x, y).getEntities())
							if (entity.getData()[ENTITY_STATE] == SOLID && tileRect.intersects(entity.getCollisionRect()))
								return true;

				return false;
			}
	}
