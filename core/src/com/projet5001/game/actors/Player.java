package com.projet5001.game.actors;


import com.badlogic.gdx.graphics.Texture;
import com.projet5001.game.events.MovementEvents;
import com.projet5001.game.listeners.MovementListener;

/**
 * Created by macmata on 27/08/14.
 */
public class Player extends MyActor{

    public Player () {
        super(null);
    }

    public Player(Texture texture) {
        super(texture);
    }
}
