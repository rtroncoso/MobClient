package com.mob.network.notifications;

import com.mob.network.interfaces.INotification;
import com.mob.network.interfaces.INotificationProcessor;

public class MovementNotification implements INotification {

    public int entityId;
    public String movement;

    public MovementNotification() {}

    public MovementNotification(int entityId, String movement) {
        this.entityId = entityId;
        this.movement = movement;
    }

    @Override
    public void accept(INotificationProcessor processor) {
        processor.processNotification(this);
    }
}
