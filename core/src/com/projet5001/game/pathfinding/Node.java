/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexandre Leblanc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.projet5001.game.pathfinding;

import com.badlogic.gdx.math.Rectangle;
import com.projet5001.game.collisions.WorldCollector;

import java.util.Comparator;

public class Node extends Rectangle {

    protected  boolean block;
    protected float h;
    protected int g;
    protected double f;
    protected Node parent;
    protected int speed;
    protected int walkingCost;
    protected Node[] neighbours;


    public Node(float x, float y, float width , float height){
        super(x, y, width, height);
        this.speed = 32;
        this.walkingCost = 32;
        this.g = 0;
        this.h =0;
        this.f = 0;
        this.block = false;
        this.neighbours = new Node[4];
    }

    public Node[] getneighbours(){
        moveUp();
        moveRight();
        moveDown();
        moveLeft();

        return this.neighbours;
    }

    public Node[] getneighbours(int speed){
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
        this.neighbours[0] = node;
    }

    public void moveRight() {
        Node node = move(speed, 0);
        this.neighbours[1] = node;
    }

    public void moveUp() {
        Node node = move(0, speed);
        this.neighbours[2] = node;
    }

    public void moveDown() {
        Node node = move(0, -speed);
        this.neighbours[3] = node;
    }

    public Node move(float x, float y) {
        Node node = new Node(this.x + x ,this.y + y, Math.max(this.width,this.height), Math.max(this.width,this.height));

        if (this.x + x < 0 || this.y + y < 0 ){
            node.block = true;
            return node ;
        }

        if (this.x + x > 3000 || this.y + y> 3000 ){
            node.block = true;
            return node;
        }

        if (!WorldCollector.collection().hitWorld(node)) {
            node.block = false;
            return node;
        }else{
            node.block = true;
            return node;

        }
    }

    public Rectangle getRectangle (){
        return this;
    }

    public double getH() {
        return this.h;
    }

    public void setH(float h_heuristique) {
        this.h = h_heuristique;
    }

    public int getG() {
        return g;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setG(int g_movementCost) {
        this.g = g_movementCost;
    }

    public double getF() {
        return f;
    }

    public void setF(double f_totalCost) {
        this.f = f_totalCost;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node p){
        this.parent = p;
    }

    public int hashCode(){

        return (int)(((int)Math.floor((x + y) % speed)) * width);

    }

    public boolean equals(Node node){
        return this.x == node.x && this.y == node.y;
    }

}
