package com.mob.client.artemis.systems.camera;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.mob.client.artemis.components.camera.CameraComponent;
import com.mob.client.artemis.components.position.WorldPositionComponent;
import com.mob.dao.objects.Tile;

import net.mostlyoriginal.api.component.basic.Pos;

/**
 * EntityCameraSystem Class
 *
 * @author rt
 */
@Wire
public class EntityCameraSystem extends EntityProcessingSystem
{

    private ComponentMapper<WorldPositionComponent> pm;
    private CameraSystem cameraSystem;

    /**
     * Creates a new EntityProcessingSystem.
     *
     * @param aspect the aspect to match entities
     */
    public EntityCameraSystem() {
        super(Aspect.all(WorldPositionComponent.class, CameraComponent.class));
    }

    @Override
    protected void process(Entity e) {
        final WorldPositionComponent pos = pm.get(e);
        cameraSystem.camera.position.x = (pos.x * Tile.TILE_PIXEL_WIDTH);
        cameraSystem.camera.position.y = (pos.y * Tile.TILE_PIXEL_HEIGHT);
        cameraSystem.camera.update();
    }

}
