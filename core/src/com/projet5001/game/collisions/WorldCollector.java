package com.projet5001.game.collisions;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.projet5001.game.Utils.Utils;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.ai.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class WorldCollector {

    private static int hashSize = 64;
    private static WorldCollector controls = null;
    private HashMap<Vector2, LinkedList<MyActor>> actor_collection;
    private HashMap<Vector2, Node> nodeGrid_collection;
    private Vector2 tmpVector2;

    public WorldCollector() {

        this.actor_collection = new HashMap<Vector2, LinkedList<MyActor>>();
        this.nodeGrid_collection = new HashMap<Vector2, Node>();
        this.tmpVector2 = new Vector2();
    }


    public static WorldCollector collection() {
        if (controls == null) {
            controls = new WorldCollector();

        }
        return controls;
    }

    public void addAll(Group group) {
        this.clear();
        Array<Actor> actors = group.getChildren();
        for (Actor actor : actors) {
            add((MyActor) actor);
        }
    }
    public void creatGrid(int x, int y){
        for (int i=0; i< x; i++ ){
            for (int j =0; j< y ; j++){
                this.nodeGrid_collection.put(new Vector2(i,j),new Node(i,j,hashSize,hashSize));
            }
        }
    }

    public void add(MyActor actor) {
        LinkedList<MyActor> list;
        float x = ((float) Math.floor(actor.getX() / hashSize));
        float y = ((float) Math.floor(actor.getY() / hashSize));
        for (int i = 0; i <= Math.ceil(actor.getWidth() / hashSize); i++) {
            for (int j = 0; j <= Math.ceil(actor.getHeight() / hashSize); j++) {
                Vector2 vector_key = new Vector2();
                vector_key.set(x, y);
                list = this.actor_collection.get(vector_key);
                if (list != null) {
                    if (!list.contains(actor)) {
                        list.add(actor);
                        this.actor_collection.remove(vector_key);
                        this.actor_collection.put(vector_key, list);
                    }
                } else {
                    LinkedList<MyActor> newList = new LinkedList<MyActor>();
                    newList.add(actor);
                    this.actor_collection.put(vector_key, newList);
                }
                y++;
            }
            y = ((float) Math.floor(actor.getY() / hashSize));
            x++;
        }
    }


    public HashMap<Vector2, Node> getNodeGrid_collection() {
        return nodeGrid_collection;
    }

    public void add(RectangleMapObject recObj) {
        Node node;
        Rectangle rect = recObj.getRectangle();

        float x = ((float) Math.floor(rect.getX() / hashSize));
        float y = ((float) Math.floor(rect.getY() / hashSize));

        for (int i = 0; i <= Math.ceil(rect.getWidth() / hashSize); i++) {
            for (int j = 0; j <= Math.ceil(rect.getHeight() / hashSize); j++) {

                tmpVector2.set(x, y);
                node = this.nodeGrid_collection.get(tmpVector2);
                if (node == null) {
                    Vector2 vector2 = new Vector2(x, y);
                    Node n = new Node(rect.x, rect.y, hashSize, hashSize);
                    n.setRectObj(rect);
                    n.setBlock(true);
                    this.nodeGrid_collection.put(vector2, n);
                } else {
                    node.setRectObj(rect);
                    node.setBlock(true);
                }
                y++;
            }
            y = ((float) Math.floor(rect.getY() / hashSize));
            x++;
        }
    }

    /**
     * containactor permet de determiner si le vecteur donne est present dans
     * le hashmap
     *
     * @param v
     * @return
     */
    public boolean lsitContainActor(Vector2 v) {
        return this.actor_collection.containsKey(v);
    }

    public boolean lsitContainMapObj(Vector2 v) {
        return this.nodeGrid_collection.containsKey(v);
    }

    public void clear() {
        this.actor_collection.clear();
    }

    public Array<Vector2> getKey() {
        Array<Vector2> vectorKeys = new Array<Vector2>();
        for (Vector2 vector2 : this.actor_collection.keySet()) {
            vectorKeys.add(vector2);
        }
        return vectorKeys;
    }

    //todo separé en 4 fonctions
    private LinkedList<Vector2> cornerThatHit(Rectangle rect) {
        //check 4 corver of rect
        LinkedList<Vector2> vector2Array = new LinkedList<>();

        Vector2 v1 = extractlowerLeftCoord(rect);
        if (v1 != null) vector2Array.add(v1);

        Vector2 v2 = extractLowerRightCoord(rect);
        if (v2 != null) vector2Array.add(v2);

        Vector2 v3 = extractTopLeftCoord(rect);
        if (v3 != null) vector2Array.add(v3);

        Vector2 v4 = extractTopRightCoord(rect);
        if (v4 != null) vector2Array.add(v4);

        return vector2Array;
    }

    private Vector2 extractTopRightCoord(Rectangle rect) {
        Vector2 v4 = new Vector2(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
        v4 = Utils.getKeyFromVector(v4, hashSize);
        if (lsitContainActor(v4) || lsitContainMapObj(v4)) {
            return v4;
        }
        return null;
    }

    private Vector2 extractTopLeftCoord(Rectangle rect) {
        Vector2 v3 = new Vector2(rect.getX(), rect.getY() + rect.getHeight());
        v3 = Utils.getKeyFromVector(v3, hashSize);
        if (lsitContainActor(v3) || lsitContainMapObj(v3)) {
            return v3;
        }
        return null;
    }

    private Vector2 extractLowerRightCoord(Rectangle rect) {
        Vector2 v2 = new Vector2(rect.getX() + rect.getWidth(), rect.getY());
        v2 = Utils.getKeyFromVector(v2, hashSize);
        if (lsitContainActor(v2) || lsitContainMapObj(v2)) {
            return v2;
        }
        return null;
    }

    private Vector2 extractlowerLeftCoord(Rectangle rect) {
        Vector2 v1 = new Vector2(rect.getX(), rect.getY());
        v1 = Utils.getKeyFromVector(v1, hashSize);
        if (lsitContainActor(v1) || lsitContainMapObj(v1)) {
            return v1;
        }
        return null;
    }

    public boolean hitWorld(Rectangle actorHitbox) {
        LinkedList<Vector2> keyVector2CornerList = cornerThatHit(actorHitbox);
        if (keyVector2CornerList != null) {
            for (Vector2 keyVector2Corner : keyVector2CornerList) {
                if (intersectWorldObjet(actorHitbox, keyVector2Corner)) return true;
            }
        }
        return false;
    }

    public boolean hit(Rectangle actorHitbox) {

        LinkedList<Vector2> keyVector2CornerList = cornerThatHit(actorHitbox);
        if (keyVector2CornerList != null) {
            for (Vector2 keyVector2Corner : keyVector2CornerList) {

                //debut pour actor
                if (intersectWorldActor(actorHitbox, keyVector2Corner)) return true;

                //debut pour mobilier
                if (intersectWorldObjet(actorHitbox, keyVector2Corner)) return true;
            }
        }
        return false;
    }

    public ArrayList<MyActor> circleContainActor(Circle circle) {
        ArrayList<MyActor> visibleActor = new ArrayList<>();
        for (Map.Entry<Vector2, LinkedList<MyActor>> entry : this.actor_collection.entrySet()) {
            Vector2 v = entry.getKey();
            if (circle.contains(v.x * hashSize, v.y * hashSize)) {
                LinkedList<MyActor> myActorList = entry.getValue();
                for (MyActor enemie : myActorList) {
                    if (circle.x != enemie.getVisionHitbox().x || circle.y != enemie.getVisionHitbox().y) {
                        visibleActor.add(enemie);
                    }
                }
            }
        }
        return visibleActor;
    }

    private boolean intersectWorldObjet(Rectangle actorHitbox, Vector2 keyVector2Corner) {
        if (lsitContainMapObj(keyVector2Corner)) {
            Node node = this.nodeGrid_collection.get(keyVector2Corner);
            if (node != null && node.getRectObj()!=null) {
                Rectangle rect = new Rectangle();
                if (Intersector.intersectRectangles(actorHitbox, node.getRectObj(), rect)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean intersectWorldActor(Rectangle actorHitbox, Vector2 keyVector2Corner) {
        if (lsitContainActor(keyVector2Corner)) {
            LinkedList<MyActor> actor_list = this.actor_collection.get(keyVector2Corner);
            Rectangle rect = new Rectangle();
            for (MyActor enemie : actor_list) {
                //security to make sure we are not testing a self hit
                if (!(enemie.getHitbox().equals(actorHitbox))) {
                    if (Intersector.intersectRectangles(actorHitbox, enemie.getHitbox(), rect)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
