package com.projet5001.game.BehaviorTree;

import com.projet5001.game.actors.MyActor;
import com.projet5001.game.actors.Player;
import com.projet5001.game.collisions.WorldCollector;

import java.util.ArrayList;

public class FindEnemie extends Routine {

    @Override
    public void act(float delta) {
        ArrayList<MyActor> actorArrayList =  WorldCollector.collection().circleContainActor(((MyActor) getRoutineActor()).getVisionHitbox());
        for (MyActor myActor : actorArrayList) {
            //todo define a way to list enemies
            if (myActor instanceof Player){
                succeed();
            }
        }
        fail();
    }

    @Override
    public void reset() {

    }
}
