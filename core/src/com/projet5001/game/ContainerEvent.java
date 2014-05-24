package com.projet5001.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class is to be use as a way to fire event that containt information
 * and a type (just a kind of flag ...).
 */
public class ContainerEvent  extends Event {
    Map<Objects,Objects> map;
    private Type type;
    private Actor relatedActor;

    public ContainerEvent() {
        super.reset();
        this.map =  new HashMap<Objects,Objects>();
        this.relatedActor = null;

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

    /**
     * To be modifie with my own type.
     */
    static public enum Type {
        SimpleContainer
    }
}
