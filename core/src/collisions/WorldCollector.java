package collisions;

import actors.MyActor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import java.util.LinkedList;


public class WorldCollector {

    public static int hashSize = 64;
    private HashMap<Vector2,LinkedList<MyActor>> collection ;
    public WorldCollector() {
        this.collection = new HashMap<Vector2, LinkedList<MyActor>>();
    }

    public void add(MyActor actor){
        LinkedList<MyActor> keyList;
        float x = (float)Math.floor(actor.getX()/hashSize);
        float y = (float)Math.floor(actor.getY()/hashSize);
        float l = 0;
        float w = 0;
        Vector2 v = new Vector2();
        for (int i = 0 ; i< Math.ceil(actor.getWidth()/hashSize); i++){
            x += l;
            for (int j = 0 ; j< Math.ceil(actor.getHeight()/hashSize); j++) {
                y += w;
                v.set(x,y);
                keyList = this.collection.get(v);
                if (keyList != null) {
                    if (!keyList.contains(actor)) {
                        keyList.add(actor);
                        this.collection.remove(v);
                        this.collection.put(v, keyList);
                    }
                } else {
                    keyList = new LinkedList<MyActor>();
                    keyList.add(actor);
                    this.collection.put(v, keyList);
                }
                w+=hashSize;
            }
            l+=hashSize;
        }
    }

    public boolean isHit (Vector2 v){
        return this.collection.containsKey(v);
    }


    private Vector2 getHashFromVector(MyActor actor){
        float x = (float)Math.floor(actor.getX()/hashSize);
        float y = (float)Math.floor(actor.getY()/hashSize);
        return new Vector2(x,y);
    }

    /**
     * version 1
     * @param actor
     * @return the actor that hit the actor
     */
    public MyActor hit(MyActor actor){
        Vector2 vector2 = getHashFromVector(actor);
        if (isHit(vector2)){
            LinkedList<MyActor> keyList = this.collection.get(vector2);
            Rectangle rect =  new Rectangle();
            for (MyActor aKeyList : keyList) {
                if (Intersector.intersectRectangles(actor.getHitbox(), aKeyList.getHitbox(), rect)) {
                    return aKeyList;
                }
            }
        }
        return null;
    }
}
