package com.mob.client.network;

import com.artemis.Component;
import com.artemis.E;
import com.artemis.Entity;
import com.artemis.EntityEdit;
import com.esotericsoftware.minlog.Log;
import com.mob.client.handlers.MapHandler;
import com.mob.client.screens.GameScreen;
import com.mob.network.interfaces.INotification;
import com.mob.network.interfaces.INotificationProcessor;
import com.mob.network.notifications.EntityUpdate;
import com.mob.network.notifications.MovementNotification;
import physics.AOPhysics;
import position.WorldPos;

import static com.artemis.E.E;

public class ClientNotificationProcessor implements INotificationProcessor {

    @Override
    public void defaultProcess(INotification notification) {

    }

    @Override
    public void processNotification(EntityUpdate entityUpdate) {
        if (!GameScreen.entityExsists(entityUpdate.entityId)) {
            Log.info("Network entity doesn't exists: " + entityUpdate.entityId + ". So we create it");
            Entity newEntity = GameScreen.getWorld().createEntity();
            GameScreen.registerEntity(entityUpdate.entityId, newEntity.getId());
            addComponentsToEntity(newEntity, entityUpdate);
        } else {
            Log.info("Network entity exists: " + entityUpdate.entityId + ". Updating");
            updateEntity(entityUpdate);
        }
    }

    private void addComponentsToEntity(Entity newEntity, EntityUpdate entityUpdate) {
        EntityEdit edit = newEntity.edit();
        for (Component component : entityUpdate.components) {
            Log.info("Adding component: " + component);
            edit.add(component);
        }
        if (E(newEntity.getId()).hasWorldPos()) {
            WorldPos worldPos = E(newEntity.getId()).getWorldPos();
            E(newEntity.getId()).pos2DX(worldPos.x);
            E(newEntity.getId()).pos2DY(worldPos.y);
            E(newEntity.getId()).character();
            E(newEntity.getId()).aOPhysics();
            MapHandler.get(worldPos.map).getTile(worldPos.x, worldPos.y).setCharIndex(newEntity.getId());
        }
    }

    private void updateEntity(EntityUpdate entityUpdate) {
        int entityId = GameScreen.getNetworkedEntity(entityUpdate.entityId);
        Entity entity = GameScreen.getWorld().getEntity(entityId);
        EntityEdit edit = entity.edit();
        for (Component component : entityUpdate.components) {
            edit.add(component);
        }
    }

    @Override
    public void processNotification(MovementNotification movementNotification) {
        E entity = E(movementNotification.entityId);
        if (entity.hasAOPhysics()) {
            entity.getAOPhysics().addIntention(AOPhysics.Movement.valueOf(movementNotification.movement));
        }
    }
}
