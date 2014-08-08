package com.projet5001.game.controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.projet5001.game.actors.MyActor;

import java.util.HashMap;

/**
 * Created by macmata on 05/08/14.
 */
public class AnimationControleur {

    public final int DEFAULT_KEY_FRAME = 3;
    private int animation_number;
    private String actor_name;
    private String last_move;
    private float speed;
    private int frame_number;
    private HashMap<String,Animation> animationHashMap;
    private float stateTime;

    public AnimationControleur(String sprite_name, int frame_number,float speed, int animation_number){
        this.speed = speed;
        this.actor_name = sprite_name;
        this.frame_number =  frame_number;
        this.animation_number = animation_number;
        this.last_move = "walk_down";
        this.animationHashMap = new HashMap<>(animation_number);
        this.stateTime = 0f;
        this.generateAnimationCycle();
    }

    private void generateAnimationCycle(){
            String[] arr = {"walk_left","walk_right","walk_up","walk_down"};
            for(String cycle: arr){
                TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("data/atlas/"+this.actor_name+"/"+cycle+".atlas"));
                this.animationHashMap.put(cycle,new Animation(this.speed, textureAtlas.getRegions()));
            }
    }

    public TextureRegion getCurrentTexture(String move){
        if (move.equalsIgnoreCase("idle")){
            TextureRegion []textureRegion = this.animationHashMap.get(this.last_move).getKeyFrames();
            return textureRegion[3];
        }
        this.last_move = move;
        stateTime += Gdx.graphics.getDeltaTime();
        return  this.animationHashMap.get(move).getKeyFrame(stateTime,true);
    }
}
