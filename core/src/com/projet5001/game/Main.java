package com.projet5001.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Classe principale a partir duquelle on a va appeller les autres "screen" comme le menue, le jeu
 * ou les configs
 */
public class Main extends Game {
    public SpriteBatch batcher;
    Application.ApplicationType osType;
    @Override
    public void create () {
        batcher = new SpriteBatch();
        osType = Gdx.app.getType();

        /**
         * pour le moment je ne lance que cette fenetre de test mais ca donne un bon exemple.
         * voir https://github.com/libgdx/libgdx-demo-superjumper pour d'autre exemple
         */
        setScreen(new Menu(this));
    }

    @Override
    public void render () {
        super.render();
    }
}