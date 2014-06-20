package com.projet5001.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by macmata on 31/05/14.
 */
public class Test extends ScreenAdapter {
    SpriteBatch batch;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    OrthographicCamera camera2;
    Sprite sprite;
    MyActor myActor;
    Director director;
    Director uiDirector;
    TiledMap tiledmap;
    MapProperties mapProperties;
    JoypadControleur joyPadControleur;
    Touchpad.TouchpadStyle touchpadStyle;
    Skin touchpadSkin;
    Drawable touchBackground;
    Drawable touchKnob;


    private Game game;


    public Test(Projet5001 game) {
        this.game = game;
        this.batch = game.batcher;

        float unitScale = 1/32f;


        /**
         * La map , la caméra et le renderer
         */
        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load("data/tmx/ageei2.tmx");
        mapProperties = tiledmap.getProperties();
        //camera2 = new OrthographicCamera();
        //camera2.setToOrtho(false,640, 480);
        //camera2.update();
        //camera2 = new OrthographicCamera((Integer) mapProperties.get("tileheight"), (Integer) mapProperties.get("tilewidth"));
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
        director.addActor(myActor);
        uiDirector = new Director();
        /**
         * Permet a myActor de recevoir les event du keyboard
         */
        uiDirector.setKeyboardFocus(myActor);

        /**
         * On peu assi ajouter des listerner a partir de directeur vers un acteur specific voir
         * 	public void addTouchFocus (EventListener listener, Actor listenerActor, Actor target, int pointer, int button)
         * 	dans stage
         */
        KeyboardControleur.register(myActor);

        touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("touchBackground", new Texture("data/joyPadControleur/touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("data/joyPadControleur/touchKnob.png"));
        //Create TouchPad Style
        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
        joyPadControleur = new JoypadControleur(10f, touchpadStyle);

        joyPadControleur.setBounds(0, 0, 200, 200);


        joyPadControleur.register(myActor);


        uiDirector.addActor(joyPadControleur);
    }


    @Override
    public void render(float delta) {
        draw();
        /*
        batch.setProjectionMatrix(camera2.combined);
        batch.begin();
        joyPadControleur.draw(batch,1f);
        batch.end();
        */
    }

    public void draw() {
        /**
         * Dans ce cas si tous les objet(acteur, joypad et map sont bounder a la
         * camera de directeur)
         * Et si on bounday le ui a une autre camera ?
         */
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera = (OrthographicCamera)director.getCamera();
        camera.setToOrtho(false,30,20);


        camera.position.set(myActor.getX(),myActor.getY(),0f);
        camera.update();

        renderer.setView(camera);
        renderer.render();

        camera2 = (OrthographicCamera)uiDirector.getCamera();
        //camera2.setToOrtho(false,640,480);
        uiDirector.act();
        uiDirector.draw();


        director.act();
        director.draw();





        System.out.print("viewportX");
        System.out.println(director.getViewport().getViewportX());

        System.out.print("viewportY");
        System.out.println(director.getViewport().getViewportY());

        System.out.print("viewportHeight");
        System.out.println(director.getViewport().getViewportHeight());
        System.out.print("viewportWidth");
        System.out.println(director.getViewport().getViewportWidth());

        System.out.print("world");
        System.out.println(director.getViewport().getWorldWidth());

    }
}
