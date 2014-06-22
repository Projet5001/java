package com.projet5001.game;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class JoypadControleur extends Touchpad {
    private Actor myActor;

    public JoypadControleur(float deadzoneRadius, TouchpadStyle skin) {

        super(deadzoneRadius, skin);

    }

    public void register(MyActor myActor) {
        this.myActor =  myActor;
    }

    public void act(float time) {
       if(isTouched()){

           if(Math.abs(getKnobPercentX())>0.5 && getKnobX() > 100){
               this.myActor.fire(new MovementEvents(MovementEvents.Type.moveRight));
           }
           else if (Math.abs(getKnobPercentX())>0.5 && getKnobX() < 100){
               this.myActor.fire(new MovementEvents(MovementEvents.Type.moveLeft));
           }
           else if (Math.abs(getKnobPercentY())>0.5 && getKnobY() < 100){
               this.myActor.fire(new MovementEvents(MovementEvents.Type.moveDown));
           }
           else if (Math.abs(getKnobPercentY())>0.5 && getKnobY() > 100){
               this.myActor.fire(new MovementEvents(MovementEvents.Type.moveUp));
           }
           if (Math.abs(getKnobPercentY())>0.5 ^ Math.abs(getKnobPercentX())>0.5){return;}
           if (Math.abs(getKnobPercentY())>0.5 && Math.abs(getKnobPercentX())>0.5){return;}
       }
    }
}
