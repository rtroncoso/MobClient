package com.mob.client.artemis.systems.camera;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mob.client.artemis.components.camera.Camera;
import com.mob.client.artemis.components.position.WorldPosition;
import com.mob.dao.objects.Tile;

/**
 * EntityCameraSystem Class
 *
 * @author rt
 */
@Wire
public class EntityCameraSystem extends EntityProcessingSystem
{

    private ComponentMapper<WorldPosition> pm;
    private CameraSystem cameraSystem;

    /**
     * Creates a new EntityCameraSystem.
     */
    public EntityCameraSystem() {
        super(Aspect.all(WorldPosition.class, Camera.class));
    }

    @Override
    protected void process(Entity e) {
        final WorldPosition pos = pm.get(e);
        cameraSystem.camera.position.x = (pos.x * Tile.TILE_PIXEL_WIDTH);
        cameraSystem.camera.position.y = (pos.y * Tile.TILE_PIXEL_HEIGHT);
        cameraSystem.camera.update();
    }

}
