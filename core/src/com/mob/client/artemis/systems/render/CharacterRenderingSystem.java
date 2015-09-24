package com.mob.client.artemis.systems.render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.artemis.components.character.Body;
import com.mob.client.artemis.components.character.Character;
import com.mob.client.artemis.components.character.Head;
import com.mob.client.artemis.components.character.Heading;
import com.mob.client.artemis.components.position.Pos;
import com.mob.client.artemis.systems.camera.CameraSystem;

/**
 * CharacterRenderingSystem Class
 *
 * @author rt
 */
@Wire
public class CharacterRenderingSystem extends EntityProcessingSystem {

    private final SpriteBatch batch;
    private ComponentMapper<Pos> pm;
    private ComponentMapper<Heading> xm;
    private ComponentMapper<Body> bm;
    private ComponentMapper<Head> hm;

    private CameraSystem cameraSystem;

    /**
     * Creates a new EntityProcessingSystem.
     */
    public CharacterRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Character.class, Pos.class, Heading.class)
                .one(Body.class, Head.class));

        this.batch = batch;
    }

    @Override
    protected void process(Entity e) {
        cameraSystem.camera.update();
        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();

        final Body body = bm.get(e);
        final Head head = hm.get(e);
        final Heading heading = xm.get(e);
        final Pos pos = pm.get(e);

        TextureRegion bodyRegion = body.body.getAnimation(heading.current).getGraphic();
        TextureRegion headRegion = head.head.getAnimation(heading.current).getGraphic();

        float bodyPixelOffsetX = 0.0f, bodyPixelOffsetY = 0.0f,
            headPixelOffsetX = 0.0f, headPixelOffsetY = 0.0f;

        if (bodyRegion != null) {
            bodyPixelOffsetX = pos.x - 32.0f;// - bodyRegion.getRegionWidth() * 0.5f;
            bodyPixelOffsetY = pos.y - (bodyRegion.getRegionHeight() - 32.0f) - 32.0f;

            batch.draw(bodyRegion, bodyPixelOffsetX, bodyPixelOffsetY);
        }

        if (headRegion != null) {
            headPixelOffsetX = bodyPixelOffsetX + 4.0f;
            headPixelOffsetY = bodyPixelOffsetY - 8.0f;

            batch.draw(headRegion, headPixelOffsetX, headPixelOffsetY);
        }

        batch.end();
    }

}


