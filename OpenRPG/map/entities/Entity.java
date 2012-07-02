package OpenRPG.map.entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import OpenRPG.utils.Camera;
import TroysCode.Constants;
import TroysCode.T.TPoint;

public abstract class Entity
	{
		public boolean exists = true;

		/**
		 * The X co-ordinate of this {@link Entity} on the map grid.
		 */
		protected int mapX;
		/**
		 * The Y co-ordinate of this {@link Entity} on the map grid.
		 */
		protected int mapY;

		/**
		 * The actual X co-ordinate of this {@link Entity} in the world.
		 */
		private float locX;
		/**
		 * The actual Y co-ordinate of this {@link Entity} in the world.
		 */
		private float locY;

		/**
		 * This array is used to hold constants which describe the
		 * characteristics of the entity.
		 */
		private byte[] data;

		/**
		 * This is the rectangle used for collision detection, it is
		 * automatically set to a Region.TILE_SIZE by Region.TILE_SIZE
		 * rectangle, aka the entire map region the Entity starts in
		 */
		private Rectangle collisionBox;

		/**
		 * The entities 'health' as a percentage.
		 */
		protected float health = 100;

		protected float frame = 0;

		public Entity(int mapX, int mapY, byte[] data)
			{
				this.mapX = mapX;
				this.mapY = mapY;
				this.data = data;

				locX = mapX * Constants.TILE_SIZE;
				locY = mapY * Constants.TILE_SIZE;

				collisionBox = new Rectangle((int) locX, (int) locY, Constants.TILE_SIZE, Constants.TILE_SIZE);
			}

		public abstract void tick();

		public abstract void render(Graphics g, Camera cam);

		public final TPoint getActualLocation()
			{
				return new TPoint(getLocX(), getLocY());
			}

		public final Point getMapLocation()
			{
				return new Point(mapX, mapY);
			}

		public final float getLocX()
			{
				return locX;
			}

		public final float getLocY()
			{
				return locY;
			}

		public final int getMapX()
			{
				return mapX;
			}

		public final int getMapY()
			{
				return mapY;
			}

		public final byte[] getData()
			{
				return data;
			}

		public final float getHealth()
			{
				return health;
			}

		public final int getWidth()
			{
				return collisionBox.width;
			}

		public final int getHeight()
			{
				return collisionBox.height;
			}

		public final Dimension getDimensions()
			{
				return new Dimension(collisionBox.width, collisionBox.height);
			}

		public final Rectangle getCollisionRect()
			{
				return new Rectangle((int) getLocX(), (int) getLocY(), collisionBox.width, collisionBox.height);
			}

		public final void setLocX(float newLocX)
			{
				locX = newLocX;
				collisionBox.x = (int) locX;
			}

		public final void setLocY(float newLocY)
			{
				locY = newLocY;
				collisionBox.y = (int) locY;
			}

		protected final void setCollisionBoxSize(int width, int height)
			{
				collisionBox.width = width;
				collisionBox.height = height;
			}


		public final void damageEntity(double damageDealt)
			{
				health -= damageDealt;
				if (health <= 0)
					healthFullyDepleted();
			}

		protected abstract void healthFullyDepleted();
	}
