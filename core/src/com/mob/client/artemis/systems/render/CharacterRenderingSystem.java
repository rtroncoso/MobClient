package com.mob.client.artemis.systems.render;

import character.*;
import character.Character;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.artemis.systems.OrderedEntityProcessingSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.handlers.AnimationsHandler;
import com.mob.client.textures.BundledAnimation;
import com.mob.shared.util.Util;
import position.Pos2D;
import position.WorldPos;

import java.util.Comparator;

import static com.artemis.E.E;


@Wire
public class CharacterRenderingSystem extends OrderedEntityProcessingSystem {

    private final SpriteBatch batch;

    private CameraSystem cameraSystem;

    public CharacterRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Character.class, WorldPos.class, Heading.class));
        this.batch = batch;
    }

    @Override
    protected void process(Entity e) {
        cameraSystem.camera.update();
        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();

        E player = E(e);
        Pos2D currentPos = player.getPos2D();
        Pos2D screenPos = Util.toScreen(currentPos);
        final Heading heading = player.getHeading();

        new CharacterDrawer(player, heading, screenPos)
                .drawBody()
                .drawHead()
                .drawHelmet()
                .drawShield()
                .drawWeapon();

        batch.end();
    }

    protected Comparator<? super Entity> getComparator() {
        return Comparator.comparingInt(entity -> E(entity).getWorldPos().y);
    }

    private class CharacterDrawer {
        private E player;
        private Heading heading;
        private Pos2D screenPos;

        private float bodyPixelOffsetX;
        private float bodyPixelOffsetY;

        public CharacterDrawer(E player, Heading heading, Pos2D screenPos) {
            this.player = player;
            this.heading = heading;
            this.screenPos = screenPos;
            bodyPixelOffsetX = screenPos.x - 32.0f;
            bodyPixelOffsetY = screenPos.x;
        }

        public CharacterDrawer drawBody() {
            if (player.hasBody()) {
                final Body body = player.getBody();
                BundledAnimation animation = AnimationsHandler.getBodyAnimation(body.index, heading.current);
                TextureRegion bodyRegion = player.isMoving() ? animation.getGraphic() : animation.getGraphic(0);
                if (bodyRegion != null) {
                    drawTexture(bodyRegion, bodyPixelOffsetX, bodyPixelOffsetY = screenPos.y - (bodyRegion.getRegionHeight() - 32.0f) - 32.0f, 0, 0);
                }
            }
            return this;
        }

        public CharacterDrawer drawHead() {
            if (player.hasHead()) {
                final Head head = player.getHead();
                BundledAnimation animation = AnimationsHandler.getHeadAnimation(head.index, heading.current);
                if (animation != null) {
                    TextureRegion headRegion = animation.getGraphic();
                    drawTexture(headRegion, bodyPixelOffsetX, bodyPixelOffsetY, 4.0f, -8.0f);
                }
            }
            return this;
        }

        public CharacterDrawer drawHelmet() {
            if (player.hasHelmet()) {
                Helmet helmet = player.getHelmet();
                BundledAnimation animation = AnimationsHandler.getHelmetsAnimation(helmet.index, heading.current);
                if (animation != null) {
                    TextureRegion helmetRegion = animation.getGraphic();
                    drawTexture(helmetRegion, bodyPixelOffsetX, bodyPixelOffsetY, 4.0f, -8.0f);
                }
            }
            return this;
        }

        public CharacterDrawer drawWeapon() {
            if (player.hasWeapon()) {
                Weapon weapon = player.getWeapon();
                BundledAnimation animation = AnimationsHandler.getWeaponAnimation(weapon.index, heading.current);
                if (animation != null) {
                    TextureRegion weaponRegion = player.isMoving() ? animation.getGraphic() : animation.getGraphic(0);
                    if (weaponRegion != null) {
                        drawTexture(weaponRegion, bodyPixelOffsetX, bodyPixelOffsetY, 0, 0);
                    }
                }
            }
            return this;
        }

        public CharacterDrawer drawShield() {
            if (player.hasShield()) {
                Shield shield = player.getShield();
                BundledAnimation animation = AnimationsHandler.getShieldAnimation(shield.index, heading.current);
                if (animation != null) {
                    TextureRegion shieldRegion = player.isMoving() ? animation.getGraphic() : animation.getGraphic(0);
                    if (shieldRegion != null) {
                        drawTexture(shieldRegion, bodyPixelOffsetX, bodyPixelOffsetY, 0, 0);
                    }
                }
            }
            return this;
        }

        private void drawTexture(TextureRegion region, float x, float y, float offsetX, float offsetY) {
            if (region != null) {
                batch.draw(region, x + offsetX, y + offsetY);
            }
        }

    }
}


