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

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.events.MovementEvents;

/**
 * Created by macmata on 31/05/14.
 */
public class KeyboardControleurOLD {

    public static void register(final MyActor myActor) {
        myActor.addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode){
                    case Input.Keys.LEFT:
                        myActor.fire(new MovementEvents(MovementEvents.Type.moveLeft));
                        break;
                    case Input.Keys.RIGHT:
                        myActor.fire(new MovementEvents(MovementEvents.Type.moveRight));
                        break;
                    case Input.Keys.DOWN:
                        myActor.fire(new MovementEvents(MovementEvents.Type.moveDown));
                        break;
                    case Input.Keys.UP:
                        myActor.fire(new MovementEvents(MovementEvents.Type.moveUp));
                        break;
                }
                return true;
            }
        });
    }
}
