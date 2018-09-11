package com.mob.network.login;

import com.mob.network.interfaces.IResponse;
import com.mob.network.interfaces.IResponseProcessor;

public class LoginFailed implements IResponse {

    private String reason;

    public LoginFailed() {
    }

    public LoginFailed(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void accept(IResponseProcessor processor) {
        processor.processResponse(this);
    }
}
