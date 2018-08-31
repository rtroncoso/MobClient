package com.mob.network;

import character.Heading;
import com.artemis.*;
import com.artemis.utils.Bag;
import com.mob.client.artemis.archetypes.AOArchetypes;
import com.mob.client.screens.GameScreen;
import com.mob.network.interfaces.IResponse;
import com.mob.network.interfaces.IResponseProcessor;
import com.mob.network.login.LoginFailed;
import com.mob.network.login.LoginOK;

import static com.artemis.E.E;

public class ClientResponseProcessor implements IResponseProcessor {

    @Override
    public void processResponse(LoginOK response) {
        //copy player
        Archetype player = AOArchetypes.player(GameScreen.getWorld());
        int entity = GameScreen.getWorld().create(player);
        E(entity)
                .pos2DX(50)
                .pos2DY(50)
                .worldPosX(50)
                .worldPosY(50)
                .worldPosMap(1)
                .focused(true)
                .playerControllable(true)
                .headingCurrent(Heading.HEADING_NORTH)
                .headIndex(4)
                .bodyIndex(100)
                .weaponIndex(8)
                .shieldIndex(3)
                .helmetIndex(6)
                .statusHealth(120)
                .statusMaxHealth(120)
                .statusHungry(100)
                .statusMana(1000)
                .statusMaxMana(1000)
                .statusStamina(100)
                .statusThirst(100)
                .statusCriminal(true)
                .states()
                .infoName("guidota")
                .infoClan("Clarinete")
                .canWrite()
                .networkId(1)
                .aOPhysics();
    }

    @Override
    public void processResponse(LoginFailed response) {

    }
}
