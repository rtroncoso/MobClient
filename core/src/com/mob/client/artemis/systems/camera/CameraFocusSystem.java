package com.mob.client.artemis.systems.camera;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.managers.TagManager;
import com.artemis.systems.EntityProcessingSystem;
import com.mob.client.artemis.components.camera.Focused;
import com.mob.client.artemis.components.character.*;
import com.mob.client.artemis.components.character.Character;
import com.mob.client.artemis.components.position.Pos;

/**
 * CameraFocusSystem Class
 *
 * Focus camera on a specified character
 */
@Wire
public class CameraFocusSystem extends EntityProcessingSystem {

    private ComponentMapper<Pos> pm;

    public CameraFocusSystem() {
        super(Aspect.all(Focused.class, Character.class));
    }

    @Override
    protected void process(Entity e) {
        Entity camera = world.getManager(TagManager.class).getEntity("camera");
        Pos cameraPos = camera.getComponent(Pos.class);

        cameraPos.x = pm.get(e).x;
        cameraPos.y = pm.get(e).y;
    }

}
