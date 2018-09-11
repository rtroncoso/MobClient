package com.mob.network.interfaces;

import com.mob.network.login.LoginRequest;
import com.mob.network.movement.MovementRequest;

public interface IRequestProcessor {

    void processRequest(LoginRequest request, int connectionId);

    void processRequest(MovementRequest request, int connectionId);

}
