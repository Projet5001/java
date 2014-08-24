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
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            this.myActor.fire(new MovementEvents(MovementEvents.Type.moveLeft));

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            this.myActor.fire(new MovementEvents(MovementEvents.Type.moveRight));

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            this.myActor.fire(new MovementEvents(MovementEvents.Type.moveUp));

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            this.myActor.fire(new MovementEvents(MovementEvents.Type.moveDown));

        //idle
        if(!(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)
             && Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.DOWN))){
            this.myActor.fire(new MovementEvents(MovementEvents.Type.idle));
        }

    }
}
