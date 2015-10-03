/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexandre Leblanc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.projet5001.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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