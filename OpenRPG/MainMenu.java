package OpenRPG;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;

import TroysCode.Constants;
import TroysCode.RenderableObject;
import TroysCode.hub;
import TroysCode.T.TButton;
import TroysCode.T.TMenu;
import TroysCode.T.TScrollEvent;

public class MainMenu extends RenderableObject implements Constants
	{
		private static final long serialVersionUID = 1L;

		private final TMenu mainOptions = new TMenu(200, 0, 400, 575, TMenu.VERTICAL);

		private final TButton newGameButton = new TButton(0, 0, hub.images.mainMenu[MMB_NEW_GAME]);
		private final TButton helpButton = new TButton(0, 0, hub.images.mainMenu[MMB_HELP]);
		private final TButton saveLoadButton = new TButton(0, 0, hub.images.mainMenu[MMB_SAVE_LOAD]);
		private final TButton optionsButton = new TButton(0, 0, hub.images.mainMenu[MMB_OPTIONS]);
		private final TButton backButton = new TButton(0, 0, hub.images.mainMenu[MMB_BACK]);

		@Override
		protected void initiate()
			{
				addTComponent(mainOptions);

				mainOptions.addTButton(newGameButton, false);
				mainOptions.addTButton(saveLoadButton, false);
				mainOptions.addTButton(helpButton, false);
				mainOptions.addTButton(optionsButton, false);
				mainOptions.addTButton(backButton, false);
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
		protected void renderObject(Graphics g)
			{
				g.setColor(Color.BLUE);
				g.fillRect(0, 0, 800, 600);
			}

		@Override
		protected void actionPerformed(ActionEvent event)
			{
				if (event.getSource() == newGameButton)
					{
						changeRenderableObject(hub.game);
						return;
					}
				else if (event.getSource() == saveLoadButton)
					{
						return;
					}
				else if (event.getSource() == helpButton)
					{
						return;
					}
				else if (event.getSource() == optionsButton)
					{
						return;
					}
				else if (event.getSource() == backButton)
					{
						changeRenderableObject(hub.game);
						return;
					}
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
		protected void mouseWheelScrolled(MouseWheelEvent event)
			{
				// TODO Auto-generated method stub

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
		public void tScrollBarScrolled(TScrollEvent event)
			{
				// TODO Auto-generated method stub

			}

	}
