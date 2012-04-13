package TroysCode;

/**
 * This interface contains variables with a constant value. These constants can
 * be reached in any Class, as long as they <code> implement Constants</code>.
 * 
 * @author Sebastian Troy
 */
public interface Constants
	{
		/*
		 * This class allows the declaration of constants. I often use constants
		 * to refer to objects in an array, for example: ""arrayOfColours[0]""
		 * refers to the first member of that array, but that could be any
		 * colour. If I have the constant ""public static final byte BLUE = 0""
		 * and call ""arrayOfColours[BLUE]"" I then have an idea of what it is
		 * I'm actually accesing.
		 */
		// GLOBAL IDENTIFIERS
		public static final byte ID = 0;
		public static final byte X = 1;
		public static final byte Y = 2;

		/* ############################################################### */

		// TILE IDENTIFIERS
		public static final byte STATE = 3;

		// TILE CONSTANTS
		public static final byte EMPTY = 0;
		public static final byte SOLID = 1;

		// TILE DATA
		public static final byte[] TILE_DEBUG = { 0, 0, 0, SOLID };
		public static final byte[] GRASS = { 1, 1, 0, EMPTY };
		public static final byte[] GRASS1 = { 1, 1, 1, EMPTY };
		public static final byte[] GRASS2 = { 1, 1, 2, EMPTY };
		public static final byte[] GRASS3 = { 1, 1, 3, EMPTY };
		public static final byte[] TREE = { 2, 2, 0, SOLID };
		public static final byte[] TREE1 = { 2, 2, 1, SOLID };
		public static final byte[] TREE2 = { 2, 2, 2, SOLID };
		public static final byte[] TREE3 = { 2, 2, 3, SOLID };
		public static final byte[] BOULDER = { 3, 3, 0, SOLID };
		public static final byte[] BOULDER2 = { 3, 3, 1, SOLID };
		public static final byte[] BOULDER3 = { 3, 3, 2, SOLID };
		public static final byte[] BOULDER4 = { 3, 3, 3, SOLID };

		/* ############################################################### */

		// ITEM IDENTIFIERS
		// ID = 0;
		// X = 1;
		// Y = 2;
		public static final byte STACK = 3;
		public static final byte WEIGHT = 4;
		public static final byte DAMAGE = 5;
		public static final byte TYPE = 6;

		// ITEM CONSTANTS
		public static final byte RESOURCE = 0;
		public static final byte TOOL = 1;
		public static final byte WEAPON = 2;

		// ITEM DATA
		public static final byte[] NO_ITEM = { 0, 0, 0, 0, 0, 0, TOOL };
		public static final byte[] STONES = { 1, 1, 0, 6, 2, 0, RESOURCE };
		public static final byte[] SWORD = { 2, 2, 0, 1, 1, 4, WEAPON };
		public static final byte[] PICK = { 3, 3, 0, 1, 1, 0, TOOL };
		public static final byte[] AXE = { 4, 4, 0, 1, 1, 0, TOOL };
		public static final byte[] WOOD = { 5, 5, 0, 16, 2, 0, RESOURCE };

		// ###########################################################

		// MOB IDENTIFIERS
		// ID = 0;
		// X = 1;
		// Y = 2;

		// MOB CONSTANTS
		// facing
		public static final byte NORTH = 0;
		public static final byte EAST = 1;
		public static final byte SOUTH = 2;
		public static final byte WEST = 3;

		// action
		public static final byte STANDING = 0;
		public static final byte MOVING = 1;
		public static final byte INTERACTING = 2;

		// MOB DATA
		public static final byte[] MOB_DEBUG = { 0, 1, 1 };
		public static final byte[] SWORDSMAN = { 1, 1, 1 };
		
		// ###########################################################

		//KEY CONSTANTS
		public static final byte NONE = 0;
		public static final byte UP = 1;
		public static final byte RIGHT = 2;
		public static final byte DOWN = 3;
		public static final byte LEFT = 4;
		public static final byte SPACE = 5;
		public static final byte Q = 5;
	}
