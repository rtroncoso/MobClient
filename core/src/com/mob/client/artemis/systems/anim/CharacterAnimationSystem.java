package com.mob.client.artemis.systems.anim;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mob.client.artemis.components.character.*;
import com.mob.client.artemis.components.character.Character;
import com.mob.client.artemis.components.movement.Moving;
import com.mob.client.textures.BundledAnimation;

/**
 * CharacterAnimationSystem Class
 *
 * @author rt
 */
@Wire
public class CharacterAnimationSystem extends EntityProcessingSystem {

    private ComponentMapper<Body> bm;
    private ComponentMapper<Heading> hm;

    /**
     * Creates a new EntityProcessingSystem.
     */
    public CharacterAnimationSystem() {
        super(Aspect.all(Character.class, Body.class,
                Moving.class, Heading.class));
    }

    @Override
    protected void process(Entity e) {
        final Body body = bm.get(e);
        final Heading heading = hm.get(e);
        BundledAnimation animation = body.body.getAnimation(heading.current);

        if(animation != null) {
            animation.setAnimationTime(animation.getAnimationTime() + world.getDelta());
        }
    }

}
