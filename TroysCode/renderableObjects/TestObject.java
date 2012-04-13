package TroysCode.renderableObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import TroysCode.RenderableObject;
import TroysCode.Tools;
import TroysCode.hub;
import TroysCode.T.TButton;
import TroysCode.T.TCollection;
import TroysCode.T.TComponent;
import TroysCode.T.TScrollBar;
import TroysCode.T.TScrollEvent;
import TroysCode.T.TSlider;

public class TestObject extends RenderableObject
	{
		private static final long serialVersionUID = 1L;
		
		private TCollection buttonCollection = new TCollection(200, 200);

		private TButton resizeButton = new TButton(50, 0, "<--- Scroll me!");
		private TSlider buttonResizeSlider = new TSlider(0, 0, TSlider.VERTICAL, 563, 3);
		private TScrollBar buttonResizeScroll = new TScrollBar(25, 0, TScrollBar.VERTICAL, 563);

		private TButton particleSwitch = new TButton(350, 270, "Turn Particles Off");
		private boolean isParticlesOn = true;

		private ArrayList<Particle> dots = new ArrayList<Particle>(100);

		public TestObject()
			{
			}

		@Override
		protected void initiate()
			{
				addTComponent(resizeButton);
				resizeButton.setFontSize(51);

				addTComponent(buttonResizeSlider);

				addTComponent(buttonResizeScroll);
				buttonResizeScroll.setScrollPercent(50f);

				buttonCollection.addTComponent(particleSwitch);
				particleSwitch.setFontSize(16);
				addTComponent(buttonCollection);
			}

		@Override
		protected void refresh()
			{
				resizeButton.setFontSize(51);

				buttonResizeScroll.setScrollPercent(50);

				particleSwitch.setFontSize(16);
			}

		@Override
		protected void tick()
			{
				for (int i = 0; i < dots.size(); i++)
					{
						if (Tools.randPercent() > 98 || dots.get(i).y > hub.frame.getHeight() - 30)
							modifyDots(null).remove(i);
						else
							{
								modifyDots(null).get(i).y += modifyDots(null).get(i).velY + Tools.randInt(-1, 1);
								modifyDots(null).get(i).x += modifyDots(null).get(i).velX + Tools.randInt(-1, 1);
								modifyDots(null).get(i).calculateVelocity();
							}
					}
			}

		@Override
		protected void renderObject(Graphics g)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, hub.frame.getWidth(), hub.frame.getHeight());

				for (int i = 0; i < dots.size(); i++)
					{
						g.setColor(Tools.randColour());
						g.drawString("*", dots.get(i).x, dots.get(i).y);
					}
			}

		@Override
		protected void mousePressed(MouseEvent event)
			{
				for (int i = Tools.randInt(39, 57); i > 0; i--)
					modifyDots(new Particle(event.getX(), event.getY(), Tools.randInt(-9, 9), Tools.randInt(-9, 9)));
			}

		@Override
		protected void mouseReleased(MouseEvent event)
			{
			}

		@Override
		protected void mouseDragged(MouseEvent event)
			{
			}

		@Override
		protected void mouseMoved(MouseEvent event)
			{
				buttonCollection.setPosition(event.getX(), event.getY());
				
				if (Tools.randPercent() > 60)
					modifyDots(new Particle(event.getX(), event.getY(), Tools.randInt(-4, 4), Tools.randInt(-4, 4)));
			}

		@Override
		protected void mouseWheelMoved(MouseWheelEvent event)
			{
			}

		@Override
		protected void actionPerformed(ActionEvent event)
			{
				if (event.getSource() == particleSwitch)
					{
						if (isParticlesOn)
							{
								particleSwitch.setLabel("Turn Particles On");
								dots = new ArrayList<Particle>(100);
							}
						else
							{
								particleSwitch.setLabel("Turn Particles Off");
							}
						isParticlesOn = !isParticlesOn;
					}
			}

		@Override
		public void tScrollBarScrolled(TScrollEvent event)
			{
				if (event.getSource() == buttonResizeSlider || event.getSource() == buttonResizeScroll)
					resizeButton.setFontSize((int) event.scrollPercent + 1);

			}

		@Override
		protected void keyPressed(KeyEvent event)
			{
			}

		@Override
		protected void keyReleased(KeyEvent event)
			{
			}

		@Override
		protected void keyTyped(KeyEvent event)
			{
			}

		@Override
		protected void mouseClicked(MouseEvent event)
			{
			}

		@Override
		protected void mouseEntered(MouseEvent event)
			{
			}

		@Override
		protected void mouseExited(MouseEvent event)
			{
			}

		@Override
		protected void programGainedFocus(WindowEvent event)
			{
			}

		@Override
		protected void programLostFocus(WindowEvent event)
			{
			}

		@Override
		protected void frameResized(ComponentEvent event)
			{
				buttonResizeSlider.setLength(hub.frame.getHeight() - 37);
				buttonResizeScroll.setLength(hub.frame.getHeight() - 37);

				particleSwitch.setPositionOfCenter((hub.frame.getWidth() / 2), hub.frame.getHeight() / 2);
			}

		private synchronized final ArrayList<Particle> modifyDots(Particle newDot)
			{
				if (newDot != null && isParticlesOn)
					dots.add(newDot);

				return dots;
			}

		private class Particle
			{
				private int x = 0;
				private int y = 0;
				private float velX = 0;
				private float velY = 0;

				private Particle(int x, int y, int velX, int velY)
					{
						this.x = x;
						this.y = y;
						this.velX = velX;
						this.velY = velY;
					}

				private final void calculateVelocity()
					{
						if (velY < 1)
							velY += 0.5f;
						else if (velY > 1)
							velY -= 0.125f;

						if (velX < 0)
							velX += 0.25f;
						else if (velX > 0)
							velX -= 0.25f;
					}
			}
	}
