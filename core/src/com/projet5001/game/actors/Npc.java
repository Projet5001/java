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

package com.projet5001.game.actors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.projet5001.game.BehaviorTree.Ai;
import com.projet5001.game.pathfinding.Node;

import java.util.LinkedList;


public class Npc extends MyActor {
    protected MyActor target;
    protected Vector2 targetPosOld;
    protected Circle targetZone;
    protected Circle attackZone;
    protected float targetZoneSize;
    protected LinkedList<Node> pathFinding;

    public Npc() {
        super();
        targetPosOld = new Vector2();
        targetZone = new Circle(0,0,targetZoneSize);
        targetZoneSize = 64f;
        attackZone = new Circle();
    }

    public Circle getAttackZone() {
        return attackZone;
    }

    public Circle getTargetZone() {
        return targetZone;
    }

    public void setTargetZone() {
        this.targetZone.set(target.getCenterX(),target.getCenterY(),targetZoneSize);
    }

    public MyActor getTarget() {
        return target;
    }

    public void setTarget(MyActor target) {
        this.target = target;
    }

    public Vector2 getTargetPosOld() {
        return targetPosOld;
    }

    public void setTargetPosOld(Vector2 targetPosOld) {
        this.targetPosOld = targetPosOld;
    }

    public LinkedList<Node> getPathFinding() {
        return pathFinding;
    }

    public void setPathFinding(LinkedList<Node> pathFinding) {
        this.pathFinding = pathFinding;
    }

    @Override
    public void act(float delta) {
        this.attackZone.set(this.getHitbox().getX() + (this.getHitbox().getWidth() / 2), this.getHitbox().getY() + (this.getHitbox().getHeight() / 2), this.getHitbox().getWidth() - 20);
        this.addAction(new Ai());
        super.act(delta);
    }
}


