package OpenRPG.utils;

import java.io.Serializable;

import OpenRPG.map.Region;
import OpenRPG.map.entities.Entity;
import TroysCode.Constants;

public class Camera implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		public float x;
		public float y;

		public int screenXBound;
		public int screenYBound;

		public Camera()
			{
				this.screenXBound = (Region.xSize - 1) * Constants.TILE_SIZE;
				this.screenYBound = (Region.ySize - 1) * Constants.TILE_SIZE;
			}

		public void setLocation(Entity e)
			{
				x = e.getLocX() - 375;
				y = e.getLocY() - 262.5f;

				if (x < 0)
					x = 0;
				else if (x > screenXBound - 800)
					x = screenXBound - 800;

				if (y < 0)
					y = 0;
				else if (y > screenYBound - 600)
					y = screenYBound - 600;
			}

		public void setLocation(double newX, double newY)
			{
				x = (float) newX;
				y = (float) newY;

				if (x < 50)
					x = 50;
				else if (x > screenXBound - 800)
					x = screenXBound - 800;

				if (y < 50)
					y = 50;
				else if (y > screenYBound - 600)
					y = screenYBound - 600;
			}
	}
