package com.projet5001.game;

import com.badlogic.gdx.scenes.scene2d.Event;

import java.util.*;

/**
 * This class is to be use as a way to fire event that containt information
 * and a type (just a kind of flag ...).
 */
public class ContainerEvent  extends Event {
    Map<Objects,Objects> map;
    String type;
    public ContainerEvent(String type){
        this.map =  new HashMap<Objects,Objects>();
        this.type = type;
    }
}
