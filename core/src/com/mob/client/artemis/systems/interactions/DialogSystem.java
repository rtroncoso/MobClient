package com.mob.client.artemis.systems.interactions;

import camera.Focused;
import entity.character.CanWrite;
import entity.character.Character;
import entity.Dialog;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import static com.artemis.E.E;
import static com.mob.shared.util.Skins.COMODORE_SKIN;

public class DialogSystem extends IteratingSystem {

    private TextField textf;
    private Table table;

    public DialogSystem(Table table) {
        super(Aspect.all(Character.class, Focused.class, CanWrite.class));
        this.table = table;
    }

    @Override
    protected void process(int player) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            E(player).removeCanWrite();
            final boolean[] firstOpened = {true};
            textf = new TextField("", COMODORE_SKIN, "transparent") {
                @Override
                protected InputListener createInputListener() {
                    return new TextField.TextFieldClickListener(){
                        @Override
                        public boolean keyUp(InputEvent event, int keycode) {
                            if (keycode == Input.Keys.ENTER && !firstOpened[0]) {
                                // remove textfield
                                talk(E(player), textf.getText());
                                E(player).canWrite();
                                E(player).removeWriting();
                                table.removeActor(textf, true);
                            } else if (firstOpened[0]) {
                                firstOpened[0] = false;
                            }
                            return super.keyUp(event, keycode);
                        }
                    };
                }
            };
            E(player).writing();
            table.row().colspan(1).expandX().fillX();
            table.add(textf).fillX();
            table.getStage().setKeyboardFocus(textf);
        }
    }

    public static void talk(E entity, String text) {
        entity.dialogText(text);
        entity.dialogAlpha(Dialog.DEFAULT_ALPHA);
        entity.dialogTime(Dialog.DEFAULT_TIME);
    }
}
