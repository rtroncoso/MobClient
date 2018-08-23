package com.mob.client.artemis.systems.render;

import character.*;
import character.Character;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.artemis.systems.OrderedEntityProcessingSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.util.Util;
import com.mob.dao.objects.Tile;
import position.Pos2D;
import position.WorldPos;

import java.util.Comparator;

import static com.artemis.E.E;
import static com.mob.client.util.Fonts.NAME_FONT;
import static com.mob.client.util.Fonts.layout;

@Wire
public class NameRenderingSystem extends OrderedEntityProcessingSystem {

    private SpriteBatch batch;
    private CameraSystem cameraSystem;

    public NameRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Character.class, WorldPos.class, Status.class));
        this.batch = batch;
    }

    @Override
    protected void process(Entity e) {
        E player = E(e);
        Pos2D playerPos = Util.toScreen(player.getPos2D());
        Pos2D cameraPos = new Pos2D(cameraSystem.camera.position.x, cameraSystem.camera.position.y);
        Pos2D screenPos = new Pos2D(cameraPos.x - playerPos.x, cameraPos.y - playerPos.y);
        cameraSystem.guiCamera.update();
        batch.setProjectionMatrix(cameraSystem.guiCamera.combined);
        batch.begin();

        layout.setText(NAME_FONT, player.getInfo().name);
        final float fontX = (cameraSystem.guiCamera.viewportWidth / 2) - screenPos.x - (Tile.TILE_PIXEL_WIDTH + layout.width) / 2;
        final float fontY = (cameraSystem.guiCamera.viewportHeight / 2) + screenPos.y - (layout.height) / 2;
        NAME_FONT.draw(batch, layout, fontX, fontY);

        batch.end();
    }

    @Override
    protected Comparator<? super Entity> getComparator() {
        return Comparator.comparingInt(entity -> E(entity).getWorldPos().y);
    }
}
