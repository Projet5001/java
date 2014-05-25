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
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class Projet5001 extends Game {
    SpriteBatch batch;

    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    Sprite sprite;
    Sprite sprite1;
    MyActor myActor1;
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
        sprite1 = new Sprite(new Texture(Gdx.files.internal("assets/alttp-link1.png")));
        myActor = new MyActor(sprite);
        myActor1 = new MyActor(sprite1);
        myActor1.setName("bob");


        myActor1.addListener(new ContainerListener() {

            public boolean SimpleContainer(ContainerEvent containerEvent) {
                System.out.println("ca fonctionne");
                return false;
            }

        });

        myActor.addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                System.out.println("testDrag");
                System.out.printf("%2f %2f", this.getDeltaX(), this.getDeltaY());

                ((MyActor) event.getTarget()).moveByC(-this.getDeltaX(), -this.getDeltaY());
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("testTouchDown");
                ContainerEvent e = new ContainerEvent();
                e.setType(ContainerEvent.Type.SimpleContainer);
                e.setStage(director);
                director.fire(e);
                return super.touchDown(event, x, y, pointer, button);
            }
        });


        director = new Director();

        director.addActor(myActor1);
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