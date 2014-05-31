package com.projet5001.game;

/**
 * Created by macmata on 31/05/14.
 */
public class MyActorControler {

}


/**
 /** Applies a key down event to the actor that has {@link Stage#setKeyboardFocus(com.badlogic.gdx.scenes.scene2d.Actor) keyboard focus}, if any, and returns
 * true if the event was {@link com.badlogic.gdx.scenes.scene2d.Event#handle() handled}.
 public boolean keyDown (int keyCode) {
 Actor target = keyboardFocus == null ? root : keyboardFocus;
 InputEvent event = Pools.obtain(InputEvent.class);
 event.setStage(this);
 event.setType(InputEvent.Type.keyDown);
 event.setKeyCode(keyCode);
 target.fire(event);
 boolean handled = event.isHandled();
 Pools.free(event);
 return handled;
 }
 */