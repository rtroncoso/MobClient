package com.mob.client.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mob.client.Game;

public class Fonts {

    public static final BitmapFont WHITE_FONT;
    public static final BitmapFont NAME_FONT;
    public static final BitmapFont DIALOG_FONT;
    public static final BitmapFont WRITING_FONT;

    public static final GlyphLayout layout = new GlyphLayout();
    public static final GlyphLayout dialogLayout = new GlyphLayout();
    public static final String FONT_NAME = "Commodore Rounded v1.2.ttf";


    static {
        WHITE_FONT = generate(Color.WHITE, 12, Color.BLACK, 0, 1, 0);
        NAME_FONT = generate(rgb(9, 132, 227), 10, Color.BLACK, 0, 1, 0);
        DIALOG_FONT = generate(Color.WHITE, 10, Color.BLACK, 0, 1, 0);
        WRITING_FONT = generate(Color.BLACK, 6, Color.WHITE, 1, 0, -2);
    }

    private static Color rgb(int r, int g, int b) {
        return new Color((float) r / 255, (float) g / 255, (float) b / 255, 1);
    }

    public static BitmapFont generate(Color color, int size, Color borderColor, int borderWidth, int shadowOffset, int spaceX) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Game.GAME_FONTS_PATH + FONT_NAME));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.borderColor = borderColor;
        parameter.borderWidth = borderWidth;
        parameter.shadowOffsetX = shadowOffset;
        parameter.shadowOffsetY = shadowOffset;
        parameter.spaceX = spaceX;
        BitmapFont font = generator.generateFont(parameter); // WHITE_FONT size 12 pixels
        font.setUseIntegerPositions(false);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        return font;
    }
}
