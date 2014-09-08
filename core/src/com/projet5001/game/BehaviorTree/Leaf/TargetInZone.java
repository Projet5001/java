/*******************************************************************************
 * Copyright 2014 Projet5001
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.projet5001.game.BehaviorTree.Leaf;

import com.projet5001.game.BehaviorTree.Routine;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.actors.Npc;

/**
 * Created by macmata on 07/09/14.
 */
public class TargetInZone extends Routine{
    @Override
    public void act(Npc npc) {
        MyActor target = npc.getTarget();
        if(npc.getTargetZone().contains(target.getCenterX(),target.getCenterY())){
            System.out.println("targe in");
            succeed();
        }else {
            npc.setTargetZone();
            fail();
        }
    }

    @Override
    public void reset() {

    }
}
