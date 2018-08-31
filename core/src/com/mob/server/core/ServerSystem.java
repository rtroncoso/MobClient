package com.mob.server.core;

import com.artemis.utils.IntBag;
import com.mob.network.ServerRequestProcessor;
import com.mob.network.interfaces.IRequest;
import com.mob.network.init.NetworkDictionary;
import com.mob.network.interfaces.IRequestProcessor;
import net.mostlyoriginal.api.network.marshal.common.MarshalState;
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
        super.disconnected(connectionId);
        IntBag entitiesOf = WorldServer.getSubscriptionManager().getEntitiesOf(connectionId);
        for (int id = entitiesOf.size(); id >= 0 ; id--) {
            WorldServer.removeEntity(entitiesOf.getData()[id]);
        }
    }
}
