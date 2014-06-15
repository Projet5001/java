package com.projet5001.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class MyActor extends Actor {
    private Sprite sprite;


    public MyActor() {
        super();
        this.sprite = null;
        setTouchable(Touchable.enabled);
    }

    public MyActor(Sprite sprite) {
        super();
        this.sprite = sprite;
        this.setBounds(this.sprite.getX(),this.sprite.getY(),this.sprite.getWidth(),this.sprite.getHeight());
        this.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
        this.setTouchable(Touchable.enabled);

    }


    /**
     * Permet de faire le deplacement de l'acteur et de sont sprite attaché si present
     *
     * @param x
     * @param y
     */
    public void move(float x, float y) {
        this.moveBy(x, y);
        if (this.sprite != null) {
            this.sprite.translate(x, y);
        }
    }

    public void draw (Batch batch, float parentAlpha) {
        if (this.sprite != null) {
            this.sprite.draw(batch);
        }
    }

    public void moveLeft(){
        move(-10,0);
        System.out.println(getX());
        System.out.println(this.sprite.getX());
    }
    public void moveRight(){
        move(10,0);
        System.out.println(getX());
        System.out.println(this.sprite.getX());
    }
    public void moveUp(){
        move(0,10);
        System.out.println(getY());
        System.out.println(this.sprite.getY());

    }
    public void moveDown(){
        move(0,-10);
        System.out.println(getY());
        System.out.println(this.sprite.getY());
    }
}
