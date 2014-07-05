package collisions;

import actors.MyActor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import java.util.HashMap;
import java.util.LinkedList;


public class WorldCollector {

    private int hashSize = 64;
    private HashMap<Vector2,LinkedList<MyActor>> actor_collection;
    private static WorldCollector controls = null;

    public WorldCollector() {
        this.actor_collection = new HashMap<Vector2, LinkedList<MyActor>>();
    }


    public static WorldCollector collection() {
        if (controls == null) {
            controls = new WorldCollector();
        }
        return controls;
    }
    public void addAll(Group group){
        this.clear();
        Array<Actor> actors = group.getChildren();
        for(Actor actor:actors){
            add((MyActor)actor);
        }
    }
    public void add(MyActor actor){
        LinkedList<MyActor> keyList;
        float x = (float)Math.floor(actor.getX()/hashSize);
        float y = (float)Math.floor(actor.getY()/hashSize);
        float l = 0;
        float w = 0;
        Vector2 vector2 = new Vector2();
        for (int i = 0 ; i< Math.ceil(actor.getWidth()/hashSize); i++){
            x += l;
            for (int j = 0 ; j< Math.ceil(actor.getHeight()/hashSize); j++) {
                y += w;
                vector2.set(x, y);
                keyList = this.actor_collection.get(vector2);
                if (keyList != null) {
                    if (!keyList.contains(actor)) {
                        keyList.add(actor);
                        this.actor_collection.remove(vector2);
                        this.actor_collection.put(vector2, keyList);
                    }
                } else {
                    keyList = new LinkedList<MyActor>();
                    keyList.add(actor);
                    this.actor_collection.put(vector2, keyList);
                }
                w+=hashSize;
            }
            l+=hashSize;
        }
    }

    public boolean isHit (Vector2 v){
        return this.actor_collection.containsKey(v);
    }

    public void clear (){
        this.actor_collection.clear();
    }

    public Array<Vector2> getKey(){
        Array<Vector2> vectorKeys = new Array<Vector2>();
        for (Vector2 vector2: this.actor_collection.keySet()){
            vectorKeys.add(vector2);
        }
        return vectorKeys;
    }

    private Vector2 getKeyFromVector(MyActor actor){
        float x = (float)Math.floor(actor.getX()/hashSize);
        float y = (float)Math.floor(actor.getY()/hashSize);
        return new Vector2(x,y);
    }

    public Boolean hit(MyActor actor){
        Vector2 keyVector2 = getKeyFromVector(actor);
        if (isHit(keyVector2)){
            LinkedList<MyActor> keyList = this.actor_collection.get(keyVector2);
            Rectangle rect =  new Rectangle();
            for (MyActor aKeyList : keyList) {
                if (aKeyList != actor){
                    if (Intersector.intersectRectangles(actor.getHitbox(), aKeyList.getHitbox(), rect)) {
                        return true;
                        //actor.collide(aKeyList);
                    }
                }
            }
        }
        return false;
    }
}
