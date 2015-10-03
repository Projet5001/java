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

import com.badlogic.gdx.math.Intersector;

import java.util.*;

public class Astar {

    protected static final int COUT = 2;

    public  static LinkedList<Node> run(Node nodeStart, Node dest){
        PriorityQueue<Node> openList = new PriorityQueue<>(5000,new FValueComarator());
        HashSet<Node> closeList = new HashSet<>(5000);
        LinkedList<Node> path  = new LinkedList<>();
        Node current;
        int exitH = 2;

        openList.add(nodeStart);

        nodeStart.setParent(null);
        nodeStart.setG(0);
        nodeStart.setH(calculHeuristique(nodeStart, dest));
        nodeStart.setF(nodeStart.getG() + calculHeuristique(nodeStart, dest));
        while (!openList.isEmpty()){


            current = openList.poll();

            if (calculManhattan(current,dest) < 6 || Intersector.overlaps(current.getRectangle(),dest.getRectangle())){
                openList.clear();
                closeList.clear();
                return reconstruct_path(path, current);
            }

            openList.remove(0);
            closeList.add(current);
            Node[] neighbours;

            //this is some fine tuning
            if (calculManhattan(current,dest) < 64){
                neighbours = current.getneighbours(4);
            }else{
                neighbours = current.getneighbours();
            }

            for(int i = 0; i < 4;i++ ){
                Node neighbour = neighbours[i];

                if(neighbour.block){
                    closeList.add(neighbour);
                }

                if (closeList.contains(neighbour)){
                    continue;
                }

                int tentativeGCost = current.getG() + movementCost(current, neighbour);

                neighbour.setParent(current);
                neighbour.setG(tentativeGCost);
                neighbour.setF(neighbour.getG() + calculManhattan(neighbour,dest));
                openList.add(neighbour);

            }
        }
        return null;
    }
    private static LinkedList<Node> reconstruct_path(LinkedList<Node> path, Node last){
        Node current = last;
        while(current.getParent() != null ){
            path.add(current);
            current = current.getParent();
        }
        return path;
    }

    private static int movementCost(Node current, Node neighbour){
        return current.walkingCost; //-neighbour malus cost
    }

    private static float  calculHeuristique(Node current, Node dest) {
        return (Math.abs(current.x/current.getSpeed() - dest.x/dest.getSpeed()) + (Math.abs(current.y/current.getSpeed()  - dest.y/dest.getSpeed()))) * COUT;
    }

    private static float  calculEuclidean(Node current, Node dest) {
        return (float)Math.sqrt(Math.pow(current.x - dest.x, 2) + Math.pow(current.y  - dest.y, 2));
    }
    private static float  calculManhattan(Node current, Node dest) {
        return Math.abs(current.x - dest.x) + Math.abs(current.y  - dest.y);
    }

    private static class FValueComarator implements Comparator<Node>{

        @Override
        public int compare(Node node, Node node2) {
            return node.getF() < node2.getF() ? -1 : node.getF() == node2.getF() ? 0 : 1;
        }
    }
}
