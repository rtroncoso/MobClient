package com.mob.client.artemis.systems.render;

import character.Body;
import character.Head;
import character.Character;
import character.Heading;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.artemis.systems.OrderedEntityProcessingSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.textures.BundledAnimation;
import com.mob.client.util.Util;
import position.Pos2D;
import position.WorldPos;

import java.util.Comparator;

import static com.artemis.E.E;


@Wire
public class CharacterRenderingSystem extends OrderedEntityProcessingSystem {

    private final SpriteBatch batch;

    private CameraSystem cameraSystem;

    public CharacterRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Character.class, WorldPos.class, Heading.class)
                .one(Body.class, Head.class));

        this.batch = batch;
    }

    @Override
    protected void process(Entity e) {
        cameraSystem.camera.update();
        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();

        E player = E(e);
        final Body body = player.getBody();
        final Head head = player.getHead();
        final Heading heading = player.getHeading();
        Pos2D currentPos = player.getPos2D();
        Pos2D screenPos = Util.toScreen(currentPos);

        BundledAnimation animation = AssetsHandler.getBody(body.index).getAnimation(heading.current);
        TextureRegion bodyRegion = player.isMoving() ? animation.getGraphic() : animation.getGraphic(0);
        TextureRegion headRegion = AssetsHandler.getHead(head.index).getAnimation(heading.current).getGraphic();

        float bodyPixelOffsetX = 0.0f, bodyPixelOffsetY = 0.0f,
            headPixelOffsetX = 0.0f, headPixelOffsetY = 0.0f;

        if (bodyRegion != null) {
            bodyPixelOffsetX = screenPos.x - 32.0f;// - bodyRegion.getRegionWidth() * 0.5f;
            bodyPixelOffsetY = screenPos.y - (bodyRegion.getRegionHeight() - 32.0f) - 32.0f;

            batch.draw(bodyRegion, bodyPixelOffsetX, bodyPixelOffsetY);
        }

        if (headRegion != null) {
            headPixelOffsetX = bodyPixelOffsetX + 4.0f;
            headPixelOffsetY = bodyPixelOffsetY - 8.0f;

            batch.draw(headRegion, headPixelOffsetX, headPixelOffsetY);
        }
        batch.end();
    }

    protected Comparator<? super Entity> getComparator() {
        return Comparator.comparingInt(entity -> E(entity).getWorldPos().y);
    }

}


