/*******************************************************************************
 * Copyright 2014 Projet5001
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
 ******************************************************************************/

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
