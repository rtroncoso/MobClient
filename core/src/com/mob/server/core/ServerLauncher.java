package com.mob.server.core;

public class ServerLauncher {

    private static WorldServer worldServer;

    public static void main(String[] args) {
        initWorld();
    }

    private static void initWorld() {
        worldServer = new WorldServer();
        System.out.println("Initializing systems");
        worldServer.initSystems();
        System.out.println("Creating world");
        worldServer.createWorld();
        worldServer.start();
    }
}
