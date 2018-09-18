package com.mob.network.interfaces;

import com.mob.network.notifications.EntityUpdate;
import com.mob.network.notifications.RemoveEntity;

public interface INotificationProcessor {

    void defaultProcess(INotification notification);

    void processNotification(EntityUpdate notification);

    void processNotification(RemoveEntity removeEntity);
}