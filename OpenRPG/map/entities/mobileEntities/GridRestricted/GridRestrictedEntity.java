package OpenRPG.map.entities.mobileEntities.GridRestricted;

import java.awt.Graphics;

import OpenRPG.map.entities.Entity;
import OpenRPG.utils.Camera;
import TroysCode.Constants;
import TroysCode.hub;

public abstract class GridRestrictedEntity extends Entity implements Constants
	{
		protected int distanceToTravel = 0;

		protected byte dirToMove = NONE;

		public byte facing = NORTH;

		protected float speed = Constants.TILE_SIZE / (60f / 3f);

		public GridRestrictedEntity(int mapX, int mapY, byte[] data)
			{
				super(mapX, mapY, data);
			}

		public final void tick()
			{
				frame += 0.1;
				if (frame > 4)
					frame = 0;

				tick_();

				calculateMovement();
				processMovement();
			}

		public abstract void tick_();

		public abstract void render(Graphics g, Camera cam);

		protected abstract void calculateMovement();

		protected void processMovement()
			{
				if (distanceToTravel == 0)
					{
						if (dirToMove == NORTH)
							{
								if (facing == NORTH)
									move(NORTH);
								else
									facing = NORTH;
							}
						else if (dirToMove == SOUTH)
							{
								if (facing == SOUTH)
									move(SOUTH);
								else
									facing = SOUTH;
							}
						else if (dirToMove == EAST)
							{
								if (facing == EAST)
									move(EAST);
								else
									facing = EAST;
							}
						else if (dirToMove == WEST)
							{
								if (facing == WEST)
									move(WEST);
								else
									facing = WEST;
							}
					}

				else if (distanceToTravel > 0)
					{
						distanceToTravel -= speed;

						if (facing == NORTH)
							{
								setLocY(getLocY() - speed);
								if (distanceToTravel <= 0)
									{
										hub.game.region.getTile(mapX, mapY).removeEntity(this);
										mapY--;
										hub.game.region.getTile(mapX, mapY).addEntity(this);
										setLocY(mapY * Constants.TILE_SIZE);
										distanceToTravel = 0;
									}
							}
						else if (facing == SOUTH)
							{
								setLocY(getLocY() + speed);
								if (distanceToTravel <= 0)
									{
										hub.game.region.getTile(mapX, mapY).removeEntity(this);
										mapY++;
										hub.game.region.getTile(mapX, mapY).addEntity(this);
										setLocY(mapY * Constants.TILE_SIZE);
										distanceToTravel = 0;
									}
							}
						else if (facing == EAST)
							{
								setLocX(getLocX() + speed);
								if (distanceToTravel <= 0)
									{
										hub.game.region.getTile(mapX, mapY).removeEntity(this);
										mapX++;
										hub.game.region.getTile(mapX, mapY).addEntity(this);
										setLocX(mapX * Constants.TILE_SIZE);
										distanceToTravel = 0;
									}
							}
						else if (facing == WEST)
							{
								setLocX(getLocX() - speed);
								if (distanceToTravel <= 0)
									{
										hub.game.region.getTile(mapX, mapY).removeEntity(this);
										mapX--;
										hub.game.region.getTile(mapX, mapY).addEntity(this);
										setLocX(mapX * Constants.TILE_SIZE);
										distanceToTravel = 0;
									}
							}
					}
			}

		protected final void move(byte dir)
			{
				if (distanceToTravel == 0)
					{
						if (dir == NORTH)
							{
								facing = NORTH;
								if (!hub.game.region.isTileOccupied(mapX, mapY - 1))
									distanceToTravel = 50;
							}
						else if (dir == SOUTH)
							{
								facing = SOUTH;
								if (!hub.game.region.isTileOccupied(mapX, mapY + 1))
									distanceToTravel = 50;
							}
						else if (dir == WEST)
							{
								facing = WEST;
								if (!hub.game.region.isTileOccupied(mapX - 1, mapY))
									distanceToTravel = 50;
							}
						else if (dir == EAST)
							{
								facing = EAST;
								if (!hub.game.region.isTileOccupied(mapX + 1, mapY))
									distanceToTravel = 50;
							}
					}
			}

		protected final void removeFromRegion()
			{
				hub.game.region.getTile(mapX, mapY).removeEntity(this);
			}
	}
