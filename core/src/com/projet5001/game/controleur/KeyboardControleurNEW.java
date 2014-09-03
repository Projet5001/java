/*
 * Copyright [2014] [Alexandre Leblanc]
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
 */

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
