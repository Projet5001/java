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
    Sprite sprite;
    MyActor myActor;
    Director director;
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

        float unitScale = 1 / 16f;


        /**
         * La map , la caméra et le renderer
         */
        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load("data/tmx/ageei2.tmx");
        mapProperties = tiledmap.getProperties();
        camera = new OrthographicCamera((Integer) mapProperties.get("tileheight"), (Integer) mapProperties.get("tilewidth"));
        renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);

        /**
         * Tous ce  qui concerne la creation du player
         */
        sprite = new Sprite(new Texture(Gdx.files.internal("data/sprites/alttp-link1.png")));
        myActor = new MyActor(sprite);


        /**
         * Le directeur s'occupe de passé les event anisi que de faire le draw de model
         */
        director = new Director();
        director.addActor(myActor);

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
        //setBounds(x,y,width,height)
        joyPadControleur.setBounds(70, 70, 200, 200);

        joyPadControleur.register(myActor);

        director.addActor(joyPadControleur);
    }


    @Override
    public void render(float delta) {
        draw();
    }

    public void draw() {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(0f, 0f, 0f);
        camera.update();

        director.act();
        director.draw();

        batch.begin();
        renderer.setView(camera);
        renderer.render();
        camera.update();
        batch.end();
    }
}
