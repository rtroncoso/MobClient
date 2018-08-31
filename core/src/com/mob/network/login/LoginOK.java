package com.mob.network.login;

import com.artemis.Entity;
import com.mob.network.interfaces.IResponse;
import com.mob.network.interfaces.IResponseProcessor;
import com.mob.server.core.WorldServer;

public class LoginOK implements IResponse {

    public LoginOK() {}

    @Override
    public void accept(IResponseProcessor processor) {
        processor.processResponse(this);
    }
}
