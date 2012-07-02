package OpenRPG;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import OpenRPG.map.Region;
import OpenRPG.map.entities.Entity;
import OpenRPG.map.entities.immobileEntities.Rock;
import OpenRPG.map.entities.mobileEntities.FreeMoving.FreeMovingPlayer;
import OpenRPG.map.entities.mobileEntities.GridRestricted.GridRestrictedEntity;
import OpenRPG.map.entities.mobileEntities.GridRestricted.GridRestrictedPlayer;
import OpenRPG.utils.Camera;
import TroysCode.Constants;
import TroysCode.RenderableObject;
import TroysCode.Tools;
import TroysCode.hub;
import TroysCode.T.TScrollEvent;

public class Game extends RenderableObject implements Constants
	{
		private static final long serialVersionUID = 1L;

		private Camera cam = new Camera();
		public Region region = new Region();
		public FreeMovingPlayer freeMovingPlayer = new FreeMovingPlayer(10, 5, PLAYER_DATA);
		public GridRestrictedPlayer gridRestrictedPlayer = new GridRestrictedPlayer(10, 5, PLAYER_DATA);
		private ArrayList<Entity> entities = new ArrayList<Entity>();

		@Override
		protected void initiate()
			{
				region.getTile(gridRestrictedPlayer.getMapX(), gridRestrictedPlayer.getMapY()).addEntity(gridRestrictedPlayer);
			}

		@Override
		protected void refresh()
			{
			}

		@Override
		protected void tick()
			{
				for (Entity e : getEntities())
					if (Tools.getVectorLengthSquared(e.getMapLocation(), gridRestrictedPlayer.getMapLocation()) < 400)
						{
							if (e.exists)
								e.tick();
							else
								entities.remove(e);
						}

				gridRestrictedPlayer.tick();
				cam.setLocation(gridRestrictedPlayer);
			}

		@Override
		protected void renderObject(Graphics g)
			{
				/*
				 * render the terrain, tile by tile, and any entities contained
				 * within them. This includes the player.
				 */
				region.render(g, cam);

				// render the gui
				g.drawImage(hub.images.GUI[0], 0, 475, hub.renderer);
			}

		public final synchronized Entity[] getEntities()
			{
				Entity[] mobs = new Entity[entities.size()];
				entities.toArray(mobs);
				return mobs;
			}

		public final synchronized void addEntity(Entity e)
			{
				if (region.isTileOccupied(e.getMapX(), e.getMapY()))
					entities.add(e);
			}

		public final synchronized void removeGridMob(GridRestrictedEntity e)
			{
				entities.remove(e);
			}

		@Override
		protected void mousePressed(MouseEvent event)
			{
				int x = (int) ((event.getX() + cam.x) / 50);
				int y = (int) ((event.getY() + cam.y) / 50);
				if (!region.isTileOccupied(x, y))
					{
						Entity e = new Rock(x, y, ROCK_DATA);
						addEntity(e);
						region.getTile(x, y).addEntity(e);
					}
			}

		@Override
		protected void mouseReleased(MouseEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mouseDragged(MouseEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mouseMoved(MouseEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mouseWheelScrolled(MouseWheelEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void actionPerformed(ActionEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void keyPressed(KeyEvent event)
			{
				if (event.getKeyCode() == 27)
					changeRenderableObject(hub.mainMenu);
				
				gridRestrictedPlayer.keyPressed(event.getKeyCode());
			}

		@Override
		protected void keyReleased(KeyEvent event)
			{
				gridRestrictedPlayer.keyReleased(event.getKeyCode());
			}

		@Override
		protected void keyTyped(KeyEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mouseClicked(MouseEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mouseEntered(MouseEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mouseExited(MouseEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void programGainedFocus(WindowEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void programLostFocus(WindowEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		public void tScrollBarScrolled(TScrollEvent event)
			{
				// TODO Auto-generated method stub

			}
	}