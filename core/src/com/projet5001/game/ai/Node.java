package com.projet5001.game.ai;

import com.badlogic.gdx.math.*;
import com.projet5001.game.collisions.WorldCollector;
import com.badlogic.gdx.math.Rectangle;

public class Node {

    public final int LIBRE  = 0;
    public final int BLOCK  = 1;
    public final int START  = 2;
    public final int ARRIVE = 3;

    private double h_heuristique =0;
    private int g_movementCost =10;
    private int f_totalCost =0;

    private Node parent;
    private float x;
    private float y;
    private Vector2 futur_position;
    private Vector2 old_position;
    private Rectangle hitbox;
    private int speed;


    public Node(float x, float y){
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.g_movementCost = speed;
    }
    public boolean moveLeft() {
        return moveValid(-speed, 0);
    }

    public boolean moveRight() {
        return moveValid(speed, 0);
    }

    public boolean moveUp() {
       return  moveValid(0, speed);
    }

    public boolean moveDown() {
        return moveValid(0, -speed);
    }

    public boolean moveValid(float x, float y) {
        savePosition(x, y);
        setHitboxPosition(this.futur_position);
        if (WorldCollector.collection().hit(this.hitbox)) {
            resetPosition();
            return false;
        } else {
            resetPosition();
            return true;
        }
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

    public double getH_heuristique() {
        return this.h_heuristique;
    }

    public int getG_movementCost() {
        return g_movementCost;
    }

    public double getF_totalCost(){
        return g_movementCost + h_heuristique;
    }

    public float getXpos() {
        return this.x;
    }

    public float getYpos() {
        return this.y;
    }

    public void setH_heuristique(double h_heuristique) {
        this.h_heuristique = h_heuristique;
    }

    public void setG_movementCost(int g_movementCost) {
        this.g_movementCost = g_movementCost;
    }

    public void setF_totalCost(int f_totalCost) {
        this.f_totalCost = f_totalCost;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setParent(Node p){
        this.parent = p;
    }
    public Node getParent(){
        return this.parent;
    }
}
