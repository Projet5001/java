package com.projet5001.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.projet5001.game.Projet5001;
import com.projet5001.game.Utils.Utils;
import com.projet5001.game.ai.Astar;
import com.projet5001.game.ai.Node;
import com.projet5001.game.collisions.WorldCollector;
import com.projet5001.game.events.MovementEvents;

import java.util.ArrayList;
import java.util.Iterator;


public class Npc extends MyActor {

    public  Node enemie_dummy;
    public Vector2 enemieOldPos;
    public ArrayList<Node> nodeArrayList;
    public int pos;
    public Npc() {
        super();
        enemieOldPos = new Vector2();
        pos = 0;
    }
    //todo  separé ca avec le ai...
    @Override
    public void act(float delta) {

        if(seeEnemiePLayer()){
            enemieOldPos = new Vector2(Projet5001.worldDirector.player.getX(),Projet5001.worldDirector.player.getY());
            enemie_dummy =  new Node(new Rectangle(Projet5001.worldDirector.player.getHitbox()));
            pathfinding();
        }
        super.act(delta);
    }

    public boolean enemieMove(){
        return !Utils.equals(enemieOldPos,Projet5001.worldDirector.player.getVector());
    }

    private boolean seeEnemiePLayer(){
        //todo utiliser pour trouver les allies et les enemie visible pour le ai
        ArrayList<MyActor> actorArrayList =  WorldCollector.collection().circleContainActor(this.getVisionHitbox());
        for (MyActor myActor : actorArrayList) {
            if (myActor instanceof Player){
                return true;
            }
        }
        return false;
    }
    private void pathfinding (){
        pos = 0;
        nodeArrayList = Astar.run(new Node(this.getHitbox()),this.enemie_dummy );
        if (nodeArrayList != null && nodeArrayList.size() >0){
            Node node = nodeArrayList.get(pos);
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
}
