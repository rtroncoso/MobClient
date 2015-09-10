package com.mob.client.artemis.manager;

import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.maps.MapProperties;
import com.mob.client.artemis.components.camera.Focused;
import com.mob.client.artemis.components.character.*;
import com.mob.client.artemis.components.character.Character;
import com.mob.client.artemis.components.physics.Physics;
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
        createCharacter(50, 50, 1, 1);
    }

    @Override
    public Entity createEntity(String entity, int cx,
                   int cy, MapProperties properties) {
        return null;
    }

    /**
     * Create a movable camera system in the given world
     * positions wx and wy (integers, tiles)
     *
     * @param wx
     * @param wy
     */
    public void createCamera(int wx, int wy) {
        Pos pos = new WorldPos(wx, wy).toScreen();
        Entity camera = cameraFactory
            .position(pos.x, pos.y)
            .tag("camera")
            .create();
    }

    /**
     * Create a character with a given position
     * and graphic components indexes
     *
     * @param wx
     * @param wy
     * @param body
     */
    public void createCharacter(int wx, int wy, int body, int head) {
        EntityBuilder builder = new EntityBuilder(world)
            .with(new WorldPos(wx, wy).toScreen())
            .with(new Heading(Heading.HEADING_SOUTH))
            .with(new Body(body))
            .with(new Head(head))
//            .with(new Physics(Physics.WALKING_VELOCITY, 0))
            .with(new Character())
            .with(new Focused());

        builder.group("characters")
            .build();
    }

}
