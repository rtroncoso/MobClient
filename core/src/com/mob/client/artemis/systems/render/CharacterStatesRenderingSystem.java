package com.mob.client.artemis.systems.render;

import camera.Focused;
import character.Character;
import character.States;
import character.Status;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.util.Util;
import com.mob.dao.objects.Tile;
import position.Pos2D;

import static com.artemis.E.E;
import static com.mob.client.util.Fonts.*;
import static com.mob.client.util.Fonts.DIALOG_FONT;
import static com.mob.client.util.Fonts.dialogLayout;

/**
 * CharacterRenderingSystem Class
 *
 * @author rt
 */
@Wire
public class CharacterStatesRenderingSystem extends IteratingSystem {

    private final SpriteBatch batch;

    private CameraSystem cameraSystem;

    /**
     * Creates a new EntityProcessingSystem.
     */
    public CharacterStatesRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Character.class, States.class, Pos2D.class));
        this.batch = batch;
    }

    @Override
    protected void process(int entity) {
        States states = E(entity).getStates();
        if (states.isInAnyState()) {
            E player = E(entity);
            Pos2D playerPos = Util.toScreen(player.getPos2D());
            Pos2D cameraPos = new Pos2D(cameraSystem.camera.position.x, cameraSystem.camera.position.y);
            Pos2D screenPos = new Pos2D(cameraPos.x - playerPos.x, cameraPos.y - playerPos.y);

            if (states.writing) {
                cameraSystem.guiCamera.update();
                batch.setProjectionMatrix(cameraSystem.guiCamera.combined);
                batch.begin();
                dialogLayout.setText(WRITING_FONT, ".");
                dialogLayout.setText(WRITING_FONT, ".", WRITING_FONT.getColor(), dialogLayout.width, Align.center, true);
                final float fontX = (cameraSystem.guiCamera.viewportWidth / 2) - screenPos.x + 8 - (dialogLayout.width / 2) - (Tile.TILE_PIXEL_WIDTH / 2) - 2;
                final float fontY = (cameraSystem.guiCamera.viewportHeight / 2) + screenPos.y + 40 + dialogLayout.height;
                WRITING_FONT.draw(batch, dialogLayout, fontX, fontY);
                batch.end();
            }
        }
    }
}


