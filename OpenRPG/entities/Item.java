package OpenRPG.entities;

import java.io.Serializable;

import TroysCode.Constants;

public class Item implements Constants, Serializable
	{
		private static final long serialVersionUID = 1L;

		public byte[] meta;
		public byte stack = 1;

		public Item(byte[] ID)
			{
				this.meta = ID;
			}

		public byte getDamageDealt(Mob mob)
			{
				if (meta[TYPE] == WEAPON)
					{
						if (mob.getClass() == SimpleMob.class)
							return (byte) (meta[DAMAGE] * 2);
					}
				return meta[DAMAGE];
			}
	}
