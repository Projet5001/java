package collisions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.IntMap;
import java.util.LinkedList;


public class WorldCollector {
    private Group group;
    private WorldCollector child;
    private IntMap<LinkedList<Actor>> collection;
    public WorldCollector() {
        this.collection = new IntMap<LinkedList<Actor>>(10);
    }

    public void add(Actor actor){
        LinkedList<Actor> keyList;
        double x = Math.floor(actor.getX()/32);
        double y = Math.floor(actor.getY()/32);
        double l = 0;
        double w = 0;
        for (int i = 0 ; i< Math.ceil(actor.getWidth()/32); i++){
            x += l;
            keyList = this.collection.get((int)x);
            if (keyList != null){
                keyList.add(actor);
                this.collection.remove((int)x);
                this.collection.put((int)x,keyList);
            }else{

                keyList = new LinkedList<Actor>();
                keyList.add(actor);
                this.collection.put((int)x,keyList);
            }

            l+=32;
        }
    }
}
