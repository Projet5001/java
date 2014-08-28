package com.projet5001.game.ai;

import com.badlogic.gdx.math.Vector2;

import java.util.*;

public class Astar {

    private static final int COUT = 32/16;

    public  static ArrayList<Node> run(Node nodeStart, Node dest){
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closeList = new ArrayList<>();
        ArrayList<Node> path  = new ArrayList<>();
        Node current;
        int exitH = 3;

        openList.ensureCapacity(50);
        closeList.ensureCapacity(50);

        openList.add(nodeStart);

        nodeStart.setG(0);
        nodeStart.setH(calculHeuristique(nodeStart, dest));
        nodeStart.setF(nodeStart.getG() + calculHeuristique(nodeStart, dest));
        while (!openList.isEmpty()){

            Collections.sort(openList,new FValueComarator());

            current = openList.get(0);

            if (calculHeuristique(current,dest) < exitH){
                openList.clear();
                closeList.clear();
                return reconstruct_path(path, current);
            }

            openList.remove(0);
            closeList.add(current);

            ArrayList<Node> neighbours = current.getneighbours();

            for(int i = 0; i < 4;i++ ){
                Node neighbour = neighbours.get(i);

                if(neighbour.block){
                    closeList.add(neighbour);
                }

                if (lisContains(neighbour,closeList)){
                    continue;
                }

                int tentativeGCost = current.getG() + movementCost(current, neighbour);

                if (!lisContains(neighbour,openList) || tentativeGCost < neighbour.getG()){
                    neighbour.setParent(current);
                    neighbour.setG(tentativeGCost);
                    neighbour.setF(neighbour.getG() + calculHeuristique(neighbour,dest));
                    if (!lisContains(neighbour,openList)){
                        openList.add(neighbour);
                    }
                }
            }
        }
        return null;
    }
    private static ArrayList<Node> reconstruct_path(ArrayList<Node> path, Node current){
        if (current.getParent() != null ){
            path = reconstruct_path(path, current.getParent());
            path.add(current);
        }
        return path;
    }

    private static int movementCost(Node current, Node neighbour){
        return current.walkingCost; //-neighbour malus cost
    }


    private static Boolean lisContains(Node node, ArrayList<Node> list){
        for (Node n: list){
          if(n.equals(node)){
              return true;
          }
        }
        return false;
    }

    private static double  calculHeuristique(Node current, Node dest) {
        return (Math.abs(current.x/current.getSpeed() - dest.x/dest.getSpeed()) + (Math.abs(current.y/current.getSpeed()  - dest.y/dest.getSpeed()))) * COUT;
    }

    private static class FValueComarator implements Comparator<Node>{

        @Override
        public int compare(Node node, Node node2) {
            return node.getF() < node2.getF() ? -1 : node.getF() == node2.getF() ? 0 : 1;
        }
    }
}
