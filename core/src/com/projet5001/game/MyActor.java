package com.projet5001.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class MyActor extends Actor {
    private Sprite sprite;

    public MyActor (Sprite sprite){
        super();
        this.sprite = sprite;

        initMyactor();
    }

    public void initMyactor(){

        this.setBounds(this.sprite.getX(),this.sprite.getY(),this.sprite.getWidth(),this.sprite.getHeight());
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        System.out.printf("%f %f",this.getOriginX(), this.getOriginY());
        setTouchable(Touchable.enabled);

    }

    public void moveByC(float x, float y) {
        this.moveBy(x, y);
        this.sprite.translate(x, y);

    }

    public void draw (Batch batch, float parentAlpha){
        this.sprite.draw(batch);
    }
}
