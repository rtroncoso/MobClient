package com.mob.network.notifications;

import com.artemis.Component;
import com.mob.network.interfaces.INotification;
import com.mob.network.interfaces.INotificationProcessor;

import java.util.List;

public class EntityUpdate implements INotification {

    public int entityId;
    public List<Component> components;

    public EntityUpdate() {}

    public EntityUpdate(int entityId, List<Component> components) {
        this.entityId = entityId;
        this.components = components;
    }

    @Override
    public void accept(INotificationProcessor processor) {
        processor.processNotification(this);
    }
}
