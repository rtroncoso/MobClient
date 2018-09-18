package com.mob.server.network;

import com.artemis.Component;
import com.artemis.E;
import com.artemis.Entity;
import com.mob.network.interfaces.IRequestProcessor;
import com.mob.network.interfaces.IResponse;
import com.mob.network.login.LoginFailed;
import com.mob.network.login.LoginOK;
import com.mob.network.login.LoginRequest;
import com.mob.network.movement.MovementRequest;
import com.mob.network.movement.MovementResponse;
import com.mob.network.notifications.EntityUpdate;
import com.mob.server.manager.MapManager;
import com.mob.server.manager.WorldManager;
import com.mob.server.database.model.User;
import com.mob.shared.util.WorldUtils;
import position.WorldPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.artemis.E.E;

public class ServerRequestProcessor implements IRequestProcessor {

    @Override
    public void processRequest(LoginRequest request, int connectionId) {
        IResponse response;
        User user = WorldManager.getUser(request.username);
        if (user == null) {
            response = new LoginFailed("User doesn't exists");
//        } else if (user.getPassword().equals(request.password)) {
        } else {
            final Entity entity = WorldManager.createEntity(user, connectionId);
            NetworkComunicator.sendTo(connectionId, new EntityUpdate(entity.getId(), WorldUtils.getComponents(entity)));
            NetworkComunicator.sendTo(connectionId, new LoginOK(entity.getId()));
            WorldManager.registerEntity(connectionId, entity.getId());
//        } else {
//            response = new LoginFailed("Wrong password");
        }
//        NetworkComunicator.sendTo(connectionId, response);
    }

    @Override
    public void processRequest(MovementRequest request, int connectionId) {
        // TODO check map changed

        // validate if valid
        int playerId = NetworkComunicator.getPlayerByConnection(connectionId);

        // update server entity
        E player = E(playerId);

        player.headingCurrent(WorldUtils.getHeading(request.movement));

        WorldPos worldPos = player.getWorldPos();
        WorldPos oldPos = new WorldPos(worldPos);
        WorldPos nextPos = WorldUtils.getNextPos(worldPos, request.movement);
        if (player.hasImmobile() || !(MapManager.isValidPos(nextPos) && WorldUtils.isValidWorldPos(nextPos))) {
            nextPos = worldPos;
        }

        player.worldPosMap(nextPos.map);
        player.worldPosX(nextPos.x);
        player.worldPosY(nextPos.y);
        player.destinationDir(request.movement);
        player.destinationWorldPos(player.getWorldPos());

        MapManager.movePlayer(playerId, Optional.of(oldPos));

        // notify near users
        List<Component> components = new ArrayList<>();
        components.addAll(Arrays.asList(player.getHeading(), player.getWorldPos(), player.getDestination()));
        WorldManager.notifyUpdateToNearEntities(playerId, components);

        // notify user
        NetworkComunicator.sendTo(connectionId, new MovementResponse(request.requestNumber, nextPos));

    }



}
