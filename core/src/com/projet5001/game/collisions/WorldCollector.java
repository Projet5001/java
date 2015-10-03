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

package com.projet5001.game.collisions;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.projet5001.game.Utils.Utils;
import com.projet5001.game.actors.MyActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class WorldCollector {

    private static int hashSize = 32;
    private static WorldCollector controls = null;
    private  HashMap<Vector2, LinkedList<MyActor>> actor_collection;
    private  HashMap<Vector2, LinkedList<RectangleMapObject>> rectangleMapObject_collection;

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

    public boolean listContainActor(Vector2 v) {
        return this.actor_collection.containsKey(v);
    }

    public boolean lsitContainMapObj(Vector2 v) {
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

    /**
     * If not null add the vector representign a corner
     * of the rectangle to the list
     *
     * @param rect
     * @return
     */
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

    /**
     * Get the topright from the vector
     *
     * @param rect
     * @return
     */
    private Vector2 getTopightVector(Rectangle rect) {
        return new Vector2(rect.getX() + rect.getWidth(), rect.getY() + rect.getHeight());
    }

    /**
     * Get the top left from vector
     *
     * @param rect
     * @return
     */
    private Vector2 getTopLeftVector(Rectangle rect) {
        return new Vector2(rect.getX(), rect.getY() + rect.getHeight());
    }

    /**
     * Get the lower right from vector
     *
     * @param rect
     * @return
     */
    private Vector2 getLowerRightVector(Rectangle rect) {
        return new Vector2(rect.getX() + rect.getWidth(), rect.getY());
    }

    /**
     * Get the lower left from vector
     *
     * @param rect
     * @return
     */
    private Vector2 getLowerLeftVector(Rectangle rect) {
        return new Vector2(rect.getX(), rect.getY());
    }

    /**
     * If the vector is present one of the list return it
     * @param rect
     * @return
     */
    private Vector2 extractTopRightCoord(Rectangle rect) {
        Vector2 v4 = getTopightVector(rect);
        v4 = Utils.getKeyFromVector(v4, hashSize);
        if (listContainActor(v4) || lsitContainMapObj(v4)) {
            return v4;
        }
        return null;
    }

    /**
     * If the vector is present one of the list return it
     * @param rect
     * @return
     */
    private Vector2 extractTopLeftCoord(Rectangle rect) {
        Vector2 v3 = getTopLeftVector(rect);
        v3 = Utils.getKeyFromVector(v3, hashSize);
        if (listContainActor(v3) || lsitContainMapObj(v3)) {
            return v3;
        }
        return null;
    }

    /**
     * If the vector is present one of the list return it
     * @param rect
     * @return
     */
    private Vector2 extractLowerRightCoord(Rectangle rect) {
        Vector2 v2 = getLowerRightVector(rect);
        v2 = Utils.getKeyFromVector(v2, hashSize);
        if (listContainActor(v2) || lsitContainMapObj(v2)) {
            return v2;
        }
        return null;
    }

    /**
     * If the vector is present one of the list return it
     * @param rect
     * @return
     */
    private Vector2 extractlowerLeftCoord(Rectangle rect) {
        Vector2 v1 = getLowerLeftVector(rect);
        v1 = Utils.getKeyFromVector(v1, hashSize);
        if (listContainActor(v1) || lsitContainMapObj(v1)) {
            return v1;
        }
        return null;
    }

    /**
     * Check wether the rect hit a world object
     *
     * @param rect
     * @return
     */
    public boolean hitWorld(Rectangle rect) {
        LinkedList<Vector2> keyVector2CornerList = cornerThatHit(rect);
        if (keyVector2CornerList != null) {
            for (Vector2 keyVector2Corner : keyVector2CornerList) {
                if (intersectWorldObjet(rect, keyVector2Corner)) return true;
            }
        }
        return false;
    }

    /**
     * Check for collision for both object and other actor in the
     * world
     * @param actorHitbox
     * @return
     */
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


    private boolean intersectWorldObjet(Rectangle actorHitbox, Vector2 keyVector2Corner) {
        if (lsitContainMapObj(keyVector2Corner)) {
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
        if (listContainActor(keyVector2Corner)) {
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

    /**
     * Return a list a actor contained in the circle
     *
     * @param circle
     * @return
     */
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
}
