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
 * Stores information about a head
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.dao.objects;

import com.badlogic.gdx.utils.IntMap;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.textures.BundledAnimation;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Head {

	private IntMap<BundledAnimation> animations = new IntMap<BundledAnimation>();
	private int[] headIndex;

	/**
	 * No-params constructor
	 */
	public Head() {

	}

	/**
	 * @param headIndex
	 */
	public Head(int[] headIndex) {
		this.headIndex = headIndex;
        this.loadAnimations();
	}

    private void loadAnimations() {
        for(int i = 0; i < headIndex.length; i++) {
            Graphic grh = AssetsHandler.getGraphic(headIndex[i]);
            if (grh != null) {
                animations.put(i, new BundledAnimation(AssetsHandler.getGraphic(headIndex[i])));
            }
        }
    }

	/**
	 * @return the headIndex
	 */
	public int[] getHeadIndex() {
		return headIndex;
	}

	/**
	 * @param headIndex the headIndex to set
	 */
	public void setHeadIndex(int[] headIndex) {
		this.headIndex = headIndex;
	}
	
	public int getHead(int pIndex) {
		return this.headIndex[pIndex];
	}

    public BundledAnimation getAnimation(int index) {
        return animations.get(index);
    }

    public IntMap<BundledAnimation> getAnimations() {
        return animations;
    }

    public void setAnimations(IntMap<BundledAnimation> animations) {
        this.animations = animations;
    }

}
