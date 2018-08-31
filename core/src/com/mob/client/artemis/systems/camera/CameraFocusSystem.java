package com.mob.client.artemis.systems.camera;

import camera.Focused;
import character.Character;
import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.mob.shared.util.Util;
import position.Pos2D;

import static com.artemis.E.E;

/**
 * CameraFocusSystem Class
 *
 * Focus camera on a specified character
 */
@Wire
public class CameraFocusSystem extends IteratingSystem {

    public CameraFocusSystem() {
        super(Aspect.all(Focused.class, Character.class, Pos2D.class));
    }

    @Override
    protected void process(int player) {
        Entity camera = world.getSystem(TagManager.class).getEntity("camera");
        Pos2D cameraPos = camera.getComponent(Pos2D.class);
        Pos2D pos = Util.toScreen(E(player).getPos2D());
        cameraPos.x = pos.x;
        cameraPos.y = pos.y;
    }

}
