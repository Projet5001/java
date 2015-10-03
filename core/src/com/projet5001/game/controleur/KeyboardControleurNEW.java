/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexandre Leblanc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
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
