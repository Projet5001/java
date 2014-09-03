/*
 * Copyright [2014] [Alexandre Leblanc]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.projet5001.game.pathfinding;

import com.badlogic.gdx.math.Rectangle;
import com.projet5001.game.collisions.WorldCollector;

public class Node extends Rectangle{

    protected  boolean block;
    protected float h;
    protected int g;
    protected double f;
    protected Node parent;
    protected int speed;
    protected int walkingCost;
    protected Node[] neighbours;


    public Node(float x, float y, float width , float height){
        super(x,y,width,height);
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

    public boolean equals(Node node){
        return this.x == node.x && this.y == node.y;
    }
}
