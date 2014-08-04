package com.projet5001.game.controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projet5001.game.events.MovementEvents;

public class KeyboardControleurNEW extends Actor{
    private Actor myActor;


    public KeyboardControleurNEW() {

        super();
        this.myActor = null;
    }

    public void register(Actor myActor){
        this.myActor =  myActor;
    }
    public void act (float delta) {
        super.act(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            this.myActor.fire(new MovementEvents(MovementEvents.Type.moveLeft));

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            this.myActor.fire(new MovementEvents(MovementEvents.Type.moveRight));

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            this.myActor.fire(new MovementEvents(MovementEvents.Type.moveUp));

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
            this.myActor.fire(new MovementEvents(MovementEvents.Type.moveDown));

    }
}
