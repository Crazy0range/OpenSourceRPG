package OpenRPG.utils;

import java.io.Serializable;

import OpenRPG.map.Region;
import TroysCode.hub;

public class Camera implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		public float x = 1;
		public float y = 1;

		public int screenXBound;
		public int screenYBound;

		public Camera()
			{
				this.screenXBound = (Region.xSize - 1) * 50;
				this.screenYBound = (Region.ySize - 1) * 50;
			}

		public void updatePos()
			{
				x = hub.save.player.x - 375;
				y = hub.save.player.y - 262.5f;

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
