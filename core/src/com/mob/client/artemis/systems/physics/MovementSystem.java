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
package com.mob.client.artemis.systems.physics;

import character.Heading;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mob.client.artemis.systems.interactions.MeditateSystem;
import com.mob.dao.objects.Tile;
import movement.Destination;
import physics.AOPhysics;
import position.Pos2D;
import position.WorldPos;

import java.util.Optional;

import static com.artemis.E.E;

@Wire
public class MovementSystem extends IteratingSystem {

	public MovementSystem() {
		super(Aspect.all(AOPhysics.class,
                WorldPos.class, Pos2D.class));
	}

    @Override
    protected void process(int entity) {
        E player = E(entity);
        final AOPhysics phys = player.getAOPhysics();
        final WorldPos pos = player.getWorldPos();

        Optional<AOPhysics.Movement> movementIntention = phys.getMovementIntention();
        if (!player.hasDestination()) {
            movementIntention.ifPresent(movement -> {
                WorldPos expectedPos = getExpectedPos(movement, pos);
                player.destinationX(expectedPos.x);
                player.destinationY(expectedPos.y);
                // stop meditating
                stopMeditating(player);
            });
        }
        player.moving(player.hasDestination());
        if (player.hasDestination()) {
            if (movePlayer(player)) {
                Destination destination = player.getDestination();
                pos.x = destination.x;
                pos.y = destination.y;
                player.removeDestination();
            }
        }

    }

    private void stopMeditating(E player) {
        player.getStates().meditating = false;
        MeditateSystem.stopMeditating(player);
    }

    private boolean movePlayer(E player) {
        Destination destination = player.getDestination();
        Pos2D pos2D = player.getPos2D();
        AOPhysics.Movement dir = getDirection(player.getWorldPos(), destination);
        Pos2D possiblePos = new Pos2D(pos2D.x, pos2D.y);
        float delta = world.getDelta() * AOPhysics.WALKING_VELOCITY / Tile.TILE_PIXEL_HEIGHT;
        switch (dir) {
            default:
            case DOWN:
                possiblePos.y += delta;
                player.headingCurrent(Heading.HEADING_SOUTH);
                break;
            case LEFT:
                possiblePos.x -= delta;
                player.headingCurrent(Heading.HEADING_WEST);
                break;
            case RIGHT:
                possiblePos.x += delta;
                player.headingCurrent(Heading.HEADING_EAST);
                break;
            case UP:
                possiblePos.y -= delta;
                player.headingCurrent(Heading.HEADING_NORTH);
                break;
        }

        adjustPossiblePos(possiblePos, destination, dir);

        pos2D.y = possiblePos.y;
        pos2D.x = possiblePos.x;
        return pos2D.x % 1 == 0 && pos2D.y % 1 == 0;
	}

    private void adjustPossiblePos(Pos2D possiblePos, Destination destination, AOPhysics.Movement dir) {
	    int newY = (int) possiblePos.y;
        int newX = (int) possiblePos.x;
        switch (dir) {
            case LEFT:
                if (newX < destination.x) {
                    possiblePos.x = destination.x;
                }
                break;
            case RIGHT:
                if (newX == destination.x) {
                    possiblePos.x = destination.x;
                }
                break;
            case UP:
                if (newY < destination.y) {
                    possiblePos.y = destination.y;
                }
                break;
            case DOWN:
                if (newY == destination.y) {
                    possiblePos.y = destination.y;
                }
                break;
        }
    }

    private AOPhysics.Movement getDirection(WorldPos worldPos, Destination destination) {
	    if (worldPos.x != destination.x) {
	        if (worldPos.x < destination.x) {
	            return AOPhysics.Movement.RIGHT;
            } else {
	            return AOPhysics.Movement.LEFT;
            }
        } else {
	        if (worldPos.y > destination.y) {
                return AOPhysics.Movement.UP;
            } else {
	            return AOPhysics.Movement.DOWN;
            }
        }
    }


    private WorldPos getExpectedPos(AOPhysics.Movement movement, WorldPos pos) {
	    switch (movement) {
            case RIGHT:
                return new WorldPos(pos.x + 1, pos.y);
            case LEFT:
                return new WorldPos(pos.x - 1, pos.y);
            case UP:
                return new WorldPos(pos.x, pos.y - 1);
            case DOWN:
                return new WorldPos(pos.x, pos.y + 1);
            default:
                return new WorldPos(pos.x, pos.y);
        }
    }

}
