package com.mob.client.artemis.systems.render;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.artemis.systems.OrderedEntityProcessingSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.screens.GameScreen;
import com.mob.dao.objects.Tile;
import com.mob.shared.util.Util;
import entity.character.states.Immobile;
import position.Pos2D;

import java.util.Comparator;

import static com.artemis.E.E;
import static com.mob.shared.util.Fonts.GM_NAME_FONT;
import static com.mob.shared.util.Fonts.layout;

@Wire
public class StateRenderingSystem extends OrderedEntityProcessingSystem {

    private final SpriteBatch batch;
    private CameraSystem cameraSystem;

    public StateRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Pos2D.class).one(Immobile.class));
        this.batch = batch;
    }

    private void drawMessage(int entityId) {
        cameraSystem.guiCamera.update();
        batch.setProjectionMatrix(cameraSystem.guiCamera.combined);
        batch.begin();

        // search position
        Pos2D playerPos = Util.toScreen(E(entityId).getPos2D());
        Pos2D cameraPos = new Pos2D(cameraSystem.camera.position.x, cameraSystem.camera.position.y);
        Pos2D screenPos = new Pos2D(cameraPos.x - playerPos.x, cameraPos.y - playerPos.y);
        // draw [P] in cyan color
        layout.setText(GM_NAME_FONT, "[P]");
        final float fontX = (cameraSystem.guiCamera.viewportWidth / 2) - screenPos.x - (Tile.TILE_PIXEL_WIDTH) / 2;
        final float fontY = (cameraSystem.guiCamera.viewportHeight / 2) + screenPos.y - (layout.height) / 2 + 15;
        GM_NAME_FONT.draw(batch, layout, fontX, fontY);
        batch.end();
    }

    @Override
    protected void process(Entity e) {
        int entityId = e.getId();
        int currentPlayer = GameScreen.getPlayer();
        if (currentPlayer == entityId) {
            drawMessage(entityId);
        } else if (E(entityId).hasClan() && E(currentPlayer).hasClan()) {
            String entityClan = E(entityId).getClan().clan;
            String playerClan = E(currentPlayer).getClan().clan;
            if (entityClan.equals(playerClan)) {
                drawMessage(entityId);
            }
        }
    }

    @Override
    protected Comparator<? super Entity> getComparator() {
        return Comparator.comparingInt(entity -> E(entity).getWorldPos().y);
    }
}
