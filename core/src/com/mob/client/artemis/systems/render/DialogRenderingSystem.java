package com.mob.client.artemis.systems.render;

import entity.Dialog;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mob.client.artemis.systems.OrderedEntityProcessingSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.shared.util.Util;
import com.mob.dao.objects.Tile;
import position.Pos2D;

import java.util.Comparator;

import static com.mob.shared.util.Fonts.DIALOG_FONT;
import static com.mob.shared.util.Fonts.dialogLayout;

@Wire
public class DialogRenderingSystem extends OrderedEntityProcessingSystem {

    public static final int ALPHA_TIME = 2;
    public static final int MAX_LENGTH = 120;
    private SpriteBatch batch;
    private CameraSystem cameraSystem;

    public DialogRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Dialog.class, Pos2D.class));
        this.batch = batch;
    }

    @Override
    protected void process(Entity e) {
        E player = E.E(e);
        Pos2D playerPos = Util.toScreen(player.getPos2D());
        Pos2D cameraPos = new Pos2D(cameraSystem.camera.position.x, cameraSystem.camera.position.y);
        Pos2D screenPos = new Pos2D(cameraPos.x - playerPos.x, cameraPos.y - playerPos.y);
        Dialog dialog = player.getDialog();
        dialog.time -= world.getDelta();
        if (dialog.time > 0) {
            Color copy = DIALOG_FONT.getColor().cpy();
            if (dialog.time < ALPHA_TIME) {
                dialog.alpha = dialog.time / ALPHA_TIME;
                DIALOG_FONT.getColor().a = dialog.alpha;
            }
            cameraSystem.guiCamera.update();
            batch.setProjectionMatrix(cameraSystem.guiCamera.combined);
            batch.begin();

            dialogLayout.setText(DIALOG_FONT, dialog.text);
            float width = Math.min(dialogLayout.width, MAX_LENGTH);
            dialogLayout.setText(DIALOG_FONT, dialog.text, DIALOG_FONT.getColor(), width, Align.center, true);
            final float fontX = (cameraSystem.guiCamera.viewportWidth / 2) - screenPos.x - (width / 2) - (Tile.TILE_PIXEL_WIDTH / 2) - 4;
            final float fontY = (cameraSystem.guiCamera.viewportHeight / 2) + screenPos.y + 60 + dialogLayout.height;
            DIALOG_FONT.draw(batch, dialogLayout, fontX, fontY);

            batch.end();
            DIALOG_FONT.setColor(copy);
        } else {
            player.removeDialog();
        }
    }

    @Override
    protected Comparator<? super Entity> getComparator() {
        return Comparator.comparingInt(entity -> E.E(entity).getWorldPos().y);
    }
}
