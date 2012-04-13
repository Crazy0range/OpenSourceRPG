package OpenRPG;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import OpenRPG.map.mapGenerator;
import OpenRPG.utils.Save;
import TroysCode.RenderableObject;
import TroysCode.Tools;
import TroysCode.hub;
import TroysCode.T.TButton;
import TroysCode.T.TScrollEvent;

public class MainMenu extends RenderableObject
	{
		private static final long serialVersionUID = 1L;

		private BufferedImage map = mapGenerator.newMap();
		
		private TButton newGameButton = new TButton(250, 15, hub.images.mainMenu[2]);
		private TButton helpButton = new TButton(250, 125, hub.images.mainMenu[3]);
		private TButton saveManagerButton = new TButton(250, 235, hub.images.mainMenu[4]);
		private TButton optionsButton = new TButton(250, 345, hub.images.mainMenu[5]);
		private TButton backButton = new TButton(250, 455, hub.images.mainMenu[6]);

		public MainMenu()
			{
			}

		@Override
		protected void initiate()
			{
				addTComponent(newGameButton);
				addTComponent(helpButton);
				addTComponent(saveManagerButton);
				addTComponent(optionsButton);
				addTComponent(backButton);
			}

		@Override
		public void renderObject(Graphics g)
			{
				g.drawImage(map, 0, -100, hub.renderer);
			}

		@Override
		protected void refresh()
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void tick()
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mousePressed(MouseEvent event)
			{
				// TODO Auto-generated method stub

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
		protected void mouseWheelMoved(MouseWheelEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void actionPerformed(ActionEvent event)
			{
				if (event.getSource() == newGameButton)// new game
					{
						hub.save = new Save();
						hub.save.newGame();
						hub.renderer.changeRenderableObject(hub.game);
					}

				if (event.getSource() == helpButton)// help
					{
						hub.renderer.changeRenderableObject(hub.help);
					}

				if (event.getSource() == saveManagerButton)// save/load
					{
						hub.renderer.changeRenderableObject(hub.saveManager);
					}

				if (event.getSource() == optionsButton)// options
					{
					}

				if (event.getSource() == backButton)// back
					{
						if (hub.save != null)
							hub.renderer.changeRenderableObject(hub.game);
						else
							Tools.infoBox("No current save detected!", "Warning");
					}
			}

		@Override
		protected void keyPressed(KeyEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void keyReleased(KeyEvent event)
			{
				// TODO Auto-generated method stub

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
		protected void frameResized(ComponentEvent event)
			{
				// TODO Auto-generated method stub

			}

		@Override
		public void tScrollBarScrolled(TScrollEvent event)
			{
				// TODO Auto-generated method stub

			}
	}
