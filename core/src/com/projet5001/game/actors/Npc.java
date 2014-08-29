package com.projet5001.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.projet5001.game.Projet5001;
import com.projet5001.game.Utils.Utils;
import com.projet5001.game.ai.AstarArrayList;
import com.projet5001.game.ai.Node;
import com.projet5001.game.collisions.WorldCollector;
import com.projet5001.game.events.MovementEvents;

import java.util.ArrayList;


public class Npc extends MyActor {

    public  Node enemie_dummy;
    public Vector2 enemieOldPos;
    public ArrayList<Node> nodeList;
    public int pos;

    public Npc() {
        super();
        enemieOldPos = new Vector2();
        pos = 0;
    }
    //todo  separé ca avec le ai...
    @Override
    public void act(float delta) {

        if(seeOthers() && (enemieMove() || !(nodeList.size() > pos) )){
            pos = 0;
            enemieOldPos = new Vector2(Projet5001.worldDirector.player.getX(),Projet5001.worldDirector.player.getY());
            enemie_dummy =  new Node(new Rectangle(Projet5001.worldDirector.player.getHitbox()));
            pathfinding();
        }else if (seeOthers() &&!enemieMove()){

            if (nodeList != null && nodeList.size() > pos){
                System.out.println(pos);
                Node node = nodeList.get(pos);
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
            pos++;

        }
        /**
        if (Projet5001.worldDirector.player!=null){
            enemie_dummy =  new Node(new Rectangle(Projet5001.worldDirector.player.getHitbox()));
            test();
        }
        */
        super.act(delta);
    }

    public boolean enemieMove(){
        return !Utils.equals(enemieOldPos,Projet5001.worldDirector.player.getVector());
    }

    private boolean seeOthers(){
        //todo utiliser pour trouver les allies et les enemie visible pour le ai
        ArrayList<MyActor> actorArrayList =  WorldCollector.collection().circleContainActor(this.getVisionHitbox());
        for (MyActor myActor : actorArrayList) {
            if (myActor instanceof Player){
                return true;
            }
        }
        return false;
    }

    public void move(float x, float y) {
        savePosition(x, y);
        setHitboxPosition(this.futur_position);
        if (WorldCollector.collection().hit(this.getHitbox())) {
            resetPosition();
            pos--;
        } else {
            MoveByAction moveAction = new MoveByAction();
            moveAction.setAmount(x, y);
            this.addAction(moveAction);
        }
        updateHitboxPosition();
    }

    private void test (){
        double i = 0;
        double total = 0;

        while (i < 10000){
            double start = System.nanoTime();
            nodeList = AstarArrayList.run(new Node(this.getHitbox()), this.enemie_dummy);
            double  end = System.nanoTime();
            i++;
            total += ((end-start)/1.0e-9);
        }
        System.out.println(((total)/i));
        return;
    }

    private void pathfinding (){


        nodeList = AstarArrayList.run(new Node(this.getHitbox()), this.enemie_dummy);

        if (nodeList != null && nodeList.size() >0){
            Node node = nodeList.get(pos);
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
        pos++;
    }
}

