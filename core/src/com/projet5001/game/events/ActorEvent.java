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

package com.projet5001.game.events;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import java.util.ArrayList;


/**
 * ContainerEvent est un event qui agis de foure tous tout en utilisant le system event driven
 */
public class ActorEvent extends Event {
    private Actor[] list;
    private Type type;
    private Actor relatedActor;

    public ActorEvent(Type type) {
        super.reset();
        this.list = new Actor[2];
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

    public void add(Actor actor){
        this.list[0] = actor;
    }

    public Actor[] getList(){
        return this.list;
    }

    /**
     * To be modifie with my own type.
     */
    static public enum Type {
        SimpleContainer , collision , discover
    }
}
