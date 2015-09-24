package com.mob.client.artemis.systems.physics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mob.client.artemis.components.character.Heading;
import com.mob.client.artemis.components.movement.Destination;
import com.mob.client.artemis.components.movement.Moving;
import com.mob.client.artemis.components.physics.Physics;
import com.mob.client.artemis.components.position.Pos;
import com.mob.client.artemis.components.position.WorldPos;
import com.mob.dao.objects.Tile;

/**
 * CharacterMovementStartSystem Class
 *
 * @author rt
 */
@Wire
public class CharacterMovementStartSystem extends EntityProcessingSystem {


    private ComponentMapper<Heading> hm;
    private ComponentMapper<Pos> pm;

    /**
     * Creates a new CharacterMovementStartSystem.
     */
    public CharacterMovementStartSystem() {
        super(Aspect.all(Moving.class, Heading.class, Pos.class)
            .exclude(Destination.class, Physics.class));
    }

    @Override
    protected void process(Entity e) {
        final Pos pos = pm.get(e);
        final Heading heading = hm.get(e);
        Physics physics = new Physics(0.0f, 0.0f);
        Destination dest = new Destination(pos.toWorld());

        switch(heading.current) {
            case Heading.HEADING_SOUTH:
                physics.speedY = Physics.WALKING_VELOCITY;
                dest.pos.y += 1;
                break;
            case Heading.HEADING_NORTH:
                physics.speedY = -Physics.WALKING_VELOCITY;
                dest.pos.y -= 1;
                break;
            case Heading.HEADING_EAST:
                physics.speedX = Physics.WALKING_VELOCITY;
                dest.pos.x += 1;
                break;
            case Heading.HEADING_WEST:
                physics.speedX = -Physics.WALKING_VELOCITY;
                dest.pos.x -= 1;
                break;
        }

        e.edit().add(dest)
                .add(physics);
    }
}
