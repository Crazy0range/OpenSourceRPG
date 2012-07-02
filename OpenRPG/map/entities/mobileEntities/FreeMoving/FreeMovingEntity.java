package OpenRPG.map.entities.mobileEntities.FreeMoving;

import java.awt.Graphics;

import OpenRPG.map.entities.Entity;
import OpenRPG.utils.Camera;
import TroysCode.Constants;
import TroysCode.hub;

public abstract class FreeMovingEntity extends Entity implements Constants
	{
		public byte facing = SOUTH;

		protected float speed = Constants.TILE_SIZE / (60f / 3f);

		public FreeMovingEntity(int mapX, int mapY, byte[] data)
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

		private final void processMovement()
			{
				/*
				 * refers to which tiles an entity is in, as it may overlap
				 * multiple tiles, which affects collision detection.
				 */
				final boolean VERTICALLY_EXACT = getLocY() % 50 == 0;
				final boolean HORIZONTALLY_EXACT = getLocX() % 50 == 0;

				int state = VERTICALLY_EXACT ? (HORIZONTALLY_EXACT ? V_EXACT_H_EXACT : V_EXACT_H_OVERLAPPING) : (HORIZONTALLY_EXACT ? V_OVERLAPPING_H_EXACT
						: V_OVERLAPPING_H_OVERLAPPING);

				/*
				 * 
				 */
				switch (state)
				{
				case (V_EXACT_H_EXACT):
					;
					break;
				case (V_EXACT_H_OVERLAPPING):
					;
					break;
				case (V_OVERLAPPING_H_EXACT):
					;
					break;
				case (V_OVERLAPPING_H_OVERLAPPING):
					;
					break;
				}


				if (hub.input.getKeyState(KEY_UP) == true)
					{
					}
				else if (hub.input.getKeyState(KEY_DOWN) == true)
					{
					}
				if (hub.input.getKeyState(KEY_LEFT) == true)
					{
					}
				else if (hub.input.getKeyState(KEY_RIGHT) == true)
					{
					}

				mapX = (int) (getLocX() / Constants.TILE_SIZE);
				mapY = (int) (getLocY() / Constants.TILE_SIZE);
				hub.game.region.getTile(mapX, mapY).addEntity(this);
			}
	}
