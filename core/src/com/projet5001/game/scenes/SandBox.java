package com.projet5001.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Array;
import com.projet5001.game.Projet5001;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.controleur.MapControleur;
import com.projet5001.game.views.TouchpadStyle;
import com.projet5001.game.collisions.WorldCollector;
import com.projet5001.game.controleur.Director;
import com.projet5001.game.controleur.JoypadControleur;
import com.projet5001.game.controleur.KeyboardControleurNEW;



/**
 * Created by macmata on 31/05/14.
 */
public class SandBox extends ScreenAdapter {

    TouchpadStyle tps;
    OrthographicCamera worldCamera;
    OrthographicCamera uiCamera;
    JoypadControleur joyPadControleur;
    InputMultiplexer multiplexer;
    KeyboardControleurNEW Keyboard;

    MyActor myActor;

    MapControleur mapControleur;

    private Projet5001 game;


    public SandBox(Projet5001 p) {
        this.game = p;
        if(Projet5001.devMode){


            if(UIUtils.isWindows){

                System.out.println("dev mode sandbox loaded");
                mapControleur = new MapControleur(new ExternalFileHandleResolver(),"projet5001\\tmx\\sandbox.tmx");

            }else if(UIUtils.isLinux||UIUtils.isMac){

                System.out.println("dev mode sandbox loaded");
                mapControleur = new MapControleur(new ExternalFileHandleResolver(),"projet5001/tmx/sandbox.tmx");

            }else{

                System.out.println("os not compatible");
                game.setScreen(new Menu(game));

            }

        }else{
            mapControleur = new MapControleur("data/tmx/sandbox.tmx");
        }


        myActor = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));



        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(Projet5001.uiDirector);
        multiplexer.addProcessor(Projet5001.worldDirector);

        Gdx.input.setInputProcessor(multiplexer);

        Projet5001.worldDirector.setKeyboardFocus(myActor);
        Keyboard = new KeyboardControleurNEW();
        Keyboard.register(myActor);

        tps  = new TouchpadStyle();
        joyPadControleur = new JoypadControleur(10f,tps.getTouchpadStyle());

        joyPadControleur.setBounds(0, 0, 200, 200);

        joyPadControleur.register(myActor);

        Projet5001.uiDirector.addActor(joyPadControleur);
        Projet5001.uiDirector.addActor(Keyboard);
        Projet5001.worldDirector.addActor(myActor);

        for (int i = 1; i< 3; i ++ ){
            MyActor myActor2 = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));
            myActor2.setPosition(i*100,i*100);
            Projet5001.worldDirector.addActor(myActor2);

        }
    }


    @Override
    public void render(float delta) {
       WorldCollector.collection().addAll(Projet5001.worldDirector.getGroupActeurs());
       Projet5001.worldDirector.act();
       Projet5001.uiDirector.act();
       draw();
    }

    public void draw() {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldCamera = (OrthographicCamera) Projet5001.worldDirector.getCamera();
        worldCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        worldCamera.position.set(myActor.getX(),myActor.getY(),0f);
        worldCamera.update();

        mapControleur.setView(worldCamera);
        mapControleur.render();
        mapControleur.debug(worldCamera);

        uiCamera = (OrthographicCamera)Projet5001.uiDirector.getCamera();
        uiCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCamera.update();

        Projet5001.worldDirector.debug();
        Projet5001.worldDirector.draw();
        Projet5001.uiDirector.draw();




    }
}
