package OpenRPG.map.entities.mobileEntities.GridRestricted;

import java.awt.Color;
import java.awt.Graphics;

import OpenRPG.utils.Camera;
import TroysCode.Constants;
import TroysCode.Tools;
import TroysCode.hub;

public class  GridRestrictedPlayer extends  GridRestrictedEntity implements Constants
	{
		public byte dirKey = KEY_NONE;
		public boolean keyHeld = false;
		public byte timeHeld = 0;
		public byte delay = 0;
		public static final byte HOLDTIME = 10;
		public static final byte DELAYTIME = 10;

		public  GridRestrictedPlayer(int mapX, int mapY, byte[] data)
			{
				super(mapX, mapY, data);
			}

		@Override
		public void tick_()
			{
				if (delay > 0)
					delay--;

				if (timeHeld < HOLDTIME)
					timeHeld++;
				else
					keyHeld = true;
			}

		@Override
		public void render(Graphics g, Camera cam)
			{
				int x = Math.round(getLocX() - cam.x);
				int y = Math.round(getLocY() - cam.y);

				g.drawImage(hub.images.player[(int) frame][facing], x, y, hub.renderer);

				if (health != 100)
					{
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 50, 6);
						g.setColor(Color.RED);
						g.fillRect(x, y, (int) (0.48 * health), 4);
					}
			}

		@Override
		protected final void calculateMovement()
			{
				if (distanceToTravel == 0)
					{
						if (dirKey == KEY_NONE && keyHeld)
							dirToMove = NONE;

						else if (dirKey == KEY_UP)
							{
								if (keyHeld)
									dirToMove = NORTH;
								else
									facing = NORTH;
							}

						else if (dirKey == KEY_DOWN)
							{
								if (keyHeld)
									dirToMove = SOUTH;
								else
									facing = SOUTH;
							}

						else if (dirKey == KEY_RIGHT)
							{
								if (keyHeld)
									dirToMove = EAST;
								else
									facing = EAST;
							}

						else if (dirKey == KEY_LEFT)
							{
								if (keyHeld)
									dirToMove = WEST;
								else
									facing = WEST;
							}
					}
			}

		@Override
		protected void healthFullyDepleted()
			{
				timeHeld = 0;
				delay = 0;

				removeFromRegion();
				
				health = 100;
				mapX = 1;
				mapY = 1;
				setLocX(mapX * Constants.TILE_SIZE);
				setLocY(mapY * Constants.TILE_SIZE);
				hub.game.region.getTile(mapX, mapY).addEntity(this);
			}

		public final void keyPressed(int keyCode)
			{
				switch (keyCode)
					{
					case (KEY_LEFT):
						if ( dirKey == KEY_NONE &&  delay == 0 &&  facing != WEST)
							{
								 keyHeld = false;
								 timeHeld = 0;
							}
						 dirKey = KEY_LEFT;
						break;

					case (KEY_UP):
						if ( dirKey == KEY_NONE &&  delay == 0 &&  facing != NORTH)
							{
								 keyHeld = false;
								 timeHeld = 0;
							}
						 dirKey = KEY_UP;
						break;

					case (KEY_RIGHT):
						if ( dirKey == KEY_NONE &&  delay == 0 &&  facing != EAST)
							{
								 keyHeld = false;
								 timeHeld = 0;
							}
						 dirKey = KEY_RIGHT;
						break;

					case (KEY_DOWN):
						if ( dirKey == KEY_NONE &&  delay == 0 &&  facing != SOUTH)
							{
								 keyHeld = false;
								 timeHeld = 0;
							}
						 dirKey = KEY_DOWN;
						break;

					case (KEY_SPACE):
						break;

					case (KEY_DELETE):
						 damageEntity(Tools.randPercent());
						break;
					}
			}

		public final void keyReleased(int keyCode)
			{
				switch (keyCode)
					{
					case (KEY_LEFT):
						if ( dirKey == KEY_LEFT)
							 dirKey = KEY_NONE;
						if ( keyHeld)
							 delay =   DELAYTIME;
						break;

					case (KEY_UP):
						if ( dirKey == KEY_UP)
							 dirKey = KEY_NONE;
						if ( keyHeld)
							 delay =   DELAYTIME;
						break;

					case (KEY_RIGHT):
						if ( dirKey == KEY_RIGHT)
							 dirKey = KEY_NONE;
						if ( keyHeld)
							 delay =   DELAYTIME;
						break;

					case (KEY_DOWN):
						if ( dirKey == KEY_DOWN)
							 dirKey = KEY_NONE;
						if ( keyHeld)
							 delay =   DELAYTIME;
						break;

					case (KEY_SPACE):
						break;
					}
			}
	}
