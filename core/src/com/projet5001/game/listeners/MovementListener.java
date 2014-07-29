package com.projet5001.game.listeners;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

import com.projet5001.game.events.MovementEvents;

/**
 * Permet d'implanter les nouveau type de eventListener
 */
public class MovementListener implements EventListener {

    MovementEvents event;

    @Override
    public boolean handle(Event e) {
        if (!(e instanceof MovementEvents)) {
            return false;
        }
        event = (MovementEvents) e;
        switch (event.getType()) {
            case moveLeft:
                return moveLeft(event);

            case moveRight:
                return moveRight(event);

            case moveDown:
                return moveDown(event);

            case moveUp:
                return moveUp(event);

            case moveSlow:
                return moveSlow(event);

            case moveFast:
                return moveFast(event);

            case idle:
                return idle(event);
        }
        return false;
    }

    public boolean idle(MovementEvents event) {
        return false;
    }
    public boolean moveFast(MovementEvents event) {
        return false;
    }
    public boolean moveSlow(MovementEvents event) {
        return false;
    }
    public boolean moveLeft(MovementEvents event) {
        return false;
    }
    public boolean moveRight(MovementEvents event) {
        return false;
    }
    public boolean moveUp(MovementEvents event) {
        return false;
    }
    public boolean moveDown(MovementEvents event) {
        return false;
    }
}