package com.projet5001.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.projet5001.game.ai.Astar;
import com.projet5001.game.ai.Node;
import com.projet5001.game.collisions.WorldCollector;

import java.util.ArrayList;


public class Npc extends MyActor {

    private Node enemie_dummy;
    public Npc() {
        super();
        this.enemie_dummy = new Node(new Rectangle(40,40, this.getWidth(),this.getHeight()));
    }

    @Override
    public void act(float delta) {

        ArrayList<Node> nodeArrayList = Astar.run(new Node(this.getHitbox()),this.enemie_dummy );
        if (nodeArrayList != null && nodeArrayList.size() >0){
            Node node = nodeArrayList.get(0);
            System.out.println(node);
            if(node.x < this.getX()) {
                moveLeft();
            }
            else if(node.x > this.getX()) {
                moveRight();
            }
            else if(node.y < this.getY()) {
                moveDown();
            }
            else if(node.y > this.getY()) {
                moveUp();
            }
        }
        super.act(delta);
    }
}
