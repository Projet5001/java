package com.projet5001.game.BehaviorTree;

import com.badlogic.gdx.math.Rectangle;
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

        Rectangle r =  npc.getHitbox();
        MyActor actor = npc.getTarget();
        Rectangle er = actor.getHitbox();

        Node nodeEnemi  = new Node(er.getX(),er.getY(),er.width,er.height);
        Node nodeNPc  = new Node(r.x, r.y, r.width, r.height);

        System.out.println(Utils.calulEuclideanDist(npc,npc.getTarget()));
        if (Utils.calulEuclideanDist(npc,npc.getTarget())< npc.getRangeOfAttack()){
            nodeEnemi.setSpeed(4);
            nodeNPc.setSpeed(4);
        }
        LinkedList<Node> pathFinding = Astar.run(nodeNPc,nodeEnemi);

        if (pathFinding != null && pathFinding.size() > 0){
            npc.setTargetOldPos(npc.getTarget().getVector());

            pathFinding = deflatePathfinding(pathFinding);

            Collections.reverse(pathFinding);
            npc.setPathFinding(pathFinding);
            succeed();

        }else{
            fail();
        }
    }

    private LinkedList<Node> deflatePathfinding(LinkedList<Node> pathfinding){
        LinkedList<Node> tempsPath = new LinkedList<>();
        for (Node node : pathfinding){
            if (node.getSpeed() !=2 ){
                int x = node.getSpeed()/2;
                for( int i =0; i<x; i++){
                    tempsPath.add(node);
                }
            }else{
                tempsPath.add(node);
            }
        }
        return tempsPath;
    }

    @Override
    public void reset() {

    }
}
