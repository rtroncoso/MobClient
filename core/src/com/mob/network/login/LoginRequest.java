package com.mob.network.login;

import com.mob.network.interfaces.IRequest;
import com.mob.network.interfaces.IRequestProcessor;

public class LoginRequest implements IRequest {

    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public String username;
    public String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void accept(IRequestProcessor processor, int connectionId) {
        processor.processRequest(this, connectionId);
    }
}
