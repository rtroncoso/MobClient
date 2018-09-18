package com.mob.server.core;

import com.mob.network.init.NetworkDictionary;
import com.mob.network.interfaces.IRequest;
import com.mob.network.interfaces.IRequestProcessor;
import com.mob.server.manager.MapManager;
import com.mob.server.manager.WorldManager;
import com.mob.server.network.NetworkComunicator;
import com.mob.server.network.ServerRequestProcessor;
import net.mostlyoriginal.api.network.marshal.common.MarshalStrategy;
import net.mostlyoriginal.api.network.system.MarshalSystem;

public class ServerSystem extends MarshalSystem {

    private static IRequestProcessor requestProcessor = new ServerRequestProcessor();

    public ServerSystem(MarshalStrategy strategy) {
        super(new NetworkDictionary(), strategy);
        start();
    }

    @Override
    public void received(int connectionId, Object object) {
        if (object instanceof IRequest) {
            ((IRequest) object).accept(requestProcessor, connectionId);
        }
    }

    @Override
    public void disconnected(int connectionId) {
        int playerToDisconnect = NetworkComunicator.getPlayerByConnection(connectionId);
        NetworkComunicator.unregisterUserConnection(playerToDisconnect, connectionId);
        WorldManager.unregisterEntity(playerToDisconnect);
        MapManager.removePlayer(playerToDisconnect);
        super.disconnected(connectionId);
    }
}
