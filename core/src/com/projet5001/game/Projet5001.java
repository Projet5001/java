package com.projet5001.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.AtomicQueue;
import com.projet5001.game.controleur.Director;
import com.projet5001.game.controleur.JoypadControleur;
import com.projet5001.game.controleur.KeyboardControleurNEW;
import com.projet5001.game.scenes.Menu;
import com.projet5001.game.views.TouchpadStyle;

public class Projet5001 extends Game {
    public SpriteBatch batcher;

    public static Director worldDirector;
    public static Director uiDirector;
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

        setScreen(new Menu(this));
    }

    @Override
    public void render () {
        super.render();
    }
    @Override
    public void dispose() {
        uiDirector.dispose();
        worldDirector.dispose();
        batcher.dispose();

    }

}