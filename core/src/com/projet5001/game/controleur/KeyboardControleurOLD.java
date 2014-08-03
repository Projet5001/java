package com.projet5001.game.controleur;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.events.MovementEvents;

/**
 * Created by macmata on 31/05/14.
 */
public class KeyboardControleurOLD {

    public static void register(final MyActor myActor) {
        myActor.addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode){
                    case Input.Keys.LEFT:
                        myActor.fire(new MovementEvents(MovementEvents.Type.moveLeft));
                        break;
                    case Input.Keys.RIGHT:
                        myActor.fire(new MovementEvents(MovementEvents.Type.moveRight));
                        break;
                    case Input.Keys.DOWN:
                        myActor.fire(new MovementEvents(MovementEvents.Type.moveDown));
                        break;
                    case Input.Keys.UP:
                        myActor.fire(new MovementEvents(MovementEvents.Type.moveUp));
                        break;
                }
                return true;
            }
        });
    }
}
