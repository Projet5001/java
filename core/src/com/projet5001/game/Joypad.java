package com.projet5001.game;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

/**
 * Created by macmata on 10/06/14.
 */
public class Joypad extends Touchpad {
    public Joypad(float deadzoneRadius, TouchpadStyle skin) {
        super(deadzoneRadius, skin);
    }

    public float getJoyPadKnobPercentX(){
        return (Math.abs(getKnobPercentX())>0.5?getKnobPercentX()*5: getKnobPercentX());
    }

    public float getJoyPadKnobPercentY(){
        return (Math.abs(getKnobPercentY())>0.5?getKnobPercentY()*5: getKnobPercentY());
    }

}
