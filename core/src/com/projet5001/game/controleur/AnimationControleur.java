package com.projet5001.game.controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.projet5001.game.actors.MyActor;

import java.util.HashMap;

/**
 * Created by macmata on 05/08/14.
 */
public class AnimationControleur {

    public final int DEFAULT_KEY_FRAME = 3;
    private MyActor actor;
    private int animation_number;
    private String actor_name;
    private float speed;
    private int frame_number;
    private HashMap<String,Animation> animationHashMap;

    public AnimationControleur(String name, int frame_number,float speed, int animation_number, MyActor actor){
        this.speed = speed;
        this.actor = actor;
        this.actor_name = name;
        this.frame_number =  frame_number;
        this.animation_number = animation_number;
        this.animationHashMap = new HashMap<>(animation_number);
    }

    private void generateAnimationCycle(){
        String[] arr = {"walk_left","walk_right","walk_up","walk_down"};

        for(String cycle: arr){
            TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("data/atlas/"+this.actor_name+"/"+cycle+".atlas"));
            this.animationHashMap.put(cycle,new Animation(this.speed, textureAtlas.getRegions()));
        }
    }
}
