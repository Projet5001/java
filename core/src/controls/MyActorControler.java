package controls;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import actors.MyActor;

public class MyActorControler implements ControlEntity {
    public void register(final MyActor myActor) {
        myActor.addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                System.out.println("test");
                return false;
            }
        });
    }
}
