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

package com.projet5001.game.Ai.BehaviorTree.Leaf;

import com.projet5001.game.Ai.BehaviorTree.Routine;
import com.projet5001.game.Projet5001;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.actors.Npc;
import com.projet5001.game.actors.Player;
import com.projet5001.game.collisions.WorldCollector;

import java.util.ArrayList;

public class FindTarget extends Routine {

    @Override
    public void act(Npc npc) {
        ArrayList<MyActor> actorArrayList = WorldCollector.collection().circleContainActor(npc.getVisionHitbox());
        for (MyActor actor : actorArrayList) {
            if (actor instanceof Player) {
                succeed();
                npc.setTarget(Projet5001.worldDirector.player);
                return;
            }
        }
        fail();
    }

    @Override
    public void reset() {

    }
}
