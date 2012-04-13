package OpenRPG.entities;

import java.awt.Graphics;
import java.io.Serializable;

import TroysCode.Tools;
import TroysCode.hub;

public class SimpleMob extends Mob implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private byte moving = NONE;

		public SimpleMob(int x, int y, byte[] id)
			{
				super(x, y, id);
			}

		@Override
		public void tick()
			{
				think();

				move();
				setFrame();
			}

		@Override
		public void render(Graphics g)
			{
				g.drawImage(hub.images.prey[getFrame()][facing], Math.round(x - hub.save.cam.x), Math.round(y - hub.save.cam.y), hub.renderer);
			}

		@Override
		protected void interact()
			{
			}

		private void think()
			{
				byte percent = (byte) Tools.randPercent();

				if (action == STANDING && percent > 99)
					moving = (byte) (Tools.randBool() ? NONE : Tools.randInt(NONE, LEFT));
				else if (action == STANDING && percent > 98)
					facing = (byte) Tools.randInt(NORTH, WEST);
				else if (action == MOVING && percent > 97)
					moving = NONE;
				if (percent == 100)
					interact();
			}

		@Override
		protected void move()
			{
				if (action == MOVING)
					{
						if (facing == NORTH)
							{
								y -= speed;
								if (mapY * 50 == y)
									{
										hub.save.region.getTile(mapX, mapY + 1).mob = null;
										action = STANDING;
									}
							}
						else if (facing == SOUTH)
							{
								y += speed;
								if (mapY * 50 == y)
									{
										hub.save.region.getTile(mapX, mapY - 1).mob = null;
										action = STANDING;
									}
							}
						else if (facing == EAST)
							{
								x += speed;
								if (mapX * 50 == x)
									{
										hub.save.region.getTile(mapX - 1, mapY).mob = null;
										action = STANDING;
									}
							}
						else if (facing == WEST)
							{
								x -= speed;
								if (mapX * 50 == x)
									{
										hub.save.region.getTile(mapX + 1, mapY).mob = null;
										action = STANDING;
									}
							}
					}

				if (action == STANDING)
					{
						if (moving == NONE)
							;
						else if (moving == UP)
							{
								if (hub.save.region.isFree(mapX, mapY - 1) && facing == NORTH)
									{
										action = MOVING;
										facing = NORTH;
										mapY--;
										hub.save.region.getTile(mapX, mapY).mob = this;
									}
								else
									facing = NORTH;
							}
						else if (moving == DOWN)
							{
								if (hub.save.region.isFree(mapX, mapY + 1) && facing == SOUTH)
									{
										action = MOVING;
										facing = SOUTH;
										mapY++;
										hub.save.region.getTile(mapX, mapY).mob = this;
									}
								else
									facing = SOUTH;
							}
						else if (moving == RIGHT)
							{
								if (hub.save.region.isFree(mapX + 1, mapY) && facing == EAST)
									{
										action = MOVING;
										facing = EAST;
										mapX++;
										hub.save.region.getTile(mapX, mapY).mob = this;
									}
								else
									facing = EAST;
							}
						else if (moving == LEFT)
							{
								if (hub.save.region.isFree(mapX - 1, mapY) && facing == WEST)
									{
										action = MOVING;
										facing = WEST;
										mapX--;
										hub.save.region.getTile(mapX, mapY).mob = this;
									}
								else
									facing = WEST;
							}
					}
			}
	}