package com.projet5001.game.collisions;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.events.ContainerEvent;

import java.util.HashMap;
import java.util.LinkedList;


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

        Vector2 v1 =  extractlowerLeftCoord(rect, vector2Array);
        if (v1 !=null)vector2Array.add(v1);

        Vector2 v2 = extractLowerRightCoord(rect, vector2Array);
        if (v2 !=null)vector2Array.add(v2);

        Vector2 v3 = extractTopLeftCoord(rect, vector2Array);
        if (v3 !=null)vector2Array.add(v3);

       Vector2 v4 = extractTopRightCoord(rect, vector2Array);
        if (v4 !=null)vector2Array.add(v4);

        return vector2Array;
    }

    private Vector2 extractTopRightCoord(Rectangle rect, Array<Vector2> vector2Array) {
        Vector2 v4 = new Vector2(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
        v4 = getKeyFromVector(v4);
        if (containActor(v4) || containMap(v4)) {
            return v4;
        }
        return null;
    }

    private Vector2 extractTopLeftCoord(Rectangle rect, Array<Vector2> vector2Array) {
        Vector2 v3 = new Vector2(rect.getX(), rect.getY() + rect.getHeight());
        v3 = getKeyFromVector(v3);
        if (containActor(v3) || containMap(v3)) {
            return v3;
        }
        return null;
    }

    private Vector2 extractLowerRightCoord(Rectangle rect, Array<Vector2> vector2Array) {
        Vector2 v2 = new Vector2(rect.getX() + rect.getWidth(), rect.getY());
        v2 = getKeyFromVector(v2);
        if (containActor(v2) || containMap(v2)) {
           return v2;
        }
        return null;
    }

    private Vector2 extractlowerLeftCoord(Rectangle rect, Array<Vector2> vector2Array) {
        Vector2 v1 = new Vector2(rect.getX(), rect.getY());
        v1 = getKeyFromVector(v1);
        if (containActor(v1) || containMap(v1)) {
            return v1;
        }
        return null;
    }

    private Vector2 getKeyFromVector(Vector2 vector2) {
        float x = (float) Math.floor(vector2.x / hashSize);
        float y = (float) Math.floor(vector2.y / hashSize);
        return vector2.set(x, y);
    }

    public boolean rectEqual(Rectangle rect1, Rectangle rect2) {
        return rect1.equals(rect2);
    }

    public boolean hitWorld(Rectangle actor_rect) {
        Array<Vector2> keyVector2List = cornerThatHit(actor_rect);
        if (keyVector2List != null) {
            for (Vector2 keyVector2 : keyVector2List) {
                if (intersectWorldObjet(actor_rect, keyVector2)) return true;
            }
        }
        return false;
    }

    public boolean hit(Rectangle actor_rect) {

        Array<Vector2> keyVector2List = cornerThatHit(actor_rect);
        if (keyVector2List != null) {
            for (Vector2 keyVector2 : keyVector2List) {

                //debut pour actor
                if (intersectWorldActor(actor_rect, keyVector2)) return true;

                //debut pour mobilier
                if (intersectWorldObjet(actor_rect, keyVector2)) return true;
            }
        }
        return false;
    }

    private boolean intersectWorldObjet(Rectangle actor_rect, Vector2 keyVector2) {
        if (containMap(keyVector2)) {
            LinkedList<RectangleMapObject> mapObjList = this.rectangleMapObject_collection.get(keyVector2);
            Rectangle rect = new Rectangle();
            for (RectangleMapObject rectObj : mapObjList) {
                if (Intersector.intersectRectangles(actor_rect, rectObj.getRectangle(), rect)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean intersectWorldActor(Rectangle actor_rect, Vector2 keyVector2) {
        if (containActor(keyVector2)) {
            LinkedList<MyActor> actor_list = this.actor_collection.get(keyVector2);
            Rectangle rect = new Rectangle();
            for (MyActor enemie : actor_list) {
                //security to make sure we are not testing a self hit
                if (!(enemie.getHitbox().equals(actor_rect))) {
                    if (Intersector.intersectRectangles(actor_rect, enemie.getHitbox(), rect)) {
                        //todo doit etre improve plus tard
                        ContainerEvent e = new ContainerEvent(ContainerEvent.Type.collision);
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
