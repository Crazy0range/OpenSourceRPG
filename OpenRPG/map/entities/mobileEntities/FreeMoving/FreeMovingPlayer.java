package OpenRPG.map.entities.mobileEntities.FreeMoving;

import java.awt.Color;
import java.awt.Graphics;

import OpenRPG.utils.Camera;
import TroysCode.Constants;
import TroysCode.Tools;
import TroysCode.hub;

public class FreeMovingPlayer extends FreeMovingEntity implements Constants
	{

		public FreeMovingPlayer(int mapX, int mapY, byte[] data)
			{
				super(mapX, mapY, data);
				// TODO Auto-generated constructor stub
			}

		@Override
		public void tick_()
			{
				// TODO Auto-generated method stub

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
		protected void calculateMovement()
			{
				// TODO Auto-generated method stub

			}

		public final void keyPressed(int keyCode)
			{
				switch (keyCode)
					{

					case (KEY_UP):
						facing = NORTH;
						break;
					case (KEY_DOWN):
						facing = SOUTH;
						break;
					case (KEY_LEFT):
						facing = WEST;
						break;
					case (KEY_RIGHT):
						facing = EAST;
						break;
					case (KEY_DELETE):
						this.damageEntity(Tools.randPercent());
						break;
					}
			}

		public final void keyReleased(int keyCode)
			{
			}

		@Override
		protected void healthFullyDepleted()
			{
				// TODO Auto-generated method stub

			}
	}
