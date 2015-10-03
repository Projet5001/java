/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexandre Leblanc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.projet5001.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.projet5001.game.Ai.BehaviorTree.Ai;
import com.projet5001.game.collisions.WorldCollector;
import com.projet5001.game.controleur.AnimationControleur;
import com.projet5001.game.events.ActorEvent;
import com.projet5001.game.events.MovementEvents;
import com.projet5001.game.listeners.ContainerListener;
import com.projet5001.game.listeners.MovementListener;


public class MyActor extends Actor {

    protected int collisionBoxSize;
    protected int ZIndex;
    protected float speed;
    protected Sprite sprite;
    protected TextureRegion textureRegion;
    protected Rectangle hitbox;
    protected Circle visionHitbox;
    protected Vector2 old_position;
    protected Vector2 futur_position;
    protected float visionDistance;
    protected AnimationControleur animationControleur;
    protected String move;

    public MyActor() {
        this(null);
    }

    public MyActor(Texture texture) {
        super();
        this.speed = 32 / 16;
        this.ZIndex = 0;
        this.visionDistance = 250;
        this.collisionBoxSize = 4;

        this.sprite = null;
        this.textureRegion = null;
        this.animationControleur = null;

        this.move = "idle";

        this.visionHitbox = new Circle(this.getCenterX(), this.getCenterY(), visionDistance);
        this.old_position = new Vector2();
        this.futur_position = new Vector2();
        this.options(texture);
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
                //((MyActor) event.getTarget()).setSpeed(0);
                return false;
            }
        });
        addListener(new ContainerListener() {
            //todo changer une fois que l'on sait comment les acteur reagise au collision
            public boolean collision(ActorEvent actorEvent) {
                //((MyActor) containerEvent.getTarget()).collide(containerEvent.getList());
                return false;
            }
        });

        //todo  menu ou option du personnage ou npc
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println(((MyActor) event.getTarget()).toString());
                return false;
            }
        });

    }

    public void options(Texture texture) {
        if (texture != null) {
            this.sprite = new Sprite(texture);
            this.setBounds(getX(), getY(), this.sprite.getWidth(), this.sprite.getHeight());
            this.hitbox = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight() / collisionBoxSize);
        }
        this.setTouchable(Touchable.enabled);
    }

    public void options(AnimationControleur animationControleur) {
        if (animationControleur != null) {
            this.animationControleur = animationControleur;
            this.textureRegion = animationControleur.getCurrentTexture(this.move);
            this.setBounds(getX(), getY(), this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
            this.hitbox = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight() / collisionBoxSize);
        }
        this.setTouchable(Touchable.enabled);
    }

    public Circle getVisionHitbox() {
        return visionHitbox;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Vector2 getVector() {
        return new Vector2(this.getX(), this.getY());
    }

    public void setMove(String move) {
        this.move = move;
    }

    public void moveLeft() {
        setMove("walk_left");
        move(-speed, 0);
    }

    public void moveRight() {
        setMove("walk_right");
        move(speed, 0);
    }

    public void moveUp() {
        setMove("walk_up");
        move(0, speed);
    }

    public void moveDown() {
        setMove("walk_down");
        move(0, -speed);
    }

    public void move(float x, float y) {
        savePosition(x, y);
        setHitboxPosition(this.futur_position);
        if (WorldCollector.collection().hit(this.hitbox)) {
            resetPosition();
        } else {
            MoveByAction moveAction = new MoveByAction();
            moveAction.setAmount(x, y);
            this.addAction(moveAction);
        }
        updateHitboxPosition();
    }

    protected void savePosition(float x, float y) {
        this.old_position.set(this.getX(), this.getY());
        this.futur_position.set(this.getX() + x, this.getY() + y);
    }

    public void resetPosition() {
        this.setPosition(this.old_position);
        this.updateHitboxPosition();
    }

    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.updateHitboxPosition();
    }

    public void setPosition(Vector2 vector) {
        this.setPosition(vector.x, vector.y);
    }

    public void updateHitboxPosition() {
        this.hitbox.setPosition(this.getX(), this.getY());
    }

    public void setHitboxPosition(Vector2 vector2) {
        this.hitbox.setPosition(vector2.x, vector2.y);
    }

    @Override
    public void act(float delta) {

        this.setZIndex((int) this.getY());
        this.isIdle();
        super.act(delta);

        this.visionHitbox.set(this.getX(), this.getY(), this.visionDistance);
        this.hitbox.setPosition(this.getX(), this.getY());
        //important puisque les actions ne son pas déclenché tjs au meme moment.
    }

    public int getZIndex() {
        return this.ZIndex;
    }

    public void setZIndex(int index) {
        this.ZIndex = index;
    }

    private boolean isIdle() {
        int i = 0;
        for (Action action : this.getActions()) {
            if (action instanceof Ai) {
                continue;
            }
            i++;
        }

        if (i == 0) {
            setMove("idle");
            return true;
        }
        return false;
    }

    public boolean isSeeingEnemies() {
        return false;
    }

    public boolean isSafe() {
        return true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (this.sprite != null) {
            this.sprite.setPosition(getX(), getY());
            this.sprite.draw(batch);
        }
        if (this.animationControleur != null) {
            this.textureRegion = this.animationControleur.getCurrentTexture(move);
            batch.draw(textureRegion, getX(), getY());
        }
    }
}
