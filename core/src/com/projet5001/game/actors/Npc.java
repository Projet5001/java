package com.projet5001.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.projet5001.game.Projet5001;
import com.projet5001.game.Utils.Utils;
import com.projet5001.game.collisions.WorldCollector;
import com.projet5001.game.events.MovementEvents;
import com.projet5001.game.pathfinding.Astar;
import com.projet5001.game.pathfinding.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class Npc extends MyActor {
    protected Node targetNode;
    protected Vector2 targetOldPos;
    protected LinkedList<Node> nodeList;
    protected int pos;

    public Npc() {
        super();
        targetOldPos = new Vector2();
        pos = 0;
    }
    //todo  separé ca avec le ai...
    @Override
    public void act(float delta) {

        if( seeOthers() && (targetMove() || !(nodeList.peek() == null))){


            targetOldPos.set(Projet5001.worldDirector.player.getX(),Projet5001.worldDirector.player.getY());

            Rectangle r = Projet5001.worldDirector.player.hitbox;

            targetNode =  new Node(r.x,r.y,r.width,r.height);

            pathfinding();

            if (isNodeListValid()){
                fireMove();
            }
        }else if (seeOthers() &&!targetMove()){

            if (isNodeListValid()){
                fireMove();
            }
        }
        super.act(delta);
    }

    public boolean targetMove(){
        return !Utils.equals(targetOldPos,Projet5001.worldDirector.player.getVector());
    }

    private boolean seeOthers(){
        //todo utiliser pour trouver les allies et les enemie visible pour le ai
        ArrayList<MyActor> actorArrayList =  WorldCollector.collection().circleContainActor(this.visionHitbox);
        for (MyActor myActor : actorArrayList) {
            if (myActor instanceof Player){
                return true;
            }
        }
        return false;
    }

    private void test (){
        double i = 0;
        double total = 0;

        while (i < 10000){
            double start = System.nanoTime();

            Rectangle r =  this.hitbox;

            nodeList = Astar.run(new Node(r.x, r.y, r.width, r.height), this.targetNode);
            double  end = System.nanoTime();
            i++;
            total += ((end-start)/1.0e-9);
        }
        System.out.println(((total) / i));
        return;
    }

    private void pathfinding (){
        Rectangle r =  this.hitbox;
        nodeList = Astar.run(new Node(r.x, r.y, r.width, r.height), this.targetNode);
        Collections.reverse(nodeList);
    }

    private void fireMove() {
        if (isNodeListValid()){
            Node node = nodeList.pop();
            if(node.x < this.getX()) {
                this.fire(new MovementEvents(MovementEvents.Type.moveLeft));
            }
            else if(node.x > this.getX()) {
                this.fire(new MovementEvents(MovementEvents.Type.moveRight));
            }
            else if(node.y < this.getY()) {
                this.fire(new MovementEvents(MovementEvents.Type.moveDown));
            }
            else if(node.y > this.getY()) {
                this.fire(new MovementEvents(MovementEvents.Type.moveUp));
            }
        }
    }

    private boolean isNodeListValid() {
        return (nodeList != null) && (nodeList.peek() != null);
    }
}


