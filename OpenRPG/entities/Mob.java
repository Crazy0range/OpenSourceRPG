package OpenRPG.entities;

import java.awt.Graphics;
import java.io.Serializable;

import OpenRPG.map.Region;
import TroysCode.Constants;
import TroysCode.Tools;
import TroysCode.hub;

public class Mob implements Constants, Serializable
	{
		private static final long serialVersionUID = 1L;

		public byte[] meta;

		public byte health = 10;
		public Item[] inventory = new Item[14];
		public Item item = new Item(NO_ITEM);

		public int mapX;
		public int mapY;

		public float x;
		public float y;
		protected float speed = 50 / (60 / 3);// tileSize / (fraction of second)
												// only 2, 3 & 6 work

		public byte facing = SOUTH;
		public byte action = STANDING;

		protected byte actionDuration = 0;

		public byte frame = 0;

		public Mob(int x, int y, byte[] id)
			{
				this.meta = id;

				this.mapX = x;
				this.mapY = y;
				this.x = x * 50;
				this.y = y * 50;

				if (!hub.save.region.isFree(mapX, mapY))
					findFreeTile();

				clearInventory();

				hub.save.region.getTile(mapX, mapY).mob = this;
			}

		private void reSpawn()
			{
				hub.save.region.getTile(mapX, mapY).mob = null;

				findFreeTile();

				this.health = 10;

				clearInventory();

				hub.save.region.getTile(mapX, mapY).mob = this;
			}

		public void tick()
			{
			}

		public void render(Graphics g)
			{
			}

		protected void move()
			{
			}

		protected void interact()
			{
			}

		public void damage(Item item)
			{
				health -= item.getDamageDealt(this);
				if (health <= 0)
					reSpawn();
			}

		public byte attack(Mob mob)
			{
				mob.damage(item);
				return 10;
			}

		protected byte getFrame()
			{
				return (byte) ((frame / 2) / 15);
			}

		protected void setFrame()
			{
				if (action == MOVING || frame != 0)
					{
						frame++;
						if (frame == 120)
							frame = 0;
					}
				else
					frame = 0;
			}

		public void clearInventory()
			{
				for (int i = 0; i < 14; i++)
					inventory[i] = new Item(NO_ITEM);
			}

		public void addItem(Item item)
			{
				for (int i = 0; i < 14; i++)
					if (inventory[i].meta[ID] == item.meta[ID] && inventory[i].stack < item.meta[STACK])
						{
							inventory[i].stack++;
							return;
						}
					else if (inventory[i].meta[ID] == NO_ITEM[ID])
						{
							inventory[i] = item;
							return;
						}
				// TODO add sound here
			}

		public void useUpItem(byte i)
			{
				if (inventory[i].stack > 0)
					{
						inventory[i].stack--;
						return;
					}
				else
					{
						inventory[i] = new Item(NO_ITEM);
						return;
					}
				// TODO add sound here
			}

		// Finds the nearest empty space to spawn,
		// If all spaces are null, calls respawn again
		protected void findFreeTile()
			{
				boolean found = false;

				while (!found)
					{
						this.mapX = Tools.randInt(1, Region.xSize - 2);
						this.mapY = Tools.randInt(1, Region.ySize - 2);
						this.x = x * 50;
						this.y = y * 50;

						if (!hub.save.region.isFree(mapX, mapY))
							for (int x = -5; x < 5; x++)
								for (int y = -5; y < 5; y++)
									if (!found)
										if (hub.save.region.isFree(mapX + x, mapY + y))
											{
												mapX += x;
												mapY += y;
												this.x = mapX * 50;
												this.y = mapY * 50;
												found = true;
											}
					}
				return;
			}
	}
