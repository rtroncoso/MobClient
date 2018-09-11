package com.mob.server.database.model;

import com.mob.shared.model.Player;

public class User {

    private Player player;
    private int entityId;

    public String getPassword() {
        return "";
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int id) {
        this.entityId = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
