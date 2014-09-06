package com.projet5001.game.BehaviorTree;

import com.badlogic.gdx.math.Rectangle;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.actors.Npc;
import com.projet5001.game.pathfinding.Astar;
import com.projet5001.game.pathfinding.Node;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by macmata on 06/09/14.
 */
public class PathFinding extends Routine {
    @Override
    public void act(Npc npc) {

        Rectangle r =  npc.getHitbox();
        MyActor actor = npc.getTarget();
        Rectangle er = actor.getHitbox();
        Node nodeEnemi  = new Node(er.getX(),er.getY(),er.width,er.height);
        Node nodeNPc  = new Node(r.x, r.y, r.width, r.height);

        LinkedList<Node> pathFinding = Astar.run(nodeNPc,nodeEnemi);

        Collections.reverse(pathFinding);

        npc.setPathFinding(pathFinding);

        if (pathFinding.size() > 0){
            succeed();
        }else{
            fail();
        }
    }

    @Override
    public void reset() {

    }
}
