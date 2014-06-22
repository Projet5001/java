package com.projet5001.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by macmata on 31/05/14.
 */
public class MyActorControler {
    public static void register(final MyActor myActor) {
        myActor.addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                System.out.println("test" + " : " + keycode);
                myActor.move(5,0);
                return false;
            }
        });
    }
}
