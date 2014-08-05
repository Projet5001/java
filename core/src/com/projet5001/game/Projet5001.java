package com.projet5001.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projet5001.game.controleur.Director;
import com.projet5001.game.controleur.JoypadControleur;
import com.projet5001.game.controleur.KeyboardControleurNEW;
import com.projet5001.game.scenes.Menu;
import com.projet5001.game.views.TouchpadStyle;


/**
 * Classe principale a partir duquelle on a va appeller les autres "screen" comme le menue, le jeu
 * ou les configs
 */
public class Projet5001 extends Game {
    public SpriteBatch batcher;
    public static float unitScale = 1/32f;
    public static Director worldDirector;
    public static Director uiDirector;
    public static boolean devMode;
    public static boolean debugMode;

    public static JoypadControleur joyPadControleur;
    public static KeyboardControleurNEW Keyboard;
    public TouchpadStyle touchpadStyle;

    @Override
    public void create () {
        batcher = new SpriteBatch();
        worldDirector = new Director();
        uiDirector = new Director();

        Keyboard = new KeyboardControleurNEW();
        touchpadStyle  = new TouchpadStyle();
        joyPadControleur = new JoypadControleur(10f,touchpadStyle.getTouchpadStyle());
        joyPadControleur.setBounds(0, 0, 200, 200);

        uiDirector.addActor(Keyboard);
        uiDirector.addActor(joyPadControleur);

        devMode = false;
        debugMode = false;
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