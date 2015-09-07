package com.mob.client.artemis.systems.camera;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mob.client.artemis.components.camera.Camera;
import com.mob.client.artemis.components.position.Pos;
import com.mob.client.artemis.components.position.WorldPos;
import com.mob.dao.objects.Tile;

/**
 * CameraMovementSystem Class
 *
 * @author rt
 */
@Wire
public class CameraMovementSystem extends EntityProcessingSystem
{

    private ComponentMapper<Pos> pm;
    private CameraSystem cameraSystem;

    /**
     * Creates a new CameraMovementSystem.
     */
    public CameraMovementSystem() {
        super(Aspect.all(Pos.class, Camera.class));
    }

    @Override
    protected void process(Entity e) {
        final Pos pos = pm.get(e);
        cameraSystem.camera.position.x = (pos.x);
        cameraSystem.camera.position.y = (pos.y);
        cameraSystem.camera.update();
    }

}
