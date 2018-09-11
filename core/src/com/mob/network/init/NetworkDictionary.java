package com.mob.network.init;

import camera.Focused;
import com.mob.network.login.LoginFailed;
import com.mob.network.login.LoginOK;
import com.mob.network.login.LoginRequest;
import com.mob.network.movement.MovementRequest;
import com.mob.network.movement.MovementResponse;
import com.mob.network.notifications.EntityUpdate;
import com.mob.shared.model.Player;
import entity.*;
import entity.character.CanWrite;
import entity.character.info.Clan;
import entity.character.info.Description;
import entity.character.info.Name;
import entity.character.states.*;
import entity.character.status.*;
import graphics.FX;
import movement.Destination;
import movement.Moving;
import movement.RandomMovement;
import net.mostlyoriginal.api.network.marshal.common.MarshalDictionary;
import network.Network;
import physics.AOPhysics;
import physics.PhysicAttack;
import position.Pos2D;
import position.WorldPos;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;


public class NetworkDictionary extends MarshalDictionary {

    public NetworkDictionary() {
        registerAll(
                // Requests
                LoginRequest.class,
                LoginFailed.class,
                MovementRequest.class,

                // Responses
                LoginOK.class,
                MovementResponse.class,
                // Notifications
                EntityUpdate.class,
                Player.class,
                ArrayList.class,
                ConcurrentLinkedDeque.class,

                Optional.class,
                Pos2D.class,
                // Components
                AOPhysics.class,
                AOPhysics.Movement.class,
                Name.class,
                WorldPos.class,
                Focused.class,
                Clan.class,
                Description.class,
                Immobile.class,
                Meditating.class,
                Navigating.class,
                Resting.class,
                Writing.class,
                Criminal.class,
                Elv.class,
                Exp.class,
                GM.class,
                Health.class,
                Hungry.class,
                Level.class,
                Mana.class,
                Stamina.class,
                Thirst.class,
                CanWrite.class,
                entity.character.Character.class,
                Body.class,
                Dialog.class,
                Head.class,
                Heading.class,
                Helmet.class,
                Shield.class,
                Weapon.class,
                FX.class,
                Destination.class,
                Moving.class,
                RandomMovement.class,
                Network.class,
                PhysicAttack.class
        );
    }

    private void registerAll(Class... classes) {
        topId = 14;
        for (Class clazz : classes) {
            register(topId++, clazz);
        }
    }
}
