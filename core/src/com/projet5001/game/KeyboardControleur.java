package com.projet5001.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by macmata on 31/05/14.
 */
public class KeyboardControleur {

    public static void register(final MyActor myActor) {
        myActor.addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode){
                    case Input.Keys.LEFT:
                        myActor.moveLeft();
                        System.out.println("move left");
                        break;
                    case Input.Keys.RIGHT:
                        myActor.moveRight();
                        System.out.println("move right");
                        break;
                    case Input.Keys.DOWN:
                        myActor.moveDown();
                        System.out.println("move down");
                        break;
                    case Input.Keys.UP:
                        myActor.moveUp();
                        System.out.println("move up");
                        break;
                }
                return true;
            }
        });
    }
}
