package com.projet5001.game.ai;

import java.util.*;

public class Astar {

    private static final int COUT = 10;

    public  ArrayList<Node> run(Node nodeStart, Node nodeGoal){

        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closeList = new ArrayList<>();
        ArrayList<Node> path  = new ArrayList<>();
        Node current;

        openList.add(nodeStart);

        nodeStart.setG(0);
        nodeStart.setH(calculHeuristique(nodeStart, nodeGoal));
        nodeStart.setF(nodeStart.getG() + nodeStart.getH());

        while (!openList.isEmpty()){
            current = openList.get(0);

            if (current.equals(nodeGoal)){

                return reconstruct_path(path, current);
            }

            openList.remove(0);
            closeList.add(current);
            Collections.sort(openList,new FValueComarator());

            for(Node neighbour: current.getneighbours()){

                if (neighbour.getG() == 0){
                    neighbour.setG(current.getG() + movementCost(current,neighbour));
                }

                if (lisContains(closeList,neighbour)){
                    continue;
                }

                int tentativeGCost = current.getG() + movementCost(current, neighbour);

                if (!lisContains(openList,neighbour) || tentativeGCost < neighbour.getG()){
                    neighbour.setParent(current);
                    neighbour.setH(calculHeuristique(neighbour,nodeGoal));
                    if (!lisContains(openList,neighbour)){
                        openList.add(neighbour);
                    }
                }
            }
        }
        return null;
    }
    private  ArrayList<Node> reconstruct_path(ArrayList<Node> path, Node current){
        if (current.getParent() != null ){
            path = reconstruct_path(path, current.getParent());
            path.add(current);
        }
        return path;
    }

    private int movementCost(Node current , Node neighbour){
        return current.getSpeed(); //-neighbour malus cost
    }


    private  Boolean lisContains(ArrayList<Node>list, Node node){
        for (Node n: list){
          if(n.equals(node)){
              return true;
          }
        }
        return false;
    }

    private double  calculHeuristique(Node current, Node arrive) {
        return Math.sqrt(Math.pow(current.getXpos() - arrive.getXpos(), 2) + (Math.pow(current.getYpos() - arrive.getYpos(), 2))) * COUT;
    }

    private class FValueComarator implements Comparator<Node>{

        @Override
        public int compare(Node node, Node node2) {
            return node.getF() < node2.getF() ? -1 : node.getF() == node2.getF() ? 0 : 1;
        }
    }
}
