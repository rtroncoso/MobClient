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
package com.mob.client.screens;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mob.client.Game;

import net.mostlyoriginal.api.utils.builder.WorldConfigurationBuilder;

public abstract class Screen extends ScreenAdapter {

    public static final int GAME_RUNNING = 0;
    public static final int GAME_PAUSED = 1;

    protected Game game;
    protected World world;
    protected FPSLogger logger;
    protected final WorldConfigurationBuilder builder = new WorldConfigurationBuilder();

    protected int state;

    /**
     * Screen constructor, calls abstract methods for scene
     * initialization using a WorldConfigurationBuilder
     *
     * @param game
     */
	public Screen(Game game) {
		this.game = game;
        this.logger = new FPSLogger();

        this.initSystems(builder);
        this.initScene();

        this.world = new World(builder.build());
        this.state = GAME_RUNNING;
	}

    @Override
    public void render (float delta) {
        this.update(delta);
//		this.drawUI();
    }

    @Override
    public void pause () {
        if (this.state == GAME_RUNNING) {
            this.state = GAME_PAUSED;
            this.pauseSystems();
        }
    }
    @Override
    public void resume() {
        if(this.state == GAME_PAUSED) {
            this.state = GAME_RUNNING;
            this.resumeSystems();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    abstract protected void initSystems(WorldConfigurationBuilder builder);
    abstract protected void initScene();
    abstract protected void resumeSystems();
    abstract protected void pauseSystems();
    abstract protected void updatePaused();
    abstract protected void updateRunning(float deltaTime);
    abstract protected void update(float deltaTime);

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
