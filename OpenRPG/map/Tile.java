package OpenRPG.map;

import java.io.Serializable;

import OpenRPG.entities.Mob;
import TroysCode.Constants;

public class Tile implements Constants, Serializable
	{
		private static final long serialVersionUID = 1L;

		public byte[] meta;

		public Mob mob = null;

		public Tile(byte[] id)
			{
				this.meta = id;
			}
	}
