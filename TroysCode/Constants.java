package TroysCode;

/**
 * This interface contains variables with a constant value. These constants can
 * be reached in any Class, as long as they <code>implement Constants</code>.
 * 
 * @author Sebastian Troy
 */
public interface Constants
	{
		// ###########################################################

		// Entity map positions, V = vertical, H = horizontal
		public final int V_EXACT_H_EXACT = 0;
		public final int V_EXACT_H_OVERLAPPING = 1;
		public final int V_OVERLAPPING_H_EXACT = 2;
		public final int V_OVERLAPPING_H_OVERLAPPING = 3;
		public static final int TILE_SIZE = 50;

		// Entity data indexes
		public static final byte ENTITY_ID = 0;
		public static final byte ENTITY_STATE = 1;

		// Entity data
		public static final byte SOLID = 0;

		public static final byte[] PLAYER_DATA = { 0, 0 };
		public static final byte[] ROCK_DATA = { 1, 0 };

		// facing
		public static final byte NORTH = 0;
		public static final byte EAST = 1;
		public static final byte SOUTH = 2;
		public static final byte WEST = 3;
		public static final byte NONE = 4;

		// actionc
		public static final byte STANDING = 0;
		public static final byte WALKING = 1;

		// ###########################################################

		// Terrain constants
		public static final byte VOID_TILE = 0;
		public static final byte GRASS_1 = 1;
		public static final byte GRASS_2 = 2;
		public static final byte GRASS_3 = 3;
		public static final byte GRASS_4 = 4;

		// ###########################################################

		// Key constants
		public static final byte KEY_NONE = 0;
		public static final byte KEY_UP = 38;
		public static final byte KEY_RIGHT = 39;
		public static final byte KEY_DOWN = 40;
		public static final byte KEY_LEFT = 37;
		public static final byte KEY_SPACE = 32;
		public static final byte KEY_DELETE = 127;
		// ###########################################################

		// Image constants
		// Main Menu Buttons:
		public static final byte MMB_NEW_GAME = 0;
		public static final byte MMB_HELP = 1;
		public static final byte MMB_SAVE_LOAD = 2;
		public static final byte MMB_OPTIONS = 3;
		public static final byte MMB_BACK = 4;
	}
