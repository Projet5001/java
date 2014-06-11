package com.projet5001.game;

import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

/**
 * Created by macmata on 10/06/14.
 */
public class Joypad extends Touchpad {



    private int facteur = 5;

    public Joypad(float deadzoneRadius, TouchpadStyle skin) {
        super(deadzoneRadius, skin);
    }

    //Todo remplace la valeur 5 par une valeur grandi au fur et a mesure que temps avance
    /**
     * Retourne la valeur en fonction de l'emplacement du pad par rapport au centre.
     * Si le pad est a moins de 50%  du centre il retourne la valeur reguliere
     * sinon retourne la valeur * par 5
     * @return
     */
    public float getJoyPadKnobPercentX(){
        return (Math.abs(getKnobPercentX())> 0.5?getKnobPercentX()* getFacteur() : getKnobPercentX());
    }

    public float getJoyPadKnobPercentY(){
        return (Math.abs(getKnobPercentY())>0.5?getKnobPercentY()* getFacteur() : getKnobPercentY());
    }

    private int getFacteur() {
        return facteur;
    }

    private void setFacteur(int facteur) {
        this.facteur = facteur;
    }

    private int getFacteurIncrement(int time){
        return  getFacteur()*time;
    }
}
