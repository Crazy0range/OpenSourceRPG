package TroysCode.T;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.io.Serializable;
import java.util.ArrayList;

import TroysCode.hub;

/**
 * This class is designed to allow the moving of multiple {@link TComponent}s at
 * one time easy.
 * 
 * @author Sebastian Troy
 */
public class TCollection extends TComponent implements Serializable, MouseListener, MouseMotionListener
	{
		private static final long serialVersionUID = 1L;

		/**
		 * An {@link ArrayList} of all the {@link TComponent}s within this
		 * TCollection.
		 */
		private ArrayList<TComponent> components = new ArrayList<TComponent>();

		/**
		 * The position of the {@link TCollection}. When the position of this
		 * {@link TCollection} is updated all of the positions of the
		 * {@link TComponent}s it contains are updated too.
		 * 
		 * @param x
		 *            - this x position of this {@link TCollection}.
		 * @param y
		 *            - this y position of this {@link TCollection}.
		 */
		public TCollection(float x, float y)
			{
				super(x, y, 0, 0);
			}

		/**
		 * This method tells the {@link TComponent} which
		 * {@link TComponentContainer} it has been added to. It then updates the
		 * {@link TComponentContainer} for each of the {@link TComponent}s it
		 * contains.
		 * 
		 * @param componentContainer
		 *            - the {@link TComponentContainer} to which this
		 *            {@link TComponent} has been added.
		 */
		protected final void setTComponentContainer(TComponentContainer componentContainer)
			{
				if (tComponentContainer != componentContainer)
					{
						if (tComponentContainer != null)
							{
								tComponentContainer.getParent().removeMouseListener(this);
								tComponentContainer.getParent().removeMouseMotionListener(this);

								TComponent[] components = new TComponent[this.components.size()];
								for (TComponent c : components)
									tComponentContainer.removeTComponent(c);
							}
						tComponentContainer = componentContainer;
						tComponentContainer.getParent().addMouseListener(this);
						tComponentContainer.getParent().addMouseMotionListener(this);
					}

				TComponent[] components = new TComponent[this.components.size()];
				for (TComponent c : components)
					tComponentContainer.addTComponent(c);
			}

		@Override
		protected final void removedFromTComponentContainer()
			{
				TComponent[] components = new TComponent[this.components.size()];
				this.components.toArray(components);
				for (TComponent c : components)
					tComponentContainer.removeTComponent(c);
				
				tComponentContainer = null;
			}

		public synchronized final void addTComponent(TComponent component)
			{
				components.add(component);
				if (tComponentContainer != null)
					tComponentContainer.addTComponent(component);
			}

		public synchronized final void removeTComponent(TComponent component)
			{
				components.remove(component);
				if (tComponentContainer != null)
					tComponentContainer.removeTComponent(component);
			}

		private synchronized final TComponent[] getTComponents()
			{
				TComponent[] tComponents = new TComponent[components.size()];
				components.toArray(tComponents);
				return tComponents;
			}

		/*
		 * If there is a background this draws it, and all it's embedded
		 * components
		 */
		@Override
		public final void render(Graphics g)
			{
				if (hub.DEBUG)
					g.drawRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
				
				for (TComponent tc : getTComponents())
					tc.render(g);
			}

		/*
		 * The following move and set position methods allow the movement of all
		 * the TComponents in unison, this is what this class is mainly intended
		 * for.
		 */

		@Override
		public final void setX(float x)
			{
				float diffX = x - this.x;
				this.moveX(diffX);
			}

		@Override
		public final void setY(float y)
			{
				float diffY = y - this.y;
				this.moveY(diffY);
			}

		@Override
		public final void setPosition(float x, float y)
			{
				float diffX = x - this.x;
				float diffY = y - this.y;
				this.movePosition(diffX, diffY);
			}

		@Override
		public final void moveX(float x)
			{
				this.x += x;
				for (TComponent tc : getTComponents())
					tc.moveX(x);
			}

		@Override
		public final void moveY(float y)
			{
				this.y += y;
				for (TComponent tc : getTComponents())
					tc.moveX(y);
			}

		@Override
		public final void movePosition(float x, float y)
			{
				this.x += x;
				this.y += y;
				for (TComponent tc : getTComponents())
					tc.movePosition(x, y);

			}

		/*
		 * The following methods pass on mouse events and key events onto any
		 * TComponents it contains
		 */

		@Override
		public final void mousePressed(MouseEvent me)
			{
				for (TComponent tc : getTComponents())
					tc.mousePressed(me);
			}

		@Override
		public final void mouseReleased(MouseEvent me)
			{
				for (TComponent tc : getTComponents())
					tc.mouseReleased(me);
			}

		@Override
		public final void mouseDragged(MouseEvent me)
			{
				for (TComponent tc : getTComponents())
					tc.mouseDragged(me);
			}

		@Override
		public void mouseMoved(MouseEvent me)
			{
				for (TComponent tc : getTComponents())
					tc.mouseMoved(me);
			}

		@Override
		public final void mouseWheelMoved(MouseWheelEvent me)
			{
				for (TComponent tc : getTComponents())
					tc.mouseDragged(me);
			}

		@Override
		public void mouseClicked(MouseEvent me)
			{
				for (TComponent tc : getTComponents())
					tc.mouseClicked(me);
			}

		@Override
		public void mouseEntered(MouseEvent me)
			{
				for (TComponent tc : getTComponents())
					tc.mouseEntered(me);
			}

		@Override
		public void mouseExited(MouseEvent me)
			{
				for (TComponent tc : getTComponents())
					tc.mouseExited(me);
			}

		@Override
		public final void actionPerformed(ActionEvent ae)
			{
				for (TComponent tc : getTComponents())
					tc.actionPerformed(ae);
			}

		@Override
		public final void keyPressed(KeyEvent ke)
			{
				for (TComponent tc : getTComponents())
					tc.keyPressed(ke);
			}

		@Override
		public final void keyReleased(KeyEvent ke)
			{
				for (TComponent tc : getTComponents())
					tc.keyReleased(ke);
			}

		@Override
		public final void keyTyped(KeyEvent ke)
			{
				for (TComponent tc : getTComponents())
					tc.keyTyped(ke);
			}
	}