package com.projet5001.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Projet5001 extends Game {
    SpriteBatch batch;

    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    Sprite sprite;
    MyActor myActor;
    Director director;
    TiledMap tiledmap;
    MapProperties mapProperties;
    @Override
    public void create () {
        float unitScale = 1 / 16f;

        batch = new SpriteBatch();


        /**
         * La map , la caméra et le renderer
         */
        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load("assets/ageei2.tmx");
        mapProperties = tiledmap.getProperties();
        camera = new OrthographicCamera((Integer)mapProperties.get("tileheight"),(Integer)mapProperties.get("tilewidth"));
        renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);

        /**
         * Tous ce  qui concerne la creation du player
         */
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/alttp-link1.png")));
        myActor = new MyActor(sprite);

        myActor.addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                System.out.println("test");
                return false;
            }
        });
        /**
         * Le directeur s'occupe de passé les event anisi que de faire le draw de model
         */
        director = new Director();
        director.addActor(myActor);


    }

    @Override
    public void render () {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.position.set(0f, 0f, 0f);
        camera.update();

        director.draw();
        batch.begin();
        renderer.setView(camera);
        renderer.render();
        camera.update();
        batch.end();
    }
}