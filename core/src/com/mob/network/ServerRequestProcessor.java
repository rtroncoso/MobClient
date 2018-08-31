package com.mob.network;

import com.mob.network.interfaces.IRequestProcessor;
import com.mob.network.interfaces.IResponse;
import com.mob.network.login.LoginFailed;
import com.mob.network.login.LoginOK;
import com.mob.network.login.LoginRequest;
import com.mob.server.core.WorldServer;
import com.mob.server.database.model.User;

public class ServerRequestProcessor implements IRequestProcessor {

    @Override
    public void processRequest(LoginRequest request, int connectionId) {
        IResponse response;
        User user = WorldServer.getUser(request.username);
        if (user == null) {
            response = new LoginFailed("User doesn't exists");
//        } else if (user.getPassword().equals(request.password)) {
        } else {
            WorldServer.createEntity(user);
            response = new LoginOK();
//        } else {
//            response = new LoginFailed("Wrong password");
        }
        WorldServer.getServer().sendTo(connectionId, response);
    }

}
