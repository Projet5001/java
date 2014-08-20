package com.projet5001.game.ai;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.projet5001.game.collisions.WorldCollector;

public class Node {

    public boolean block;
    private double h_heuristique =0;
    private int gCost =0;
    private double f_totalCost = 0;
    private Node parent;
    private float x;
    private float y;
    private float width;
    private float height;
    private Vector2 position;
    private Vector2 futur_position;
    private Vector2 old_position;
    private Rectangle hitbox;
    private int speed;
    private Array<Node> neighbours;


    public Node(float x, float y, float width, float height, int speed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.gCost = 0;
        this.block = false;
        this.neighbours = null;
        this.hitbox  =  new Rectangle(this.x,this.y,this.width,this.height);
        this.position = new Vector2(this.x,this.y);
    }

    public void moveLeft() {
        moveValid(-speed, 0);
    }

    public void moveRight() {
        moveValid(speed, 0);
    }

    public void moveUp() {
        moveValid(0, speed);
    }

    public void moveDown() {
        moveValid(0, -speed);
    }

    public void moveValid(float x, float y) {
        savePosition(x, y);
        setHitboxPosition(this.futur_position);
        if (this.neighbours == null) {
            this.neighbours = new Array<>();
        }
        Node node = new Node(this.futur_position.x, this.futur_position.y, this.width, this.height, this.speed);
        if (!WorldCollector.collection().hit(this.hitbox)) {

            node.block = false;
            resetPosition();
            this.neighbours.add(node);
        } else {
            //ici changer le g cost en fonction de ce qui est hit
            node.block = true;
            resetPosition();
            this.neighbours.add(node);
        }
    }

    public Array<Node> getneighbours(){
        moveDown();
        moveLeft();
        moveRight();
        moveUp();

        return this.neighbours;
    }

    public void setHitboxPosition(Vector2 vector2) {
        this.hitbox.setPosition(vector2.x, vector2.y);
    }

    public void resetPosition() {
        this.setPosition(this.old_position);
        this.updateHitboxPosition();
    }

    public void updateHitboxPosition() {
        this.hitbox.setPosition(x, y);
    }


    private void savePosition(float x, float y) {
        this.old_position.set(this.x, this.y);
        this.futur_position.set(this.x + x, this.y + y);
    }
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.updateHitboxPosition();
    }

    public void setPosition(Vector2 vector) {
        this.setPosition(vector.x, vector.y);
    }

    public Vector2 getVector(){
        return this.position.set(this.x,this.y);
    }

    public double getH() {
        return this.h_heuristique;
    }

    public void setH(double h_heuristique) {
        this.h_heuristique = h_heuristique;
    }

    public int getG() {
        return gCost;
    }

    public int getSpeed(){
        return speed;
    }

    public void setG(int g_movementCost) {
        this.gCost = g_movementCost;
    }

    public double getF() {
        return f_totalCost;
    }

    public void setF(double f_totalCost) {
        this.f_totalCost = f_totalCost;
    }

    public float getXpos() {
        return this.x;
    }

    public float getYpos() {
        return this.y;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node p){
        this.parent = p;
    }

    public boolean equals(Node node){
        return this.position.x == node.getVector().x && this.position.y == node.getVector().y;
    }
}
