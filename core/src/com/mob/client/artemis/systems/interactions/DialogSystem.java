package com.mob.client.artemis.systems.interactions;

import camera.Focused;
import character.CanWrite;
import character.Character;
import character.Dialog;
import com.artemis.Aspect;
import com.artemis.E;
import com.artemis.Entity;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import net.mostlyoriginal.api.component.interact.Focus;

import static com.artemis.E.E;
import static com.mob.client.util.Skins.COMODORE_SKIN;

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
                                E(player).canWrite(true);
                                E(player).getStates().writing = false;
                                table.removeActor(textf, true);
                            } else if (firstOpened[0]) {
                                firstOpened[0] = false;
                            }
                            return super.keyUp(event, keycode);
                        }
                    };
                }
            };
            E(player).getStates().writing = true;
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
