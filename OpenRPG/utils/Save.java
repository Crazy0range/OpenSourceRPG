package OpenRPG.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import OpenRPG.entities.Item;
import OpenRPG.entities.Mob;
import OpenRPG.entities.Player;
import OpenRPG.entities.SimpleMob;
import OpenRPG.map.Region;
import TroysCode.Constants;
import TroysCode.Tools;
import TroysCode.hub;
import TroysCode.T.TButton;

public class Save implements Serializable, Constants
	{
		private static final long serialVersionUID = 1L;

		public Camera cam = new Camera();

		public String saveName = "";
		public boolean saved = false;
		public long time = 0;

		public Region region = new Region();
		public Player player;
		public ArrayList<Mob> mobs = new ArrayList<Mob>();

		public Save()
			{
				region.createRegion();
			}
		
		// called when the save is loaded, as opposed to created
		public void reLoad()
			{
				saved = false;
			}

		public void newGame()
			{
				player = new Player(500, 500);
				mobs.add(player);
				player.health = 1;

				for (int i = 0; i < 1000; i++)
					{
						mobs.add(new SimpleMob(Tools.randInt(2, 998), Tools.randInt(2, 998), (Tools.randBool() ? SWORDSMAN : MOB_DEBUG)));
						if (mobs.get(i).meta[ID] == SWORDSMAN[ID])
							mobs.get(i).item = new Item(SWORD);
					}
			}

		public void render(Graphics g)
			{
				region.renderRegion(g, cam.x, cam.y);

				player.render(g);

				for (Mob mob : mobs)
					mob.render(g);

				// Draw GUI Background
				g.drawImage(hub.images.GUI[0], 0, 475, hub.renderer);

				// Draw health
				for (int i = 0; i < hub.save.player.health; i++)
					g.drawImage(hub.images.GUI[3], 240 + (i * 12), 505, hub.renderer);

				// Draw Inventory
				for (int x = 0; x < 7; x++)
					for (int y = 0; y < 2; y++)
					{
						Item item = hub.save.player.inventory[x + (y * 7)];
						g.drawImage(hub.images.objects[item.meta[X]][item.meta[Y]], 12 + (30 * x), 505 + (30 * y), hub.renderer);
						if (item.stack > 1)
							{
								Font font = new Font(g.getFont().toString(), Font.BOLD, 15);
								g.setFont(font);
								g.setColor(Color.BLACK);
								g.drawString("" + item.stack, 12 + (30 * x), 533 + (30 * y));
							}
					}
			}

		public void tick()
			{
				time++;
				cam.updatePos();
				player.tick();
				for (Mob mob : mobs)
					mob.tick();
			}
	}
