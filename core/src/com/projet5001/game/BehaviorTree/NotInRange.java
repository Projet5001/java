package com.projet5001.game.BehaviorTree;

import com.projet5001.game.Utils.Utils;
import com.projet5001.game.actors.Npc;

/**
 * Created by macmata on 06/09/14.
 */
public class NotInRange extends Routine{

    @Override
    public void act(Npc npc) {
        System.out.println(Utils.calulEuclideanDist(npc, npc.getTarget()));
        if(Utils.calulEuclideanDist(npc, npc.getTarget()) < npc.getRangeOfAttack()){
            fail();
        }else{
            succeed();
        }
    }

    @Override
    public void reset() {

    }


}
