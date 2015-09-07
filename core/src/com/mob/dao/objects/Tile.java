/*******************************************************************************
 * Copyright (C) 2014  Rodrigo Troncoso
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
/**
 * Stores single tile information
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.dao.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.textures.BundledAnimation;

import java.util.Vector;

public class Tile {

	public static final float TILE_PIXEL_WIDTH = 32.0f;
	public static final float TILE_PIXEL_HEIGHT = 32.0f;

	private int[] graphic;
	private Vector<BundledAnimation> textures = new Vector<BundledAnimation>();
	
	private int charIndex;
	private int objIndex;
	private int npcIndex;
	
	private WorldPosition tileExit;
	private boolean blocked;
	
	private int trigger;

	/**
	 * @param graphic
	 * @param charIndex
	 * @param objIndex
	 * @param npcIndex
	 * @param tileExit
	 * @param blocked
	 * @param trigger
	 */
	public Tile(int[] graphic, int charIndex, int objIndex,
				int npcIndex, WorldPosition tileExit, boolean blocked,
				int trigger) {
		super();
		this.setGraphic(graphic);
		this.setCharIndex(charIndex);
		this.setObjIndex(objIndex);
		this.setNpcIndex(npcIndex);
		this.setTileExit(tileExit);
		this.setBlocked(blocked);
		this.setTrigger(trigger);
		this.loadTextures();
	}

	public void loadTextures() {
		int layer = 0;
		this.textures.setSize(this.getGraphic().length);
		for(int grhIndex : this.getGraphic()) {
			if(grhIndex > 0)
				this.textures.setElementAt(
					new BundledAnimation(AssetsHandler.getGraphic(grhIndex)),
					layer
				);
			layer++;
		}
	}


	public TextureRegion getRegion(int index) {
		return (this.getGraphic(index) > 0) ?
				(this.textures.get(index).isAnimated() ?
						this.textures.get(index).getAnimatedGraphic(true) :
						this.textures.get(index).getGraphic()) :
				null;
	}

	public BundledAnimation getAnimation(int index) {
		return (this.textures.get(index) != null) ? this.textures.get(index) : null;
	}

	public int getGraphic(int index) {
		return this.graphic[index];
	}
	/**
	 * @return the graphic
	 */
	public int[] getGraphic() {
		return this.graphic;
	}

	/**
	 * @param graphic the graphic to set
	 */
	public void setGraphic(int[] graphic) {
		this.graphic = graphic;
	}


	/**
	 * @return the charIndex
	 */
	public int getCharIndex() {
		return charIndex;
	}


	/**
	 * @param charIndex the charIndex to set
	 */
	public void setCharIndex(int charIndex) {
		this.charIndex = charIndex;
	}


	/**
	 * @return the objIndex
	 */
	public int getObjIndex() {
		return objIndex;
	}


	/**
	 * @param objIndex the objIndex to set
	 */
	public void setObjIndex(int objIndex) {
		this.objIndex = objIndex;
	}


	/**
	 * @return the npcIndex
	 */
	public int getNpcIndex() {
		return npcIndex;
	}


	/**
	 * @param npcIndex the npcIndex to set
	 */
	public void setNpcIndex(int npcIndex) {
		this.npcIndex = npcIndex;
	}


	/**
	 * @return the tileExit
	 */
	public WorldPosition getTileExit() {
		return tileExit;
	}


	/**
	 * @param tileExit the tileExit to set
	 */
	public void setTileExit(WorldPosition tileExit) {
		this.tileExit = tileExit;
	}


	/**
	 * @return the blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}


	/**
	 * @param blocked the blocked to set
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}


	/**
	 * @return the trigger
	 */
	public int getTrigger() {
		return trigger;
	}


	/**
	 * @param trigger the trigger to set
	 */
	public void setTrigger(int trigger) {
		this.trigger = trigger;
	}

}
