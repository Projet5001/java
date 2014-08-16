package com.projet5001.game.ai;


import java.util.ArrayList;

public class Astar {

    public int h;   // heuristic
    public int g;   // cost
    public int f;   //fonction g +h

    public ArrayList<Node> openList;
    public ArrayList<Node> closeList;

    public static final int COUT = 10;

    public Node depart;
    public Node arrive;

    public void run(Node nodeStart, Node nodeGoal){

        openList = new ArrayList<>();
        while (!openList.isEmpty()){

        }

        /**
         Okay, now that you have gone through the explanation, let’s lay out the step-by-step method all in one place:
         1) Add the starting square (or node) to the open list.

         2) Repeat the following:

         a) Look for the lowest F cost square on the open list. We refer to this as the current square.

         b) Switch it to the closed list.

         c) For each of the 8 squares adjacent to this current square …

         If it is not walkable or if it is on the closed list, ignore it. Otherwise do the following.

         If it isn’t on the open list, add it to the open list. Make the current square the parent of this square. Record the F, G, and H costs of the square.

         If it is on the open list already, check to see if this path to that square is better, using G cost as the measure. A lower G cost means that this is a better path. If so, change the parent of the square to the current square, and recalculate the G and F scores of the square. If you are keeping your open list sorted by F score, you may need to resort the list to account for the change.

         d) Stop when you:

         Add the target square to the closed list, in which case the path has been found (see note below), or
         Fail to find the target square, and the open list is empty. In this case, there is no path.
         3) Save the path. Working backwards from the target square, go from each square to its parent square until you reach the starting square. That is your path.
         *
         *
         *
         */
    }

    private Node reconstruct_path(){
        return null;
    }

    public void calculerVoisinage(Node current){
    }

    public Node findBestOpen(){
        return null;
    }

    public ArrayList<Node> findVoisin(Node node){
        ArrayList<Node> voisins = new ArrayList<Node>();


        return voisins;
    }

    public double calculHeuristique(int x1, int y1) {
        return Math.sqrt(Math.pow(x1 - arrive.getXpos(), 2) + (Math.pow(y1 - arrive.getYpos(), 2))) * COUT;
    }

}
