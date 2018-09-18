package com.mob.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;
import net.mostlyoriginal.api.network.marshal.common.MarshalState;
import net.mostlyoriginal.api.network.marshal.kryonet.KryonetMarshalStrategy;

import java.io.IOException;

/**
 * Wrapper for Kryonet client connections.
 *
 * @author Daan van Yperen
 */
public class KryonetClientMarshalStrategy extends KryonetMarshalStrategy {

    protected static final int CONNECTION_TIMEOUT = 1000;
    private final String host;
    private final int port;

    /**
     * Create client connection handler.
     *
     * @param host bind ip
     * @param port bind port (tcp and udp)
     */
    public KryonetClientMarshalStrategy(String host, int port) {
        this.host = host;
        this.port = port;

        endpoint = new Client();
    }

    @Override
    protected void connectEndpoint() {
        try {
            ((Client)endpoint).connect(CONNECTION_TIMEOUT, host, port,port+1);
            state = MarshalState.STARTED;
        } catch (IOException e) {
            state = MarshalState.FAILED_TO_START;
            e.printStackTrace();
            Log.info("Failed to connect");
        }
    }

    /** Establish connection / prepare to listen. */
    @Override
    public void start() {
        state = MarshalState.STARTING;
        registerDictionary();
        endpoint.addListener(listener); // can be safely called more than once.
        Log.info("add listener to " + this.getClass().getSimpleName() + " " +  listener);
        endpoint.start();
        new Thread(() -> connectEndpoint()).start();
    }


    @Override
    public MarshalState getState() {
        return state;
    }

    /**
     * Send object to everyone.
     * Since the client is only connected to the server, this only targets the server.
     * @todo might want to consider if we want to send from a different perspective. (client > client)
     */
    @Override
    public void sendToAll(Object o) {
        Log.info("Send object " + o);
        ((Client)endpoint).sendTCP(o);
    }
}
