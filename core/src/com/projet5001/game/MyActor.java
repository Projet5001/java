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

        this.setBounds(this.sprite.getX(),this.sprite.getY(),this.sprite.getWidth(),this.sprite.getHeight());
        this.setOrigin(this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        System.out.printf("%f %f",this.getOriginX(), this.getOriginY());
        setTouchable(Touchable.enabled);

    }
    public void setPositionC(float x,float y){
        this.sprite.setPosition(x,y);
        setPosition(x,y);

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
