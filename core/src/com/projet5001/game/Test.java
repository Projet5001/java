package com.projet5001.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by macmata on 31/05/14.
 */
public class Test extends ScreenAdapter {
    SpriteBatch batch;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    OrthographicCamera uiCamera;
    Sprite sprite;
    MyActor myActor;
    Director director;
    Director uiDirector;
    TiledMap tiledmap;
    MapProperties mapProperties;
    JoypadControleur joyPadControleur;
    InputMultiplexer multiplexer;

    private Game game;


    public Test(Projet5001 game) {
        this.game = game;
        this.batch = game.batcher;
        //scale qui represente  le ratio de render de la map
        float unitScale = 1/32f;

        /**
         * La map et son renderer
         */
        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load("data/tmx/ageei2.tmx");
        mapProperties = tiledmap.getProperties();
        renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);


        /**
         * Tous ce  qui concerne la creation du player
         */
        sprite = new Sprite(new Texture(Gdx.files.internal("data/sprites/alttp-link1.png")));
        sprite.setSize(2,2);
        myActor = new MyActor(sprite);

        /**
         * Le directeur s'occupe de passé les event anisi que de faire le draw de model
         */
        director = new Director();


        /**
         * Le ui director va prendre en charge le draw de tous les objets du ui
         * va aussi achemier tous les event de keyboard et touch
         */
        uiDirector = new Director();

        /***
         * il est important d'utiliser un multiplexer pour
         * enregister tous les inputs. Pour le moment il est ici  mais idealement
         * il fautdrait avoir une seul multiplexeur pour le jeux sachant que l'on va
         * changer les "screen" souvent
         */
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiDirector);
        multiplexer.addProcessor(director);
        //playerItemMenu = new director()
        //ex: multiplexer.addProcessor(playerItemMenu);
        Gdx.input.setInputProcessor(multiplexer);


        /**
         * Permet a myActor de recevoir les event du keyboard
         */
        director.setKeyboardFocus(myActor);

        /**
         * On peu assi ajouter des listerner a partir de directeur vers un acteur specific voir
         * 	public void addTouchFocus (EventListener listener, Actor listenerActor, Actor target, int pointer, int button)
         * 	dans stage
         */
        KeyboardControleur.register(myActor);

        /**
         * Cree un nouceau touchpad
         */
        TouchpadStyle tps  = new TouchpadStyle();
        joyPadControleur = new JoypadControleur(10f,tps.getTouchpadStyle());

        //set size et position
        joyPadControleur.setBounds(0, 0, 200, 200);

        //enregister l'acteur qui va recevoir les evenements
        joyPadControleur.register(myActor);

        //enregistre le joypad au bon directeur
        uiDirector.addActor(joyPadControleur);

        //enregistre myactor pour etre render dans le director
        director.addActor(myActor);
    }


    @Override
    public void render(float delta) {
        draw();
    }

    public void draw() {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera = (OrthographicCamera)director.getCamera();
        //render sur la surface de la fenetre le 30x20 de la map
        camera.setToOrtho(false,30,20);


        camera.position.set(myActor.getX(),myActor.getY(),0f);
        camera.update();

        renderer.setView(camera);
        renderer.render();

        uiCamera = (OrthographicCamera)uiDirector.getCamera();
        uiCamera.setToOrtho(false, 640, 480);

        uiDirector.act();
        uiDirector.draw();


        director.act();
        director.draw();

    }
}
