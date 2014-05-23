package com.projet5001.game;

import com.badlogic.gdx.InputProcessor;

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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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

