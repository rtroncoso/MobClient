package com.mob.client.artemis.systems.render;

import camera.Focused;
import character.*;
import character.Character;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.util.Colors;

import static com.artemis.E.E;
import static com.mob.client.util.Fonts.WHITE_FONT;
import static com.mob.client.util.Fonts.layout;

/**
 * CharacterRenderingSystem Class
 *
 * @author rt
 */
@Wire
public class CharacterStatusRenderingSystem extends IteratingSystem {

    private CameraSystem cameraSystem;
    private static final int BAR_WIDTH = 400;
    public static final int BAR_HEIGHT = 8;
    public static final int BORDER = 2;

    private static final Texture white = new Texture("data/ui/images/blank.png");
    public static final float ALPHA = 0.7f;

    public static float OFFSET_X = (Gdx.graphics.getWidth() + BAR_WIDTH) / 2;
    public static final int OFFSET_Y = 5;
    private final SpriteBatch batch;


    /**
     * Creates a new EntityProcessingSystem.
     */
    public CharacterStatusRenderingSystem(SpriteBatch batch) {
        super(Aspect.all(Character.class, Status.class, Focused.class));
        this.batch = batch;
    }

    @Override
    protected void process(int entity) {
        cameraSystem.guiCamera.update();
        batch.setProjectionMatrix(cameraSystem.guiCamera.combined);
        batch.begin();
        OFFSET_X = (cameraSystem.guiCamera.viewportWidth / 2) - BAR_WIDTH / 2;
        drawHealth(E(entity));
        drawMana(E(entity));
        batch.end();
    }

    private void drawMana(E player) {
        int maxHealth = player.getStatus().maxMana;
        int health = player.getStatus().mana;
        drawBar(maxHealth, health, (int) OFFSET_X, OFFSET_Y + BAR_HEIGHT + 1, Colors.MANA.cpy());
    }

    private void drawHealth(E player) {
        int maxHealth = player.getStatus().maxHealth;
        int health = player.getStatus().health;
        drawBar(maxHealth, health, (int) OFFSET_X, OFFSET_Y, Colors.HEALTH.cpy());
    }

    private void drawBar(int max, int value, int offsetX, int offsetY, Color barColor) {
        //background
        Color black = Color.BLACK.cpy();
        batch.setColor(black.r, black.g, black.b, ALPHA);
        batch.draw(white, offsetX, offsetY, BAR_WIDTH + BORDER, BAR_HEIGHT);

        //color
        batch.setColor(barColor.r, barColor.g, barColor.b, ALPHA);
        batch.draw(white, offsetX + BORDER/2, offsetY + BORDER/2,( (float) value / max) * BAR_WIDTH, BAR_HEIGHT-BORDER);

        //text
        batch.setColor(Color.WHITE.cpy());
        layout.setText(WHITE_FONT,value + "/" + max);
        final float fontX = offsetX + (BAR_WIDTH + BORDER - layout.width) / 2;
        final float fontY = offsetY + (BAR_HEIGHT + layout.height) / 2;
        WHITE_FONT.draw(batch, layout, fontX, fontY);
    }

}


