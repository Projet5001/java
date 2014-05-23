package com.projet5001.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;


public class MyActor extends Actor {
    private Sprite sprite;

    public MyActor (Sprite sprite){
        super();
        this.sprite = sprite;

        initMyactor();
    }

    public void initMyactor(){
        this.setHeight(this.sprite.getHeight());
        this.setWidth(this.sprite.getWidth());
        this.setX(this.sprite.getX());
        this.setY(this.sprite.getY());
        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("yesyes");
                return true;
            }
        });

    }

    public void sayHelloTest(){
        System.out.println("Allo");
        System.out.printf("height %2f width %2f\n", this.getHeight(), this.getWidth());
        System.out.printf( "height %2f width %2f\n",this.sprite.getHeight(), this.sprite.getWidth());
    }

    public void draw (Batch batch, float parentAlpha){
        this.sprite.draw(batch);
    }
}
