package com.mob.client.artemis.systems.network;

import com.mob.network.ClientResponseProcessor;
import com.mob.network.interfaces.IResponse;
import com.mob.network.init.NetworkDictionary;
import com.mob.network.interfaces.IResponseProcessor;
import net.mostlyoriginal.api.network.marshal.common.MarshalState;
import net.mostlyoriginal.api.network.marshal.common.MarshalStrategy;
import net.mostlyoriginal.api.network.marshal.kryonet.KryonetClientMarshalStrategy;
import net.mostlyoriginal.api.network.system.MarshalSystem;

public class ClientSystem extends MarshalSystem {

    private static IResponseProcessor processor = new ClientResponseProcessor();

    public ClientSystem(MarshalStrategy strategy) {

        super(new NetworkDictionary(), strategy);
        start();
    }

    @Override
    public void received(int connectionId, Object object) {
        if (object instanceof IResponse) {
            ((IResponse) object).accept(processor);
        }
    }
}
