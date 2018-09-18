package com.mob.client.artemis.systems.camera;

import camera.Focused;
import entity.character.Character;
import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.managers.TagManager;
import com.artemis.systems.IteratingSystem;
import com.mob.shared.util.Util;
import position.Pos2D;
import position.WorldPos;

import static com.artemis.E.E;

@Wire
public class CameraFocusSystem extends IteratingSystem {

    public CameraFocusSystem() {
        super(Aspect.all(Focused.class, Pos2D.class));
    }

    @Override
    protected void process(int player) {
        Entity camera = world.getSystem(TagManager.class).getEntity("camera");
        Pos2D cameraPos = camera.getComponent(Pos2D.class);
        Pos2D pos = Util.toScreen(E(player).getPos2D()); // TODO Cambiar por pos2d
        cameraPos.x = pos.x;
        cameraPos.y = pos.y;
    }

}
