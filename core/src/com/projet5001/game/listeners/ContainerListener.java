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
