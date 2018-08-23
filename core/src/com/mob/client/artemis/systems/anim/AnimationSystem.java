package com.mob.client.artemis.systems.anim;

import character.Body;
import character.Heading;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.textures.BundledAnimation;
import movement.Moving;
import character.Character;

import static com.artemis.E.E;

@Wire
public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(Aspect.all(Character.class, Body.class,
                Moving.class, Heading.class));
    }

    @Override
    protected void process(int entityId) {
        E entity = E(entityId);
        final Body body = entity.getBody();
        final Heading heading = entity.getHeading();

        BundledAnimation animation = AssetsHandler.getBody(body.index).getAnimation(heading.current);

        if(animation != null) {
            animation.setAnimationTime(animation.getAnimationTime() + world.getDelta());
        }
    }

}
