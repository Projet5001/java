package com.projet5001.game.BehaviorTree;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projet5001.game.actors.MyActor;

import java.awt.*;

/**
 * Created by macmata on 11/08/14.
 */
public class Ai extends Action {

    public Ai(){

    }


    protected Actor getOwner(){
        return super.getActor();
    }

    @Override
    public boolean act(float delta) {



        return false;
    }
}
