package OpenRPG;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import OpenRPG.utils.Save;
import TroysCode.RenderableObject;
import TroysCode.Tools;
import TroysCode.hub;
import TroysCode.T.TButton;
import TroysCode.T.TScrollBar;
import TroysCode.T.TScrollEvent;

public class SaveManager extends RenderableObject
	{
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private String saveDirectory = "saves//";
		private String regionDirectory = "regions//";

		private TScrollBar scrollbar = new TScrollBar(0, 0, TScrollBar.VERTICAL, 573);

		public ArrayList<SaveFiles> saves = new ArrayList<SaveFiles>();

		private TButton saveButton = new TButton(500, 88, hub.images.saveManager[2]);
		private TButton loadButton = new TButton(500, 238, hub.images.saveManager[3]);
		private TButton backButton = new TButton(500, 388, hub.images.saveManager[4]);
		private TButton deleteButton = new TButton(500, 388, hub.images.saveManager[4]);

		public SaveManager()
			{
			}

		@Override
		protected void initiate()
			{
				addTComponent(scrollbar);
				
				addTComponent(saveButton);
				addTComponent(loadButton);
				addTComponent(backButton);

				addTComponent(deleteButton);
			}

		@Override
		protected void refresh()
			{
				for (SaveFiles s : saves)
					removeTComponent(s);
				setSaves();
				for (SaveFiles s : saves)
					addTComponent(s);
			}

		@Override
		public void tick()
			{
			}

		@Override
		public void renderObject(Graphics g)
			{
				g.fillRect(0, 0, 800, 600);

				for (SaveFiles s : saves)
					{
						if (s.selected)
							g.drawImage(hub.images.saveManager[7], Math.round(s.getX() - 10), Math.round(s.getY()), hub.renderer);
					}
			}

		private synchronized final void save()
			{
				hub.save.saveName = Tools.getText();

				ObjectOutputStream OOS = null;
				try
					{
						Save save = hub.save;
						File saveFile = new File(saveDirectory + save.saveName + ".dat");
						OOS = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(saveFile, false)));

						OOS.writeUTF(save.saveName);
						OOS.writeLong(save.time);
						OOS.writeObject(save);

//						for (int x = 0; x < save.region.xSize; x++)
//							for (int y = 0; y < save.region.ySize; y++)
//								{
//									OOS.write(save.region.getTile(x, y).id);
//									OOS.writeBoolean(save.region.getTile(x, y).containsMob);
//								}

						hub.save.saved = true;

						for (SaveFiles s : saves)
							removeTComponent(s);
						setSaves();
						for (SaveFiles s : saves)
							addTComponent(s);
					}
				catch (Exception e)
					{
						Tools.errorWindow(e, "Problem writing to file");
					}
				finally
					{
						try
							{
								OOS.flush();
								OOS.close();
							}
						catch (IOException e)
							{
								Tools.errorWindow(e, "Problem closing streams");
							}
					}
				setSaves();
			}

		private synchronized final void loadSave()

			{
				SaveFiles selectedSave = null;
				for (SaveFiles s : saves)
					if (s.selected)
						selectedSave = s;

				if (selectedSave != null)
					{
						ObjectInputStream OIS = null;
						try
							{
								File savedGame = new File(this.saveDirectory + selectedSave.saveName + ".dat");
								OIS = new ObjectInputStream(new BufferedInputStream(new FileInputStream(savedGame)));

								String saveName = OIS.readUTF();
								OIS.readLong();
								hub.save = (Save) OIS.readObject();
								hub.save.saveName = saveName;

								hub.save.reLoad();
							}
						catch (Exception e)
							{
								Tools.errorWindow(e, "loadFile, I/O error");
							}
						finally
							{
								try
									{
										OIS.close();
									}
								catch (IOException e)
									{
										Tools.errorWindow(e, "Problem closing OIS");
									}
							}
					}
			}

		private synchronized final void deleteFile(String saveName)
			{
				File save = new File(saveDirectory + saveName + ".dat");
				if (save.exists())
					{
						if (!save.delete())
							Tools.infoBox("Problem deleting Save", "delete method in saveManager");
						else
							for (SaveFiles s : saves)
								if (s.selected)
									removeTComponent(s);
					}
				else
					Tools.infoBox("Uhoh, no save detected", "Save Manager");
			}

		private synchronized final void setSaves()
			{
				saves.clear();

				File directory = new File("saves");

				if (!directory.exists())
					{
						directory.mkdir();
						Tools.infoBox("No saves folder detected!" + "\n" + "New saves folder created", "SavesManager");
					}

				File[] files = directory.listFiles();

				for (File save : files)
					{
						ObjectInputStream OIS = null;
						try
							{
								// Get data from the save
								OIS = new ObjectInputStream(new BufferedInputStream(new FileInputStream(save)));
								String name = OIS.readUTF();
								Long time = OIS.readLong();
								String size = "    MB: " + (save.length() / 1024) / 1024;
								// Create Save and it's image
								BufferedImage bIm = new BufferedImage(350, 50, BufferedImage.TYPE_4BYTE_ABGR);

								Graphics g = bIm.createGraphics();

								Font font = new Font(g.getFont().toString(), Font.BOLD, 20);

								g.setFont(font);
								g.drawImage(hub.images.saveManager[5], 0, 0, hub.renderer);
								g.drawString(" " + name, 50, 20);
								g.drawString("   Time Played: " + time + size, 50, 40);

								this.saves.add(new SaveFiles(20, 20, bIm, name));
							}
						catch (Exception e)
							{
								Tools.errorWindow(e, "loadFile, I/O error, cardStats");
							}
						finally
							{
								try
									{
										OIS.close();
									}
								catch (IOException e)
									{
										Tools.errorWindow(e, "problem closing input stream, SaveCards");
									}
							}
					}
			}

		@Override
		public void mouseDragged(MouseEvent me)
			{
				scrollbar.mouseDragged(me);
			}

		public void keyPressed(KeyEvent ke)
			{
			}

		public void loadRegion(String regName)

			{
				ObjectInputStream OIS = null;
				try
					{
						File region = new File(this.regionDirectory + regName + ".dat");
						OIS = new ObjectInputStream(new BufferedInputStream(new FileInputStream(region)));

						changeRenderableObject(hub.game);

						OIS.readUTF();
					}
				catch (Exception e)
					{
						Tools.errorWindow(e, "loadFile, I/O error");
					}
			}

		@Override
		protected void mousePressed(MouseEvent event)
			{
				// Selected save chooser
				for (SaveFiles save : saves)
					if (save.containsPoint(event.getPoint()))
						save.selected = true;
					else
						save.selected = false;
			}

		@Override
		protected void mouseReleased(MouseEvent event)
			{
			}

		@Override
		protected void mouseMoved(MouseEvent event)
			{
			}

		@Override
		protected void mouseWheelMoved(MouseWheelEvent event)
			{
			}

		@Override
		protected void actionPerformed(ActionEvent event)
			{
				if (event.getSource() == saveButton)// save
					{
						if (hub.save != null)
							save();
						else
							Tools.infoBox("No current game detected", "");
					}

				if (event.getSource() == loadButton)// load
					{
						loadSave();
						hub.renderer.changeRenderableObject(hub.game);
					}

				if (event.getSource() == backButton)// back
					{
						hub.renderer.changeRenderableObject(hub.mainMenu);
					}

				if (event.getSource() == deleteButton)// delete
					{
						for (SaveFiles s : saves)
							if (s.selected)
								{
									deleteFile(s.saveName);
									setSaves();
								}
					}
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
			}

		@Override
		public void tScrollBarScrolled(TScrollEvent event)
			{
				float yModifier = 0;
				int saveNum = 0;
				for (SaveFiles s : saves)
					{
						if (saves.size() > 11)
							{
								float distance = -(saves.size() - 11) * 50;
								yModifier = (distance / 100) * scrollbar.getSliderPercent();
							}
						s.setPosition(40, 12.5f + (saveNum * 50));
						saveNum++;
					}
			}

		private class SaveFiles extends TButton
			{
				private static final long serialVersionUID = 1L;

				private String saveName = "";

				private boolean selected = false;

				private SaveFiles(float x, float y, BufferedImage image, String saveName)
					{
						super(x, y, image);
						this.saveName = saveName;
					}
			}
	}
