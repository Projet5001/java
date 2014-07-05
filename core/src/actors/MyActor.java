package actors;

import collisions.WorldCollector;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import events.MovementEvents;
import listeners.MovementListener;


public class MyActor extends Actor {

    private int fastSpeed;
    private int slowSpeed;
    private int speed;
    private Sprite sprite;
    private Rectangle hitbox;
    private Vector2 old_position;
    private Vector2 futur_position;


    public MyActor(Sprite sprite) {
        super();
        this.speed = 1;
        this.fastSpeed = 1;
        this.slowSpeed = 1;
        this.sprite = sprite;
        this.old_position = new Vector2();
        this.futur_position = new Vector2();

        this.setBounds(this.sprite.getX(),this.sprite.getY(),this.sprite.getWidth(),this.sprite.getHeight());
        this.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
        this.setTouchable(Touchable.enabled);
        this.hitbox = new Rectangle(this.getX(), this.getY(),this.getWidth(), this.getHeight());
        addListener(new MovementListener() {

            public boolean moveLeft(MovementEvents event) {
                ((MyActor) event.getTarget()).moveLeft();
                return false;
            }

            public boolean moveRight(MovementEvents event) {
                ((MyActor) event.getTarget()).moveRight();
                return false;
            }

            public boolean moveUp(MovementEvents event) {
                ((MyActor) event.getTarget()).moveUp();
                return false;
            }

            public boolean moveDown(MovementEvents event) {
                ((MyActor) event.getTarget()).moveDown();
                return false;
            }

            public boolean idle(MovementEvents event) {
                ((MyActor) event.getTarget()).setSpeed(0);
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
        WorldCollector.getInstance().add(this);
    }
    public Rectangle getHitbox() {
        return hitbox;
    }

    public void act(float delta){
        super.act(delta);
        this.hitbox.setSize(this.getWidth(),this.getHeight());
        this.hitbox.setPosition(this.getX(), this.getY());
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
        this.old_position.set(this.getX(),this.getY());
        this.futur_position.set(this.getX()+x,this.getY()+y);
        WorldCollector.getInstance().hit(this);
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
}
