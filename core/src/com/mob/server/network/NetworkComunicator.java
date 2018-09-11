package com.mob.server.network;

import com.mob.server.core.KryonetServerMarshalStrategy;

public class NetworkComunicator {

    private static KryonetServerMarshalStrategy server;
    private static NetworkComunicator instance;

    public NetworkComunicator(KryonetServerMarshalStrategy server) {
        this.server = server;
        this.instance = this;
    }

    public void stop() {
        server.stop();
    }

    public static void sendTo(int id, Object packet) {
        server.sendTo(id, packet);
    }

    public static void sendToAll(Object packet) {
        server.sendToAll(packet);
    }

}
