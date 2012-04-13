package OpenRPG.entities;

import java.awt.Graphics;
import java.io.Serializable;

import OpenRPG.Game;
import TroysCode.hub;

public class Player extends Mob implements Serializable
	{
		private static final long serialVersionUID = 1L;

		public Player(int mapX, int mapY)
			{
				super(mapX, mapY, MOB_DEBUG);
				addItem(new Item(SWORD));
				addItem(new Item(PICK));
				addItem(new Item(AXE));
			}

		@Override
		public void render(Graphics g)
			{
				g.drawImage(hub.images.player[getFrame()][facing], Math.round(x - hub.save.cam.x), Math.round(y - hub.save.cam.y), hub.renderer);
			}

		@Override
		public void tick()
			{
				move();
				setFrame();

				interact();
			}

		@Override
		protected void interact()
			{
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
										action = STANDING;
										hub.save.region.getTile(mapX, mapY + 1).mob = null;
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
						if (hub.game.key == Game.NONE)
							;
						else if (hub.game.key == Game.UP)
							{
								if (hub.game.held && hub.save.region.isFree(mapX, mapY - 1) && facing == NORTH)
									{
										action = MOVING;
										facing = NORTH;
										mapY--;
										hub.save.region.getTile(mapX, mapY).mob = this;
									}
								else
									facing = NORTH;
							}
						else if (hub.game.key == Game.DOWN)
							{
								if (hub.game.held && hub.save.region.isFree(mapX, mapY + 1) && facing == SOUTH)
									{
										action = MOVING;
										facing = SOUTH;
										mapY++;
										hub.save.region.getTile(mapX, mapY).mob = this;
									}
								else
									facing = SOUTH;
							}
						else if (hub.game.key == Game.RIGHT)
							{
								if (hub.game.held && hub.save.region.isFree(mapX + 1, mapY) && facing == EAST)
									{
										action = MOVING;
										facing = EAST;
										mapX++;
										hub.save.region.getTile(mapX, mapY).mob = this;
									}
								else
									facing = EAST;
							}
						else if (hub.game.key == Game.LEFT)
							{
								if (hub.game.held && hub.save.region.isFree(mapX - 1, mapY) && facing == WEST)
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
