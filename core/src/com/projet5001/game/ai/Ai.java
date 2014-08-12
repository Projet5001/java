package com.projet5001.game.ai;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.projet5001.game.actors.MyActor;

/**
 * Created by macmata on 11/08/14.
 */
public class Ai extends DefaultStateMachine<MyActor> {
    public Ai(MyActor owner) {
        super(owner);
    }
}
