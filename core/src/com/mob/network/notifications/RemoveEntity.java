package com.mob.network.notifications;

import com.mob.network.interfaces.INotification;
import com.mob.network.interfaces.INotificationProcessor;

public class RemoveEntity implements INotification {

    public int playerId;

    public RemoveEntity() {
    }

    public RemoveEntity(int entityId) {

        this.playerId = entityId;
    }

    @Override
    public void accept(INotificationProcessor processor) {
        processor.processNotification(this);
    }
}
