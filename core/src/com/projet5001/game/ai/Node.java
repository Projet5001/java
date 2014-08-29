package com.projet5001.game.ai;

import com.badlogic.gdx.math.Rectangle;
import com.projet5001.game.collisions.WorldCollector;

import java.util.ArrayList;

public class Node extends Rectangle{

    public  boolean block;
    private double h_heuristique;
    private int gCost;
    private double f_totalCost;
    private Node parent;
    private int speed;
    public int walkingCost;
    private ArrayList<Node> neighbours;


    public Node(Rectangle rect){
        super(rect.x,rect.y,Math.max(rect.width,rect.height),Math.max(rect.width,rect.height));
        this.speed = 32;
        this.walkingCost = 32;
        this.gCost = 0;
        this.h_heuristique =0;
        this.f_totalCost = 0;
        this.block = false;
        this.neighbours = new ArrayList<>();
    }

    public ArrayList<Node> getneighbours(){
        moveUp();
        moveRight();
        moveDown();
        moveLeft();

        return this.neighbours;
    }

    public ArrayList<Node> getneighbours(int speed){
        this.walkingCost = speed;
        this.speed = speed;
        moveUp();
        moveRight();
        moveDown();
        moveLeft();

        return this.neighbours;
    }

    public void moveLeft() {
        Node node = move(-speed, 0);
        this.neighbours.add(node);

    }

    public void moveRight() {
        Node node = move(speed, 0);
        this.neighbours.add(node);
    }

    public void moveUp() {
        Node node = move(0, speed);
        this.neighbours.add(node);
    }

    public void moveDown() {
        Node node = move(0, -speed);
        this.neighbours.add(node);
    }

    public Node move(float x, float y) {
        Rectangle rectangle = new Rectangle(this.getX() + x,this.getY() + y,Math.max(this.width,this.height),Math.max(this.width,this.height));

        if (this.getX() + x < 0 || this.getY() + y < 0 ){
            Node node = new Node(rectangle);
            node.block = true;
            return node ;
        }

        if (this.getX() + x > 3000 || this.getY() + y> 3000 ){
            Node node = new Node(rectangle);
            node.block = true;
            this.neighbours.add(node);
            return node;
        }

        if (!WorldCollector.collection().hitWorld(rectangle)) {
            Node node = new Node(rectangle);
            node.block = false;
            return node;
        }else{
            Node node = new Node(rectangle);
            node.block = true;
            return node;

        }
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
