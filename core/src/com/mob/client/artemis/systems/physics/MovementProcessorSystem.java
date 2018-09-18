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

import camera.Focused;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.mob.client.artemis.systems.interactions.MeditateSystem;
import com.mob.client.screens.GameScreen;
import com.mob.client.utils.MapUtils;
import com.mob.dao.objects.Tile;
import com.mob.network.movement.MovementRequest;
import com.mob.shared.util.WorldUtils;
import entity.Heading;
import physics.AOPhysics;
import position.Pos2D;
import position.WorldPos;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.artemis.E.E;

@Wire
public class MovementProcessorSystem extends IteratingSystem {

    private static int requestNumber;
    public static java.util.Map<Integer, MovementRequest> requests = new ConcurrentHashMap<>();

    public MovementProcessorSystem() {
        super(Aspect.all(Focused.class, AOPhysics.class,
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
                player.headingCurrent(getHeading(movement));
                WorldPos expectedPos = WorldUtils.getNextPos(pos, movement);
                boolean valid = MapUtils.isValid(expectedPos) && !player.hasImmobile();
                MovementRequest request = new MovementRequest(++requestNumber, movement, valid);
                requests.put(requestNumber, request);
                GameScreen.getClient().sendToAll(request);
                if (valid) { // Prediction
                    MapUtils.updateTile(Tile.EMPTY_INDEX, pos);
                    player.destinationWorldPos(expectedPos);
                    player.destinationDir(movement);
                    stopMeditating(player);
                }
            });
        }
    }

    private int getHeading(AOPhysics.Movement movement) {
        return movement == AOPhysics.Movement.UP ? Heading.HEADING_NORTH : movement == AOPhysics.Movement.DOWN ? Heading.HEADING_SOUTH : movement == AOPhysics.Movement.LEFT ? Heading.HEADING_WEST : Heading.HEADING_EAST;
    }

    private void stopMeditating(E player) {
        player.removeMeditating();
        MeditateSystem.stopMeditating(player);
    }

    public static WorldPos getPosition(WorldPos worldPos) {
        WorldPos correctPos = new WorldPos(worldPos.x, worldPos.y, worldPos.map);
        requests.values().stream().filter(it -> it.valid).forEach(request -> {
                WorldPos nextPos = WorldUtils.getNextPos(correctPos, request.movement);
                correctPos.x = nextPos.x;
                correctPos.y = nextPos.y;
                correctPos.map = nextPos.map;
        });
        return correctPos;
    }

    public static void validateRequest(int requestNumber, WorldPos destination) {
        requests.remove(requestNumber);
        E(GameScreen.getPlayer()).worldPosMap(destination.map);
        E(GameScreen.getPlayer()).worldPosY(destination.y);
        E(GameScreen.getPlayer()).worldPosX(destination.x);
        if (MapUtils.changeMap(E(GameScreen.getPlayer()), destination)) {
            return;
        }
        MapUtils.updateTile(GameScreen.getPlayer(), destination);
    }


}
