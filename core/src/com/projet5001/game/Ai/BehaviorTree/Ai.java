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

package com.projet5001.game.Ai.BehaviorTree;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projet5001.game.Ai.BehaviorTree.Leaf.FindTarget;
import com.projet5001.game.Ai.BehaviorTree.Leaf.InRange;
import com.projet5001.game.Ai.BehaviorTree.MoveCompositeLeaft.TargeAndNpcNotInZone;
import com.projet5001.game.Ai.BehaviorTree.MoveCompositeLeaft.TargetAndNpcInZone;
import com.projet5001.game.Ai.BehaviorTree.MoveCompositeLeaft.TargetImmobileAndNpcInZone;
import com.projet5001.game.Ai.BehaviorTree.MoveCompositeLeaft.TargetInZoneAndNpcNotInZone;
import com.projet5001.game.actors.Npc;

public class Ai extends Action {

    public Ai() {

    }

    protected Actor getOwner() {
        return super.getActor();
    }

    @Override
    public boolean act(float delta) {

        Sequence sequenceMain = new Sequence();



        Selector move = new Selector();
        move.addRoutine(new TargeAndNpcNotInZone());
        move.addRoutine(new TargetImmobileAndNpcInZone());
        move.addRoutine(new TargetAndNpcInZone());
        move.addRoutine( new TargetInZoneAndNpcNotInZone());

        //if see enemie then if not in range then selectTagrgetMove
        sequenceMain.addRoutine(new FindTarget());
        sequenceMain.addRoutine(new DecorateurNot(new InRange()));
        sequenceMain.addRoutine(move);

        sequenceMain.start();
        sequenceMain.act((Npc) getOwner());
        return true;

    }
}
