package com.mob.client.artemis.systems.anim;

import character.*;
import character.Character;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.mob.client.handlers.AnimationsHandler;
import com.mob.client.handlers.DescriptorsHandler;
import com.mob.client.textures.BundledAnimation;
import movement.Moving;

import static com.artemis.E.E;

@Wire
public class MovementAnimationSystem extends IteratingSystem {

    public MovementAnimationSystem() {
        super(Aspect.all(Character.class,
                Moving.class, Heading.class));
    }

    @Override
    protected void process(int entityId) {
        E entity = E(entityId);
        final Heading heading = entity.getHeading();
        if (entity.hasBody()) {
            final Body body = entity.getBody();
            BundledAnimation animation = AnimationsHandler.getBodyAnimation(body.index, heading.current);
            if(animation != null) {
                animation.setAnimationTime(animation.getAnimationTime() + world.getDelta());
            }
        }
        if (entity.hasWeapon()) {
            final Weapon weapon = entity.getWeapon();
            BundledAnimation animation = AnimationsHandler.getWeaponAnimation(weapon.index, heading.current);
            if(animation != null) {
                animation.setAnimationTime(animation.getAnimationTime() + world.getDelta());
            }
        }
        if (entity.hasShield()) {
            final Shield weapon = entity.getShield();
            BundledAnimation animation = AnimationsHandler.getShieldAnimation(weapon.index, heading.current);
            if(animation != null) {
                animation.setAnimationTime(animation.getAnimationTime() + world.getDelta());
            }
        }


    }

}
