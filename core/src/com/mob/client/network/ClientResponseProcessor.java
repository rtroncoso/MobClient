package com.mob.client.network;

import com.mob.client.artemis.systems.physics.MovementProcessorSystem;
import com.mob.client.screens.GameScreen;
import com.mob.network.interfaces.IResponseProcessor;
import com.mob.network.login.LoginFailed;
import com.mob.network.login.LoginOK;
import com.mob.network.movement.MovementResponse;

import static com.artemis.E.E;

public class ClientResponseProcessor implements IResponseProcessor {

    @Override
    public void processResponse(LoginOK response) {
//        ClientSystem.notificationProcessor.processNotification(new EntityUpdate(response.entityId, response.player.getComponents()));
        int localEntity = GameScreen.getNetworkedEntity(response.entityId);
        GameScreen.setPlayer(localEntity);
        E(localEntity).focused();
    }

    @Override
    public void processResponse(LoginFailed response) {

    }

    @Override
    public void processResponse(MovementResponse movementResponse) {
        MovementProcessorSystem.validateRequest(movementResponse.requestNumber, movementResponse.destination);
    }
}