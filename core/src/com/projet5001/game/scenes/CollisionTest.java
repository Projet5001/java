package com.projet5001.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.projet5001.game.Main;
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
public class CollisionTest extends ScreenAdapter {

    TouchpadStyle tps;
    SpriteBatch batch;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera worldCamera;
    OrthographicCamera uiCamera;


    Director worldDirector;
    Director uiDirector;

    JoypadControleur joyPadControleur;
    InputMultiplexer multiplexer;
    KeyboardControleurNEW Keyboard;

    MyActor myActor;
    MyActor myActor2;
    MapControleur mapControleur;

    private Game game;


    public CollisionTest(Main game) {
        this.game = game;
        this.batch = game.batcher;

        mapControleur = new MapControleur("data/tmx/ageei2.tmx");

        myActor = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));

        worldDirector = new Director();

        uiDirector = new Director();

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiDirector);
        multiplexer.addProcessor(worldDirector);

        Gdx.input.setInputProcessor(multiplexer);

        worldDirector.setKeyboardFocus(myActor);
        Keyboard = new KeyboardControleurNEW();
        Keyboard.register(myActor);

        tps  = new TouchpadStyle();
        joyPadControleur = new JoypadControleur(10f,tps.getTouchpadStyle());

        joyPadControleur.setBounds(0, 0, 200, 200);

        joyPadControleur.register(myActor);

        uiDirector.addActor(joyPadControleur);
        uiDirector.addActor(Keyboard);
        worldDirector.addActor(myActor);

        for (int i = 3; i< 10; i ++ ){
            myActor2 = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));
            myActor2.setPosition(i,i);
            worldDirector.addActor(myActor2);

        }
    }


    @Override
    public void render(float delta) {
       WorldCollector.collection().addAll(worldDirector.getGroupActeurs());
       worldDirector.act();
       uiDirector.act();
       draw();
    }

    public void draw() {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldCamera = (OrthographicCamera) worldDirector.getCamera();
        worldCamera.setToOrtho(false, 30, 20);
        worldCamera.position.set(myActor.getX(),myActor.getY(),0f);
        worldCamera.update();

        mapControleur.setView(worldCamera);
        mapControleur.render();

        uiCamera = (OrthographicCamera)uiDirector.getCamera();
        uiCamera.setToOrtho(false, 640, 480);
        uiCamera.update();

        worldDirector.debug();
        worldDirector.draw();
        uiDirector.draw();

    }
}
