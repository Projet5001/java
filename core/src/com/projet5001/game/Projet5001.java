package com.projet5001.game;

import com.badlogic.gdx.Game;


/**
 * Classe principale a partir duquelle on a va appeller les autres "screen" comme le menue, le jeu
 * ou les configs
 */
public class Projet5001 extends Game {

    @Override
    public void create () {
        /**
         * pour le moment je ne lance que cette fenetre de test mais ca donne un bon exemple.
         * voir https://github.com/libgdx/libgdx-demo-superjumper pour d'autre exemple
         */
        setScreen(new Test(this));
    }

    @Override
    public void render () {
        super.render();
    }
}