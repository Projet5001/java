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

package com.projet5001.game.actors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.projet5001.game.Ai.BehaviorTree.Ai;
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


