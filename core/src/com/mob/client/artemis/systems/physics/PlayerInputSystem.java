package com.mob.client.artemis.systems.physics;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import physics.AOPhysics;
import player.PlayerControllable;

import static com.artemis.E.E;

public class PlayerInputSystem extends IteratingSystem {

    public PlayerInputSystem() {
        super(Aspect.all(PlayerControllable.class, AOPhysics.class));
    }

    @Override
    protected void process(int entityId) {
        AOPhysics aoPhysics = E(entityId).getAOPhysics();
        aoPhysics.move(AOPhysics.Movement.UP, Gdx.input.isKeyPressed(Input.Keys.UP));
        aoPhysics.move(AOPhysics.Movement.DOWN, Gdx.input.isKeyPressed(Input.Keys.DOWN));
        aoPhysics.move(AOPhysics.Movement.LEFT, Gdx.input.isKeyPressed(Input.Keys.LEFT));
        aoPhysics.move(AOPhysics.Movement.RIGHT, Gdx.input.isKeyPressed(Input.Keys.RIGHT));
    }
}
