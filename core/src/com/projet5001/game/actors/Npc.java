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

package com.projet5001.game.actors;

import com.badlogic.gdx.math.Vector2;
import com.projet5001.game.BehaviorTree.Ai;
import com.projet5001.game.pathfinding.Node;

import java.util.LinkedList;


public class Npc extends MyActor {
    protected MyActor Target;
    protected Vector2 targetOldPos;
    protected LinkedList<Node> pathFinding;
    protected int rangeOfAttack;

    public Npc() {
        super();
        targetOldPos = new Vector2();
        rangeOfAttack = 64;
    }

    public int getRangeOfAttack() {
        return rangeOfAttack;
    }

    public void setRangeOfAttack(int rangeOfAttack) {
        this.rangeOfAttack = rangeOfAttack;
    }

    public MyActor getTarget() {
        return Target;
    }

    public void setTarget(MyActor target) {
        Target = target;
    }

    public Vector2 getTargetOldPos() {
        return targetOldPos;
    }


    public LinkedList<Node> getPathFinding() {
        return pathFinding;
    }

    public void setPathFinding(LinkedList<Node> pathFinding) {
        this.pathFinding = pathFinding;
    }

    @Override
    public void act(float delta) {
        this.addAction(new Ai());
        super.act(delta);
    }
}


