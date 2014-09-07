/*******************************************************************************
 * Copyright 2014 Projet5001
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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