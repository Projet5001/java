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
package com.projet5001.game.Ai.BehaviorTree.Leaf;

import com.badlogic.gdx.math.Rectangle;
import com.projet5001.game.Ai.BehaviorTree.Routine;
import com.projet5001.game.Utils.Utils;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.actors.Npc;
import com.projet5001.game.pathfinding.Astar;
import com.projet5001.game.pathfinding.Node;

import java.util.Collections;
import java.util.LinkedList;

public class PathFinding extends Routine {
    @Override
    public void act(Npc npc) {

        Rectangle r = npc.getHitbox();
        MyActor actor = npc.getTarget();
        Rectangle er = actor.getHitbox();

        Node nodeEnemi = new Node(er.getX(), er.getY(), er.width, er.height);
        Node nodeNPc = new Node(r.x, r.y, r.width, r.height);
        if (Math.floor(Utils.calulEuclideanDist(npc, npc.getTarget())) <= 64) {
            System.out.println("64");
            nodeEnemi.setSpeed(4);
            nodeNPc.setSpeed(4);
        }
        LinkedList<Node> pathFinding = Astar.run(nodeNPc, nodeEnemi);

        if (pathFinding != null && pathFinding.size() > 0) {
            npc.setTargetPosOld(npc.getTarget().getVector());

            pathFinding = deflatePathfinding(pathFinding);

            Collections.reverse(pathFinding);
            npc.setPathFinding(pathFinding);
            succeed();

        } else {
            fail();
        }
    }

    private LinkedList<Node> deflatePathfinding(LinkedList<Node> pathfinding) {
        LinkedList<Node> tempsPath = new LinkedList<>();
        for (Node node : pathfinding) {
            if (node.getSpeed() != 2) {
                int x = node.getSpeed() / 2;
                for (int i = 0; i < x; i++) {
                    tempsPath.add(node);
                }
            } else {
                tempsPath.add(node);
            }
        }
        return tempsPath;
    }

    @Override
    public void reset() {

    }
}
