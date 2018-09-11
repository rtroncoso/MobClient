package com.mob.network.movement;

import com.mob.network.interfaces.IRequest;
import com.mob.network.interfaces.IRequestProcessor;
import physics.AOPhysics;

public class MovementRequest implements IRequest {

    public boolean valid;
    public int requestNumber;
    public String movement;

    public MovementRequest(){}

    public MovementRequest(int requestNumber, AOPhysics.Movement movement, boolean valid) {
        this.requestNumber = requestNumber;
        this.movement = movement.toString();
        this.valid = valid;
    }

    @Override
    public void accept(IRequestProcessor processor, int connectionId) {
        processor.processRequest(this, connectionId);
    }
}
