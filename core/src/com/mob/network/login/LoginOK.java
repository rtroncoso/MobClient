package com.mob.network.login;

import com.mob.network.interfaces.IResponse;
import com.mob.network.interfaces.IResponseProcessor;

public class LoginOK implements IResponse {

    public int entityId;

    public LoginOK() {}

    public LoginOK(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void accept(IResponseProcessor processor) {
        processor.processResponse(this);
    }
}
