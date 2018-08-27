package com.mob.client.artemis.systems.interactions;

import character.States;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mob.client.handlers.ParticlesHandler;
import physics.AOPhysics;
import player.PlayerControllable;

import static com.artemis.E.E;

public class MeditateSystem extends IteratingSystem {

    private static int MEDITATE_NW_FX = 1;
    private static int MEDITATE_13_FX = 2;
    private static int MEDITATE_25_FX = 3;

    public MeditateSystem() {
        super(Aspect.all(PlayerControllable.class, States.class));
    }

    @Override
    protected void process(int entityId) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.M) && !E(entityId).isMoving() && !E(entityId).getStates().writing) {
            boolean meditating = E(entityId).getStates().meditating;
            if (meditating) {
                stopMeditating(E(entityId));
            } else {
                E(entityId).fX().fXAdd(ParticlesHandler.getParticle(MEDITATE_NW_FX));
            }
            E(entityId).getStates().meditating = !meditating;
        }


    }

    public static void stopMeditating(E entity) {
        entity.removeFX();
    }

}
