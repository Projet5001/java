/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexandre Leblanc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
