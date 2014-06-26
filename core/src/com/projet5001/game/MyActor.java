package com.projet5001.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class MyActor extends Actor {
    private  ShapeRenderer rect;
    private int fastSpeed;
    private int slowSpeed;
    private int speed;
    private Sprite sprite;

    public ShapeRenderer getRect() {
        return rect;
    }

    public MyActor(Sprite sprite) {
        super();
        this.speed = 1;
        this.fastSpeed = 1;
        this.slowSpeed = 1;
        this.sprite = sprite;

        this.setBounds(this.sprite.getX(),this.sprite.getY(),this.sprite.getWidth(),this.sprite.getHeight());
        this.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
        this.setTouchable(Touchable.enabled);
        addListener(new MovementListener(){

            public boolean moveLeft(MovementEvents event) {
                ((MyActor)event.getTarget()).moveLeft();
                return false;
            }
            public boolean moveRight(MovementEvents event) {
                ((MyActor)event.getTarget()).moveRight();
                return false;
            }
            public boolean moveUp(MovementEvents event) {
                ((MyActor)event.getTarget()).moveUp();
                return false;
            }
            public boolean moveDown(MovementEvents event) {
                ((MyActor)event.getTarget()).moveDown();
                return false;
            }
            public boolean idle(MovementEvents event) {
                ((MyActor)event.getTarget()).setSpeed(0);
                return false;
            }
            public boolean moveFast(MovementEvents event) {
                ((MyActor)event.getTarget()).setSpeed(fastSpeed);
                return false;
            }
            public boolean moveSlow(MovementEvents event) {
                ((MyActor)event.getTarget()).setSpeed(slowSpeed);
                return false;
            }

        });
    }

    public int getSpeed(){
        return this.speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }

    /**
     * Permet de faire le deplacement de l'acteur et de sont sprite attaché si present
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
        move(-speed,0);
        System.out.println(getX());
        System.out.println(this.sprite.getX());
    }
    public void moveRight(){
        move(speed,0);
        System.out.println(getX());
        System.out.println(this.sprite.getX());
    }
    public void moveUp(){
        move(0, speed);
        System.out.println(getY());
        System.out.println(this.sprite.getY());

    }
    public void moveDown(){
        move(0,-speed);
        System.out.println(getY());
        System.out.println(this.sprite.getY());
    }
}
