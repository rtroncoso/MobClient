package com.mob.network.interfaces;

import com.mob.network.login.LoginFailed;
import com.mob.network.login.LoginOK;
import com.mob.network.movement.MovementResponse;

public interface IResponseProcessor {
    void processResponse(LoginOK response);
    void processResponse(LoginFailed response);
    void processResponse(MovementResponse movementResponse);
}
