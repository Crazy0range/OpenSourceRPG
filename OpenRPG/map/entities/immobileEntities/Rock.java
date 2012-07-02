package OpenRPG.map.entities.immobileEntities;

import java.awt.Graphics;

import OpenRPG.map.entities.Entity;
import OpenRPG.utils.Camera;
import TroysCode.hub;

public class Rock extends Entity
	{

	public Rock(int mapX, int mapY, byte[] data)
		{
			super(mapX, mapY, data);
			// TODO Auto-generated constructor stub
		}

	@Override
	public void tick()
		{
		}

	@Override
	public void render(Graphics g, Camera cam)
		{
			g.drawImage(hub.images.terrain[5], (int) (getLocX() - cam.x), (int) (getLocY() - cam.y), hub.renderer);
		}

	@Override
	protected void healthFullyDepleted()
		{
		}
	}
