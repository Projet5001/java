package com.projet5001.game;

import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.Input.Keys;

/**
 * Created by macmata on 20/05/14.
 */
public class MyInputProcessor implements InputProcessor {

    private Projet5001 game;
    private MyActor actor;


    public MyInputProcessor( Projet5001 game){
            this.game = game;
            this.actor = game.myActor;
            iniOtherInput();
    }

    public void iniOtherInput(){



    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.LEFT){
            System.out.println(keycode);
            System.out.println(true);
            this.actor.sayHelloTest();

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(true);
        this.actor.sayHelloTest();
        this.actor.hit(screenX,screenY,true);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println(screenX);
        System.out.println(screenY);
        System.out.println(pointer);

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

