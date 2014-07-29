package com.projet5001.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import java.util.ArrayList;


/**
 * ContainerEvent est un event qui agis de foure tous tout en utilisant le system event driven
 */
public class ContainerEvent extends Event {
    private ArrayList<Actor> list;
    private Type type;
    private Actor relatedActor;

    public ContainerEvent(Type type) {
        super.reset();
        this.list = new ArrayList<Actor>();
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
        this.list.add(actor);
    }

    public ArrayList<Actor> getList(){
        return this.list;
    }

    /**
     * To be modifie with my own type.
     */
    static public enum Type {
        SimpleContainer , collision
    }
}
