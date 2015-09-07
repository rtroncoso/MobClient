package com.mob.client.artemis.manager;

import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.managers.TagManager;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.mob.client.artemis.components.camera.Camera;
import com.mob.client.artemis.components.position.Pos;
import com.mob.client.artemis.components.position.WorldPos;

import net.mostlyoriginal.api.manager.AbstractEntityFactorySystem;

/**
 * EntityFactorySystem Class
 *
 * @author rt
 */
@Wire
public class EntityFactorySystem extends AbstractEntityFactorySystem {

    private CameraFactory cameraFactory;

    @Override
    protected void initialize() {
        super.initialize();

        createCamera(50, 50);
    }

    @Override
    public Entity createEntity(String entity, int cx,
                   int cy, MapProperties properties) {
        return null;
    }

    /**
     * Create a movable camera system in the given world
     * positions cx and cy (integers, tiles)
     *
     * @param cx
     * @param cy
     */
    public void createCamera(int cx, int cy) {
        Pos pos = new WorldPos(cx, cy).toScreen();
        Entity camera = cameraFactory
            .position(pos.x, pos.y)
            .tag("camera")
            .create();
    }

}
