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
