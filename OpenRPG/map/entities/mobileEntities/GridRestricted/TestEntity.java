package OpenRPG.map.entities.mobileEntities.GridRestricted;

import java.awt.Color;
import java.awt.Graphics;

import OpenRPG.utils.Camera;
import TroysCode.Tools;
import TroysCode.hub;

public class TestEntity extends GridRestrictedEntity
	{
		public TestEntity(int mapX, int mapY, byte[] data)
			{
				super(mapX, mapY, data);
			}

		@Override
		public void tick_()
			{
			}

		@Override
		public void render(Graphics g, Camera cam)
			{
				int x = Math.round(getLocX() - cam.x);
				int y = Math.round(getLocY() - cam.y);
				
				g.drawImage(hub.images.prey[(int) frame][facing], x, y, hub.renderer);
				if (health != 100)
					{
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 50, 4);
						g.setColor(Color.BLACK);
						g.fillRect(x, y, (int) (0.48 * health), 6);
					}
			}

		@Override
		protected final void calculateMovement()
			{
				if (distanceToTravel == 0 && Tools.randPercent() > 97)
					dirToMove = (byte) Tools.randInt(NORTH, WEST);
				else
					dirToMove = NONE;
			}

		@Override
		protected void healthFullyDepleted()
			{
				exists = false;
			}
	}
