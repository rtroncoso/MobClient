package com.mob.client.artemis.systems.network;

import com.mob.client.screens.GameScreen;
import com.mob.client.network.ClientNotificationProcessor;
import com.mob.client.network.ClientResponseProcessor;
import com.mob.network.init.NetworkDictionary;
import com.mob.network.interfaces.INotification;
import com.mob.network.interfaces.INotificationProcessor;
import com.mob.network.interfaces.IResponse;
import com.mob.network.interfaces.IResponseProcessor;
import com.mob.network.login.LoginRequest;
import net.mostlyoriginal.api.network.marshal.common.MarshalStrategy;
import net.mostlyoriginal.api.network.system.MarshalSystem;

public class ClientSystem extends MarshalSystem {

    public static IResponseProcessor responseProcessor = new ClientResponseProcessor();
    public static INotificationProcessor notificationProcessor = new ClientNotificationProcessor();

    public ClientSystem(MarshalStrategy strategy) {
        super(new NetworkDictionary(), strategy);
        start();
    }

    @Override
    public void connected(int connectionId) {
        super.connected(connectionId);
        GameScreen.client.sendToAll(new LoginRequest("guidota", ""));
    }

    @Override
    public void received(int connectionId, Object object) {
        if (object instanceof IResponse) {
            ((IResponse) object).accept(responseProcessor);
        } else if (object instanceof INotification) {
            ((INotification) object).accept(notificationProcessor);
        }
    }
}
