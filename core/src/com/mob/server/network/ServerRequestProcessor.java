package com.mob.server.network;

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
import com.mob.server.core.WorldManager;
import com.mob.server.database.model.User;
import com.mob.shared.util.WorldUtils;
import physics.AOPhysics;
import position.WorldPos;

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
            WorldManager.registerUser(connectionId, entity.getId());
//        } else {
//            response = new LoginFailed("Wrong password");
        }
//        NetworkComunicator.sendTo(connectionId, response);
    }

    @Override
    public void processRequest(MovementRequest request, int connectionId) {
        // validate if valid
        int playerId = WorldManager.getPlayerByConnection(connectionId);
        // notify movement intention
        AOPhysics.Movement movement = AOPhysics.Movement.valueOf(request.movement);
        WorldManager.sendEntityMovement(playerId, movement);
        // update server entity
        E player = E(playerId);
        WorldPos worldPos = player.getWorldPos();
        WorldPos nextPos = WorldUtils.getNextPos(worldPos, movement);
        if (player.hasImmobile() || !WorldUtils.isValidWorldPos(nextPos)) {
            nextPos = worldPos;
        }
        player.worldPosMap(nextPos.map);
        player.worldPosX(nextPos.x);
        player.worldPosY(nextPos.y);

        // notify user
        NetworkComunicator.sendTo(connectionId, new MovementResponse(request.requestNumber, nextPos));

        // notify all near by user
        WorldManager.updateUserMoved(playerId, nextPos);
    }



}
