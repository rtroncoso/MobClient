package com.mob.server.database;

import com.mob.server.database.model.User;

public interface IDatabase {

    User findUser(String username);

}
