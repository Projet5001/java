package com.projet5001.game.ai;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.projet5001.game.collisions.WorldCollector;

public class Node extends Rectangle{

    public boolean block;
    private double h_heuristique;
    private int gCost;
    private double f_totalCost;
    private Node parent;
    private Vector2 futur_position;
    private Vector2 old_position;
    private int speed;
    private Array<Node> neighbours;


    public Node(Rectangle rect){
        super(rect.x,rect.y,Math.max(rect.width,rect.height),Math.max(rect.width,rect.height));
        this.speed = (int)Math.max(rect.width,rect.height);
        this.gCost = 0;
        this.h_heuristique =0;
        this.f_totalCost = 0;
        this.block = false;
        this.neighbours = new Array<>();
        this.old_position = new Vector2(this.x,this.y);
        this.futur_position = new Vector2(this.x,this.y);
    }



    public Array<Node> getneighbours(){
        moveDown();
        moveLeft();
        moveUp();
        moveRight();

        return this.neighbours;
    }
    public void moveLeft() {
        move(-speed, 0);
    }

    public void moveRight() {
        move(speed, 0);
    }

    public void moveUp() {
        move(0, speed);
    }

    public void moveDown() {
        move(0, -speed);
    }

    public void move(float x, float y) {
        old_position.set(this.getX(), this.getY());
        futur_position.set(this.getX() + x, this.getY() + y);
        Rectangle rectangle = new Rectangle(futur_position.x,futur_position.y,Math.max(this.width,this.height),Math.max(this.width,this.height));
        this.neighbours.add(new Node(rectangle));

    }

    public Vector2 getVector2() {

        return new Vector2(x,y);
    }

    public Vector2 getKeyFromVector(Node node){
        float x = (float)Math.floor(node.x / speed);
        float y = (float)Math.floor(node.y / speed);
        return new Vector2(x,y);
    }

    public Rectangle getRectangle (){
        return this;
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

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node p){
        this.parent = p;
    }

    public boolean equals(Node node){
        return this.x == node.x && this.y == node.y;
    }
}
