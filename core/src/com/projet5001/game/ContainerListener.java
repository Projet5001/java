package com.projet5001.game;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class ContainerListener implements EventListener {
    @Override
    public boolean handle(Event e) {
        if (!(e instanceof ContainerEvent)) {
            return false;
        }
        System.out.println(e.toString());
        ContainerEvent containerEvent = (ContainerEvent) e;
        switch (containerEvent.getType()) {
            case SimpleContainer:
                return SimpleContainer(containerEvent);
        }
        return false;
    }

    public boolean SimpleContainer(ContainerEvent containerEvent) {
        return false;
    }
}
