package com.projet5001.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Director extends Stage implements InputProcessor {

    Director director;

    public Director() {
        super();
        Gdx.input.setInputProcessor(this);

    }

}
