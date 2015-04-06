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

package com.projet5001.game.listeners;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

import com.projet5001.game.events.ActorEvent;

/**
 * Permet d'implanter les nouveau type de eventListener
 */
public class ContainerListener implements EventListener {

    ActorEvent actorEvent;

    @Override
    public boolean handle(Event e) {
        if (!(e instanceof ActorEvent)) {
            return false;
        }
        actorEvent = (ActorEvent) e;
        switch (actorEvent.getType()) {
            case SimpleContainer:
                return simpleContainer(actorEvent);
            case collision:
                return collision(actorEvent);
        }
        return false;
    }

    public boolean simpleContainer(ActorEvent actorEvent) {
        return false;
    }
    public boolean collision(ActorEvent actorEvent) {
        return false;
    }
}
