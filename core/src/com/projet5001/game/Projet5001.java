package com.projet5001.game;

import com.badlogic.gdx.ApplicationAdapter;
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
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

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
        MapProperties mapProperties =  tiledmap.getProperties();
        camera = new OrthographicCamera((Integer)mapProperties.get("tileheight"),(Integer)mapProperties.get("tilewidth"));

        renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);

        sprite = new Sprite(new Texture(Gdx.files.internal("assets/alttp-link1.png")));

        myActor = new MyActor(sprite);


        myActor.addListener(new DragListener(){
            private float startDragX;
            private float startDragY;
            @Override
            public void dragStart(
                    InputEvent event,
                    float x,
                    float y,
                    int pointer) {
                startDragX = x;
                startDragY = y;
                myActor.setOrigin(startDragX,startDragY);
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                System.out.println("test");
                myActor.setPositionC(x, y);
            }
            public void dragStop (InputEvent event, float x, float y, int pointer) {
                myActor.setOrigin(x,y);
            }

        });

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