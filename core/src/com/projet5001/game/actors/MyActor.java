package com.projet5001.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.projet5001.game.collisions.WorldCollector;
import com.projet5001.game.events.ContainerEvent;
import com.projet5001.game.events.MovementEvents;
import com.projet5001.game.listeners.ContainerListener;
import com.projet5001.game.listeners.MovementListener;


public class MyActor extends Actor {

    private int fastSpeed;
    private int slowSpeed;
    private float speed;
    private Sprite sprite;
    private Rectangle hitbox;
    private Vector2 old_position;
    private Vector2 futur_position;
    private float unitScale;

    public movement getMove() {
        return move;
    }

    public void setMove(movement move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return "MyActor{" +
                "fastSpeed=" + fastSpeed +
                ", slowSpeed=" + slowSpeed +
                ", speed=" + speed +
                ", sprite=" + sprite +
                ", hitbox=" + hitbox +
                ", old_position=" + old_position +
                ", futur_position=" + futur_position +
                ", unitScale=" + unitScale +
                ", move=" + move +
                '}';
    }

    private movement move;

    public MyActor(){
        this(null);
    }

    public MyActor(Texture texture) {
        super();
        this.speed = 5;
        this.fastSpeed = 10;
        this.slowSpeed = 10;
        this.unitScale = 1/32f;
        this.sprite =  null;
        this.move = movement.idle;
        this.old_position = new Vector2();
        this.futur_position = new Vector2();
        this.options(this.unitScale, texture);
        addListener(new MovementListener() {

            public boolean moveLeft(MovementEvents event) {
                ((MyActor) event.getTarget()).moveLeft();
                setMove(movement.left);
                return false;
            }

            public boolean moveRight(MovementEvents event) {
                ((MyActor) event.getTarget()).moveRight();
                setMove(movement.right);
                return false;
            }

            public boolean moveUp(MovementEvents event) {
                ((MyActor) event.getTarget()).moveUp();
                setMove(movement.up);
                return false;
            }

            public boolean moveDown(MovementEvents event) {
                ((MyActor) event.getTarget()).moveDown();
                setMove(movement.down);
                return false;
            }

            public boolean idle(MovementEvents event) {
                //((MyActor) event.getTarget()).setSpeed(0);
                return false;
            }

            public boolean moveFast(MovementEvents event) {
                ((MyActor) event.getTarget()).setSpeed(fastSpeed);
                return false;
            }

            public boolean moveSlow(MovementEvents event) {
                ((MyActor) event.getTarget()).setSpeed(slowSpeed);
                return false;
            }

        });
        addListener(new ContainerListener(){
            //todo changer une fois que l'on sait comment les acteur reagise au collision
            public boolean collision(ContainerEvent containerEvent) {
                //((MyActor) containerEvent.getTarget()).collide(containerEvent.getList());
                return false;
            }
        });
    }
    public void options (float unitScale, Texture texture){
        if (texture!=null){
            this.sprite = new Sprite(texture);
            this.sprite.setSize(this.sprite.getWidth() , this.sprite.getHeight());
            this.setBounds(getX(),getY(),this.sprite.getWidth(),this.sprite.getHeight());
        }
        this.setTouchable(Touchable.enabled);
        this.hitbox = new Rectangle(this.getX(), this.getY(),this.getWidth(), this.getHeight()/3);
    }
    public Rectangle getHitbox() {
        return hitbox;
    }

    public float getSpeed(){
        return this.speed;
    }

    public Vector2 getVector(){
        return new Vector2(getX(),getY());
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void move(float x, float y) {
        savePosition(x, y);
        setHitboxPosition(this.futur_position);
        if ( WorldCollector.collection().hit(this.getHitbox())){
            resetPosition();
        }else{
            MoveByAction moveAction = new MoveByAction();
            moveAction.setAmount(x,y);
            this.addAction(moveAction);
        }
        updateHitboxPosition();
    }

    private void savePosition(float x, float y) {
        this.old_position.set(this.getX(),this.getY());
        this.futur_position.set(this.getX()+x,this.getY()+y);
    }

    public void resetPosition(){
        this.setPosition(this.old_position);
        this.updateHitboxPosition();
    }

    public void setPosition(float x, float y) {
        super.setPosition(x,y);
        this.updateHitboxPosition();
    }

    public void setPosition(Vector2 vector) {
        this.setPosition(vector.x, vector.y);
    }

    public void updateHitboxPosition(){
        this.hitbox.setPosition(this.getX(), this.getY());
    }

    public void setHitboxPosition(Vector2 vector2){
        this.hitbox.setPosition(vector2.x, vector2.y);
    }

    public void setHitbox(Rectangle rect){
        this.hitbox = rect;
    }

    public void moveLeft(){
        move(-speed,0);
    }
    public void moveRight(){
        move(speed,0);
    }
    public void moveUp(){
        move(0, speed);
    }
    public void moveDown(){
        move(0,-speed);
    }

    @Override
    public void act(float delta){
        this.setZIndex((int)this.getY());
        this.updateHitboxPosition();
        this.isIdle();
        super.act(delta);
        System.out.println(getMove());

    }

    private void isIdle(){
        if(this.getActions().size == 0){
            setMove(movement.idle);
        }
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        if (this.sprite != null) {
            this.sprite.setPosition(getX(),getY());
            this.sprite.draw(batch);
        }
    }
    private enum movement {
        left, down, up, right, idle
    }
}
