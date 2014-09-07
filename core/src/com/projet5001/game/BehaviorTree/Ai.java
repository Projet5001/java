/*
 * Copyright [2014] [Alexandre Leblanc]
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
 */

package com.projet5001.game.BehaviorTree;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
        Selector selectTargetMoved = new Selector();

        //make pathfinding and move
        Sequence seqPathAndFire = new Sequence();
            seqPathAndFire.addRoutine(new PathFinding());
            seqPathAndFire.addRoutine(new FireMove());

        //if taget did not moved then move
        Sequence seqMovedNotFire = new Sequence();
            seqMovedNotFire.addRoutine(new TagetMove());
            seqMovedNotFire.addRoutine(new FireMove());


        selectTargetMoved.addRoutine(seqMovedNotFire);
        selectTargetMoved.addRoutine(seqPathAndFire);

        //if see enemie then if not in range then selectTagrgetMove
        sequenceMain.addRoutine(new FindEnemie());
        sequenceMain.addRoutine(new NotInRange());
        sequenceMain.addRoutine(selectTargetMoved);

        sequenceMain.start();
        sequenceMain.act((Npc) getOwner());
        return true;

    }
}
