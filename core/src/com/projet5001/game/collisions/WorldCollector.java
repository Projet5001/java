package com.projet5001.game.collisions;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.projet5001.game.Utils.Utils;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.events.ActorEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class WorldCollector {

    private static int hashSize = 64;
    private static WorldCollector controls = null;
    private HashMap<Vector2, LinkedList<MyActor>> actor_collection;
    private HashMap<Vector2, LinkedList<RectangleMapObject>> rectangleMapObject_collection;

    public WorldCollector() {

        this.actor_collection = new HashMap<Vector2, LinkedList<MyActor>>();
        this.rectangleMapObject_collection = new HashMap<Vector2, LinkedList<RectangleMapObject>>();
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


    public void add(RectangleMapObject recObj) {
        LinkedList<RectangleMapObject> list;
        Rectangle rect = recObj.getRectangle();

        float x = ((float) Math.floor(rect.getX() / hashSize));
        float y = ((float) Math.floor(rect.getY() / hashSize));

        for (int i = 0; i <= Math.ceil(rect.getWidth() / hashSize); i++) {
            for (int j = 0; j <= Math.ceil(rect.getHeight() / hashSize); j++) {
                Vector2 vector_key = new Vector2();
                vector_key.set(x, y);
                list = this.rectangleMapObject_collection.get(vector_key);
                if (list != null) {
                    if (!list.contains(recObj)) {
                        list.add(recObj);
                        this.rectangleMapObject_collection.remove(vector_key);
                        this.rectangleMapObject_collection.put(vector_key, list);
                    }
                } else {
                    LinkedList<RectangleMapObject> newList = new LinkedList<RectangleMapObject>();
                    newList.add(recObj);
                    this.rectangleMapObject_collection.put(vector_key, newList);
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
     * @param v
     * @return
     */
    public boolean containActor(Vector2 v) {
        return this.actor_collection.containsKey(v);
    }

    public boolean containMap(Vector2 v) {
        return this.rectangleMapObject_collection.containsKey(v);
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
    private Array<Vector2> cornerThatHit(Rectangle rect) {
        //check 4 corver of rect
        Array<Vector2> vector2Array = new Array<>();

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
        v4 = Utils.getKeyFromVector(v4,hashSize);
        if (containActor(v4) || containMap(v4)) {
            return v4;
        }
        return null;
    }

    private Vector2 extractTopLeftCoord(Rectangle rect) {
        Vector2 v3 = new Vector2(rect.getX(), rect.getY() + rect.getHeight());
        v3 = Utils.getKeyFromVector(v3,hashSize);
        if (containActor(v3) || containMap(v3)) {
            return v3;
        }
        return null;
    }

    private Vector2 extractLowerRightCoord(Rectangle rect) {
        Vector2 v2 = new Vector2(rect.getX() + rect.getWidth(), rect.getY());
        v2 = Utils.getKeyFromVector(v2,hashSize);
        if (containActor(v2) || containMap(v2)) {
            return v2;
        }
        return null;
    }

    private Vector2 extractlowerLeftCoord(Rectangle rect) {
        Vector2 v1 = new Vector2(rect.getX(), rect.getY());
        v1 = Utils.getKeyFromVector(v1,hashSize);
        if (containActor(v1) || containMap(v1)) {
            return v1;
        }
        return null;
    }

    public boolean hitWorld(Rectangle actorHitbox) {
        Array<Vector2> keyVector2CornerList = cornerThatHit(actorHitbox);
        if (keyVector2CornerList != null) {
            for (Vector2 keyVector2Corner : keyVector2CornerList) {
                if (intersectWorldObjet(actorHitbox, keyVector2Corner)) return true;
            }
        }
        return false;
    }

    public boolean hit(Rectangle actorHitbox) {

        Array<Vector2> keyVector2CornerList = cornerThatHit(actorHitbox);
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
            if (circle.contains(v.x*hashSize,v.y*hashSize)){
                LinkedList<MyActor> myActorList = entry.getValue();
                for (MyActor enemie : myActorList) {
                    if (circle.x != enemie.getVisionHitbox().x && circle.y != enemie.getVisionHitbox().y ){
                            //todo doit etre improve plus tard
                            ActorEvent e = new ActorEvent(ActorEvent.Type.discover);
                            e.add(enemie);
                            enemie.fire(e);
                            visibleActor.add(enemie);

                    }
                }
            }
        }
        return visibleActor;
    }

    private boolean circleIntersectVector(Circle circle, Rectangle rect) {

        //vector vector vector rayon
        if (Intersector.intersectSegmentCircle(extractlowerLeftCoord(rect), extractTopLeftCoord(rect),
                new Vector2(circle.x, circle.y), circle.radius)) {
            return true;
        } else if (Intersector.intersectSegmentCircle(extractlowerLeftCoord(rect), extractLowerRightCoord(rect),
                new Vector2(circle.x, circle.y), circle.radius)) {
            return true;

        } else if (Intersector.intersectSegmentCircle(extractTopRightCoord(rect), extractLowerRightCoord(rect),
                new Vector2(circle.x, circle.y), circle.radius)) {
            return true;

        } else if (Intersector.intersectSegmentCircle(extractTopRightCoord(rect), extractTopLeftCoord(rect),
                new Vector2(circle.x, circle.y), circle.radius)) {
            return true;

        } else {
            return false;
        }
    }

    private boolean intersectWorldObjet(Rectangle actorHitbox, Vector2 keyVector2Corner) {
        if (containMap(keyVector2Corner)) {
            LinkedList<RectangleMapObject> mapObjList = this.rectangleMapObject_collection.get(keyVector2Corner);
            Rectangle rect = new Rectangle();
            for (RectangleMapObject rectObj : mapObjList) {
                if (Intersector.intersectRectangles(actorHitbox, rectObj.getRectangle(), rect)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean intersectWorldActor(Rectangle actorHitbox, Vector2 keyVector2Corner) {
        if (containActor(keyVector2Corner)) {
            LinkedList<MyActor> actor_list = this.actor_collection.get(keyVector2Corner);
            Rectangle rect = new Rectangle();
            for (MyActor enemie : actor_list) {
                //security to make sure we are not testing a self hit
                if (!(enemie.getHitbox().equals(actorHitbox))) {
                    if (Intersector.intersectRectangles(actorHitbox, enemie.getHitbox(), rect)) {
                        //todo doit etre improve plus tard
                        ActorEvent e = new ActorEvent(ActorEvent.Type.collision);
                        e.add(enemie);
                        enemie.fire(e);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
