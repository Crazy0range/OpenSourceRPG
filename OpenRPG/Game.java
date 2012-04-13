package OpenRPG;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;

import OpenRPG.entities.Item;
import TroysCode.Constants;
import TroysCode.RenderableObject;
import TroysCode.hub;
import TroysCode.T.TButton;
import TroysCode.T.TScrollEvent;

public class Game extends RenderableObject implements Constants
	{
		private static final long serialVersionUID = 1L;

		public byte key = NONE;
		public byte keyTwo = NONE;

		public boolean held = false;
		private byte timeHeld = 0;
		private byte delay = 0;
		private final byte HOLDTIME = 10;
		private final byte DELAYTIME = 10;

		private boolean paused = false;

		private int inventorySlotSelected = 0;

		private TButton inventorySlot1 = new TButton(12, 505, hub.images.GUI[1]);
		private TButton inventorySlot2 = new TButton(42, 505, hub.images.GUI[1]);
		private TButton inventorySlot3 = new TButton(72, 505, hub.images.GUI[1]);
		private TButton inventorySlot4 = new TButton(102, 505, hub.images.GUI[1]);
		private TButton inventorySlot5 = new TButton(132, 505, hub.images.GUI[1]);
		private TButton inventorySlot6 = new TButton(162, 505, hub.images.GUI[1]);
		private TButton inventorySlot7 = new TButton(192, 505, hub.images.GUI[1]);
		private TButton inventorySlot8 = new TButton(12, 535, hub.images.GUI[1]);
		private TButton inventorySlot9 = new TButton(42, 535, hub.images.GUI[1]);
		private TButton inventorySlot10 = new TButton(72, 535, hub.images.GUI[1]);
		private TButton inventorySlot11 = new TButton(102, 535, hub.images.GUI[1]);
		private TButton inventorySlot12 = new TButton(132, 535, hub.images.GUI[1]);
		private TButton inventorySlot13 = new TButton(162, 535, hub.images.GUI[1]);
		private TButton inventorySlot14 = new TButton(192, 535, hub.images.GUI[1]);

		public Game()
			{
			}

		@Override
		protected void initiate()
			{
				addTComponent(inventorySlot1);
				addTComponent(inventorySlot2);
				addTComponent(inventorySlot3);
				addTComponent(inventorySlot4);
				addTComponent(inventorySlot5);
				addTComponent(inventorySlot6);
				addTComponent(inventorySlot7);
				addTComponent(inventorySlot8);
				addTComponent(inventorySlot9);
				addTComponent(inventorySlot10);
				addTComponent(inventorySlot11);
				addTComponent(inventorySlot12);
				addTComponent(inventorySlot13);
				addTComponent(inventorySlot14);
			}

		@Override
		public void tick()
			{
				if (!paused)
					{
						hub.save.tick();
					}

				if (delay < DELAYTIME)
					delay++;

				if (timeHeld < HOLDTIME)
					timeHeld++;
				else
					held = true;

			}

		@Override
		public void renderObject(Graphics g)
			{
				hub.save.render(g);
			}

		@Override
		public void keyPressed(KeyEvent ke)
			{
				if (!paused)
					{
						if (ke.getKeyCode() == 127)// 'delete key'
							{
								hub.save.player.damage(new Item(SWORD));
								return;
							}

						if (ke.getKeyCode() == 32) // 'SPACE key'
							{
								keyTwo = SPACE;
								return;
							}
						if (ke.getKeyCode() == 38) // '^ key'
							{
								if (key == NONE && delay == DELAYTIME && hub.save.player.facing != NORTH)
									{
										held = false;
										timeHeld = 0;
									}
								key = UP;
								return;
							}
						else if (ke.getKeyCode() == 40) // 'v key'
							{
								if (key == NONE && delay == DELAYTIME && hub.save.player.facing != SOUTH)
									{
										held = false;
										timeHeld = 0;
									}
								key = DOWN;
								return;
							}
						if (ke.getKeyCode() == 37) // '< key'
							{
								if (key == NONE && delay == DELAYTIME && hub.save.player.facing != WEST)
									{
										held = false;
										timeHeld = 0;
									}
								key = LEFT;
								return;
							}
						else if (ke.getKeyCode() == 39) // '> key'
							{
								if (key == NONE && delay == DELAYTIME && hub.save.player.facing != EAST)
									{
										held = false;
										timeHeld = 0;
									}
								key = RIGHT;
								return;
							}
						else if (ke.getKeyCode() == 87) // 'w key'
							{
								incrementSlot((byte) UP);
								setCurrentItem();
							}
						else if (ke.getKeyCode() == 83) // 's key'
							{
								incrementSlot((byte) DOWN);
								setCurrentItem();
							}
						else if (ke.getKeyCode() == 65) // 'a key'
							{
								incrementSlot((byte) LEFT);
								setCurrentItem();
							}
						else if (ke.getKeyCode() == 68) // 'd key'
							{
								incrementSlot((byte) RIGHT);
								setCurrentItem();
							}
						else if (ke.getKeyCode() == 81) // 'q key'
							{
								incrementSlot((byte) Q);
								setCurrentItem();
							}
					}
			}
		
		private void incrementSlot(byte direction)
			{
				if (direction == UP)
					{
						if (inventorySlotSelected > 7)
							inventorySlotSelected -= 7;
					}
				else if (direction == DOWN)
					{
						if (inventorySlotSelected < 8)
							inventorySlotSelected += 7;
					}
				else if (direction == LEFT)
					{
						if (inventorySlotSelected > 1 && inventorySlotSelected != 8)
							inventorySlotSelected--;
						else
							inventorySlotSelected += 6;
					}
				else if (direction == RIGHT)
					{
						if (inventorySlotSelected < 14 && inventorySlotSelected != 7)
							inventorySlotSelected++;
						else
							inventorySlotSelected -= 6;
					}
				else if (direction == Q)
					inventorySlotSelected = 0;

				if (inventorySlotSelected < 1)
					inventorySlotSelected = 14;
				else if (inventorySlotSelected > 14)
					inventorySlotSelected = 0;
			}

		private void setCurrentItem()
			{
				if (inventorySlotSelected > 0)
					hub.save.player.item = hub.save.player.inventory[inventorySlotSelected - 1];
				else if (inventorySlotSelected == 0)
					hub.save.player.item = new Item(NO_ITEM);

			}

		@Override
		public void keyReleased(KeyEvent ke)
			{
				if (ke.getKeyCode() == 32) // 'SPACE key'
					{
						if (keyTwo == SPACE)
							keyTwo = NONE;
						return;
					}
				if (ke.getKeyCode() == 38) // '^ key'
					{
						if (key == UP)
							key = NONE;
						if (held)
							delay = 0;
						return;
					}
				else if (ke.getKeyCode() == 40) // 'v key'
					{
						if (key == DOWN)
							key = NONE;
						if (held)
							delay = 0;
						return;
					}
				if (ke.getKeyCode() == 37) // '< key'
					{
						if (key == LEFT)
							key = NONE;
						if (held)
							delay = 0;
						return;
					}
				else if (ke.getKeyCode() == 39) // '> key'
					{
						if (key == RIGHT)
							key = NONE;
						if (held)
							delay = 0;
						return;
					}
			}

		@Override
		protected void refresh()
			{
				// TODO Auto-generated method stub

			}

		@Override
		protected void mousePressed(MouseEvent event)
			{
			}

		@Override
		protected void mouseReleased(MouseEvent event)
			{
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
				if (event.getSource() == inventorySlot1)
					inventorySlotSelected = 1;
				else if (event.getSource() == inventorySlot2)
					inventorySlotSelected = 2;
				else if (event.getSource() == inventorySlot3)
					inventorySlotSelected = 3;
				else if (event.getSource() == inventorySlot4)
					inventorySlotSelected = 4;
				else if (event.getSource() == inventorySlot5)
					inventorySlotSelected = 5;
				else if (event.getSource() == inventorySlot6)
					inventorySlotSelected = 6;
				else if (event.getSource() == inventorySlot7)
					inventorySlotSelected = 7;
				else if (event.getSource() == inventorySlot8)
					inventorySlotSelected = 8;
				else if (event.getSource() == inventorySlot9)
					inventorySlotSelected = 9;
				else if (event.getSource() == inventorySlot10)
					inventorySlotSelected = 10;
				else if (event.getSource() == inventorySlot11)
					inventorySlotSelected = 11;
				else if (event.getSource() == inventorySlot12)
					inventorySlotSelected = 12;
				else if (event.getSource() == inventorySlot13)
					inventorySlotSelected = 13;
				else if (event.getSource() == inventorySlot14)
					inventorySlotSelected = 14;
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
