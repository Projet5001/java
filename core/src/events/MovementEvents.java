package events;

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
