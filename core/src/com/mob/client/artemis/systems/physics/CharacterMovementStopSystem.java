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

import java.util.function.Function;

/**
 * CharacterMovementStopSystem Class
 *
 * @author rt
 */
@Wire
public class CharacterMovementStopSystem extends EntityProcessingSystem {

    private ComponentMapper<Heading> hm;
    private ComponentMapper<Destination> dm;
    private ComponentMapper<Pos> pm;
    private ComponentMapper<Physics> xm;

    /**
     * Creates a new CharacterMovementStopSystem.
     */
    public CharacterMovementStopSystem() {
        super(Aspect.all(Destination.class, Moving.class,
                Heading.class, Physics.class,
                Pos.class));
    }

    @Override
    protected void process(Entity e) {
        final Pos pos = pm.get(e);
        final Physics physics = xm.get(e);
        final Heading heading = hm.get(e);
        final Destination dest = dm.get(e);

        switch(heading.current) {
            case Heading.HEADING_SOUTH:
                if(pos.y >= dest.pos.toScreen().y) {
                    stopMovement(e);
                }
                break;
            case Heading.HEADING_NORTH:
                if(pos.y <= dest.pos.toScreen().y) {
                    stopMovement(e);
                }
                break;
            case Heading.HEADING_EAST:
                if(pos.x >= dest.pos.toScreen().x) {
                    stopMovement(e);
                }
                break;
            case Heading.HEADING_WEST:
                if(pos.x <= dest.pos.toScreen().x) {
                    stopMovement(e);
                }
                break;
        }
    }

    private void stopMovement(Entity e) {
        e.edit().remove(Moving.class)
                .remove(Physics.class)
                .remove(Destination.class);
    }

}
