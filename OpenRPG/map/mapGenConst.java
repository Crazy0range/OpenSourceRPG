package OpenRPG.map;

import java.awt.Color;

public interface mapGenConst
	{
		// Map Properties
		
		// Forest options
		public final static float BASE_FOREST_DENSITY = 0.65f;
		public final static int NUM_FORESTS = 12;
		public final static int BASE_FOREST_SIZE = 95;
		public final static int RANGE_FOREST_SIZE = 36;
		public final static int FOREST_LOBES_BASE = 20;//number of
		public final static int FOREST_LOBES_RANGE = 9;
		public final static int FOREST_LOBES_SPREAD = 200;
		
		// Mountain options
		public final static float BASE_MOUNTAIN_DENSITY = 0.05f;
		public final static int NUM_MOUNTAINS = 3;
		public final static int BASE_MOUNTAIN_SIZE = 65;
		public final static int RANGE_MOUNTAIN_SIZE = 36;
		public final static int MOUNTAIN_LOBES_BASE = 35;//number of
		public final static int MOUNTAIN_LOBES_RANGE = 9;
		public final static int MOUNTAIN_LOBES_SPREAD = 120;
		
		//Colours
		public static final Color DEBUG_MAP = new Color(0, 0, 0);
		public static final Color GRASS_MAP = new Color(0, 190, 0);
		public static final Color STONEGRASS_MAP = new Color(100, 190, 100);
		public static final Color BOULDER_MAP = new Color(100, 100, 100);
		public static final Color COBBLESTONE_MAP = new Color(60, 60, 60);
		public static final Color TREE_MAP = new Color(0, 80, 0);
	}
