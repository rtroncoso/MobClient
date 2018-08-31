package com.mob.network.init;

import com.artemis.Entity;
import com.artemis.World;
import com.mob.network.login.LoginFailed;
import com.mob.network.login.LoginOK;
import com.mob.network.login.LoginRequest;
import net.mostlyoriginal.api.network.marshal.common.MarshalDictionary;


public class NetworkDictionary extends MarshalDictionary {

    public NetworkDictionary() {
        super(
                LoginRequest.class,
                LoginFailed.class,
                LoginOK.class
        );
    }
}
