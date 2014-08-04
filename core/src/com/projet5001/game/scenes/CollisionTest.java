package com.projet5001.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
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
public class CollisionTest extends ScreenAdapter {

    TouchpadStyle tps;
    SpriteBatch batch;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera worldCamera;
    OrthographicCamera uiCamera;
    JoypadControleur joyPadControleur;
    InputMultiplexer multiplexer;
    KeyboardControleurNEW Keyboard;

    MyActor myActor;
    MyActor myActor2;
    MapControleur mapControleur;

    private Game game;


    public CollisionTest(Projet5001 game) {
        this.game = game;
        this.batch = game.batcher;

        mapControleur = new MapControleur("data/tmx/sandbox.tmx");

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
            myActor2 = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));
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

        uiCamera = (OrthographicCamera)Projet5001.uiDirector.getCamera();
        uiCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCamera.update();

        Projet5001.worldDirector.debug();
        Projet5001.worldDirector.draw();
        Projet5001.uiDirector.draw();



        ShapeRenderer rect = new ShapeRenderer();
        rect.setProjectionMatrix(worldCamera.combined);
        MapObjects mapObjects = mapControleur.mapCollidable.getObjects();
        for (MapObject mapObject : mapObjects) {
            RectangleMapObject rectObj = ((RectangleMapObject) mapObject);
            Rectangle rectangle = rectObj.getRectangle();
                rect.begin(ShapeRenderer.ShapeType.Line);
                rect.setColor(1, 1, 0, 1);
                rect.rect(rectangle.getX(),rectangle.getY(),rectangle.getWidth() ,rectangle.getHeight());
                rect.end();
        }
    }
}
