package com.mob.network.interfaces;

import com.mob.network.login.LoginRequest;

public interface IRequestProcessor {

    void processRequest(LoginRequest request, int connectionId);

}
