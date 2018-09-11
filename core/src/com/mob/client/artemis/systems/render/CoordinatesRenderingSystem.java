package com.mob.client.artemis.systems.render;

import camera.Focused;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.artemis.systems.OrderedEntityProcessingSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.textures.TextureUtils;
import position.WorldPos;

import java.util.Comparator;

import static com.artemis.E.E;
import static com.mob.shared.util.Fonts.WHITE_FONT;
import static com.mob.shared.util.Fonts.layout;

@Wire
public class CoordinatesRenderingSystem extends OrderedEntityProcessingSystem {

    private static final int BORDER = 6;
    private SpriteBatch batch;
    private CameraSystem cameraSystem;
    public static final float ALPHA = 0.5f;

    public CoordinatesRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Focused.class, WorldPos.class));
        this.batch = batch;
    }

    @Override
    protected void process(Entity e) {
        E player = E(e);
        WorldPos worldPos = player.getWorldPos();
        cameraSystem.guiCamera.update();
        batch.setProjectionMatrix(cameraSystem.guiCamera.combined);
        batch.begin();

        drawCoordinates(10, 10, worldPos);

        batch.end();
    }

    private void drawCoordinates(int offsetX, int offsetY, WorldPos worldPos) {
        String worldPosString = "[" + worldPos.map + "-" + worldPos.x + "-" + worldPos.y + "]";
        layout.setText(WHITE_FONT, worldPosString);
        float fontX = cameraSystem.guiCamera.viewportWidth - layout.width;
        //background
        Color black = Color.BLACK.cpy();
        batch.setColor(black.r, black.g, black.b, ALPHA);
        batch.draw(TextureUtils.white, fontX - (BORDER / 2) - offsetX, offsetY - (BORDER / 2), layout.width + BORDER, layout.height + BORDER);

        //text
        batch.setColor(Color.WHITE.cpy());
        WHITE_FONT.draw(batch, layout, fontX - offsetX, offsetY + layout.height);
    }

    @Override
    protected Comparator<? super Entity> getComparator() {
        return Comparator.comparingInt(entity -> E(entity).getWorldPos().y);
    }
}
