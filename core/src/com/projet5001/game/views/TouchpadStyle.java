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
