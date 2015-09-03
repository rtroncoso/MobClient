package com.mob.client.artemis.manager;

import com.artemis.Entity;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.maps.MapProperties;
import com.mob.client.artemis.components.camera.CameraComponent;
import com.mob.client.artemis.components.position.WorldPositionComponent;

import net.mostlyoriginal.api.manager.AbstractEntityFactorySystem;

/**
 * EntityFactorySystem Class
 *
 * @author rt
 */
public class EntityFactorySystem extends AbstractEntityFactorySystem {

    @Override
    protected void initialize() {
        super.initialize();

        createCamera(50, 50);
    }

    @Override
    public Entity createEntity(String entity, int cx, int cy, MapProperties properties) {
        return null;
    }

    public void createCamera(int cx, int cy) {
        new EntityBuilder(world)
            .with(new WorldPositionComponent(cx, cy))
            .with(new CameraComponent())
            .build();
    }

}
