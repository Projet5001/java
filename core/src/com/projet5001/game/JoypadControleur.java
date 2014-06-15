package com.projet5001.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class JoypadControleur extends Touchpad {
    private Actor actorFocus;
    private int facteur = 5;

    public JoypadControleur(float deadzoneRadius, TouchpadStyle skin) {

        super(deadzoneRadius, skin);
        addListener(new InputListener(){
            /** Called when a mouse button or a finger touch goes down on the actor. If true is returned, this listener will receive all
             * touchDragged and touchUp events, even those not over this actor, until touchUp is received. Also when true is returned, the
             * event is {@link com.badlogic.gdx.scenes.scene2d.Event#handle() handled}.
             * @see InputEvent */
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return false;
            }

            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link com.badlogic.gdx.scenes.scene2d.Event#handle() handled}.
             * @see InputEvent */
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("touchUp");
            }

            /** Called when a mouse button or a finger touch is moved anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchDragged event is always {@link com.badlogic.gdx.scenes.scene2d.Event#handle() handled}.
             * @see InputEvent */
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                System.out.println("touchDragged");


            }
        });
    }

    /**
     * Retourne la valeur en fonction de l'emplacement du pad par rapport au centre.
     * Si le pad est a moins de 50%  du centre il retourne la valeur reguliere
     * sinon retourne la valeur * par 5
     * @return
     */
    public float getJoyPadKnobPercentX(){
        return (Math.abs(getKnobPercentX())> 0.5?getKnobPercentX()* facteur : getKnobPercentX());
    }
    public float getVectorAngle(){
        return 0;
    }
    public float getVectorLen(){
        return 0;
    }
    public float getJoyPadKnobPercentY(){
        return (Math.abs(getKnobPercentY())>0.5?getKnobPercentY()* facteur : getKnobPercentY());
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

    public void setJoyPadFocus(Actor actor){actorFocus = actor;}

    public Actor removeJoyPadFocus(){return actorFocus;}

    public void act(float time) {
       if(isTouched()){
           System.out.println(getKnobX() + " " + getKnobY());
       }
    }
}
