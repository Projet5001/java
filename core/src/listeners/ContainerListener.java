package listeners;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

import events.ContainerEvent;

/**
 * Permet d'implanter les nouveau type de eventListener
 */
public class ContainerListener implements EventListener {

    ContainerEvent containerEvent;

    @Override
    public boolean handle(Event e) {
        if (!(e instanceof ContainerEvent)) {
            return false;
        }
        System.out.println(e.toString());
        containerEvent = (ContainerEvent) e;
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
