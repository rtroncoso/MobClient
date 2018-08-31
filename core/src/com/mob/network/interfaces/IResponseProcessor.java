package com.mob.network.interfaces;

import com.mob.network.login.LoginFailed;
import com.mob.network.login.LoginOK;

public interface IResponseProcessor {
    void processResponse(LoginOK response);
    void processResponse(LoginFailed response);
}
