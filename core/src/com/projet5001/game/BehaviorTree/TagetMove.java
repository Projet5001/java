package com.projet5001.game.BehaviorTree;

import com.projet5001.game.Utils.Utils;
import com.projet5001.game.actors.Npc;

public class TagetMove extends Routine {
    @Override
    public void act(Npc npc) {

        if(Utils.equals(npc.getTargetOldPos(), npc.getTarget().getVector())){
            System.out.println("taget did not move");
            succeed();
        }else{
            fail();
        }
    }

    @Override
    public void reset() {

    }
}
