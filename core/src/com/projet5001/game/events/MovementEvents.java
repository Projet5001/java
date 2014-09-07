/*******************************************************************************
 * Copyright 2014 Projet5001
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
 ******************************************************************************/

package com.projet5001.game.events;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by macmata on 16/06/14.
 */
public class MovementEvents extends Event {
    Map<Objects, Objects> map;
    private Type type;
    private Actor relatedActor;

    public MovementEvents(Type type){
        super.reset();
        this.map = new HashMap<Objects, Objects>();
        this.relatedActor = null;
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    static public enum Type {
        moveLeft, moveRight, moveUp, moveDown, moveSlow, moveFast, idle
    }
}
