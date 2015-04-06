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
package com.projet5001.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

/**
 * Created by macmata on 20/06/14.
 */
public class TouchpadStyle {

    Touchpad.TouchpadStyle touchpadStyle;
    Skin touchpadSkin;

    /**
     * Ceci est juste une classe pour entrposse tous ce qui et recquis pour un touchpad , genere trop de poubelle dans
     * le code.Trouver un fix
     */
    public  TouchpadStyle(){

            touchpadSkin = new Skin();
            touchpadSkin.add("touchBackground", new Texture("data/joyPadControleur/touchBackground.png"));
            touchpadSkin.add("touchKnob", new Texture("data/joyPadControleur/touchKnob.png"));
            touchpadStyle = new Touchpad.TouchpadStyle();
            touchpadStyle.background = touchpadSkin.getDrawable("touchBackground");
            touchpadStyle.knob = touchpadSkin.getDrawable("touchKnob");

    }
    public Touchpad.TouchpadStyle getTouchpadStyle() {
        return touchpadStyle;
    }

}
