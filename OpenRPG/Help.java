package OpenRPG;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;

import TroysCode.RenderableObject;
import TroysCode.hub;
import TroysCode.T.TScrollEvent;

public class Help extends RenderableObject
	{
		private static final long serialVersionUID = 1L;

		private String[] lines = {
//				"Press 'M' key to return to the menu",
				"Use the Arrow Keys to Move", "Use Space to interact with the tile in front of you", "Press E to Exit if the window listener fails",
				"Click on the item in your inventory you want to use", "Use 'WASD' keys to cycle through your inventory",
				"Use 'Q' key to select nothing in your inventory",
//				"",
//				"",
//				"",
//				"",
//				"",
//				"",
//				"",
//				"",
		}; // DO NOT USE MORE LINES THAN PROVIDED!

		public Help()
			{
			}

		public void tick()
			{
			}

		public void renderObject(Graphics g)
			{
				g.fillRect(0, 0, 800, 600);

				Font font = new Font(g.getFont().toString(), Font.BOLD, 30);

				g.setFont(font);
				g.setColor(Color.ORANGE);

				int spacing = 570 / lines.length;
				int y = 0;

				for (String s : lines)
					{
						g.drawString(s, 50, 30 + (y * spacing));
						y++;
					}
			}

		@Override
		protected void initiate()
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void refresh()
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mousePressed(MouseEvent event)
			{
				if (event.getButton() == MouseEvent.BUTTON1)
					changeRenderableObject(hub.mainMenu);
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
