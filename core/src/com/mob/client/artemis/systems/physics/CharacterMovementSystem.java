package com.mob.client.artemis.systems.physics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mob.client.artemis.components.character.Heading;
import com.mob.client.artemis.components.character.Moving;
import com.mob.client.artemis.components.physics.Physics;

/**
 * CharacterMovementSystem Class
 *
 * @author rt
 */
@Wire
public class CharacterMovementSystem extends EntityProcessingSystem {

    private ComponentMapper<Heading> hm;
    private ComponentMapper<Moving> mm;

    /**
     * Creates a new CharacterMovementSystem.
     */
    public CharacterMovementSystem() {
        super(Aspect.all(Moving.class, Heading.class, Physics.class));
    }

    @Override
    protected void process(Entity e) {

    }

}
