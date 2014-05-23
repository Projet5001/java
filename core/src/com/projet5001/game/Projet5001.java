package com.projet5001.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Projet5001 extends ApplicationAdapter {
    SpriteBatch batch;

    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    Sprite sprite;

    MyActor myActor;
    Director director;

    @Override
    public void create () {
        float unitScale = 1 / 16f;

        batch = new SpriteBatch();



        TiledMap tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load("assets/ageei2.tmx");

        camera = new OrthographicCamera((Integer)tiledmap.getProperties().get("tileheight"),
                (Integer)tiledmap.getProperties().get("tilewidth"));

        renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);

        sprite = new Sprite(new Texture(Gdx.files.internal("assets/alttp-link1.png")));
        sprite.setPosition(0, 0);
        myActor = new MyActor(sprite);


        director = new Director();
        director.addActor(myActor);


    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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