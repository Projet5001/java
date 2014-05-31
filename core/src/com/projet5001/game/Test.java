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

    private Game game;


    public Test(Projet5001 game) {
        this.game = game;
        float unitScale = 1 / 16f;

        batch = new SpriteBatch();

        /**
         * La map , la caméra et le renderer
         */
        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load("assets/ageei2.tmx");
        mapProperties = tiledmap.getProperties();
        camera = new OrthographicCamera((Integer) mapProperties.get("tileheight"), (Integer) mapProperties.get("tilewidth"));
        renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);

        /**
         * Tous ce  qui concerne la creation du player
         */
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/alttp-link1.png")));
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
        MyActorControler.register(myActor);

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

        director.draw();
        batch.begin();
        renderer.setView(camera);
        renderer.render();
        camera.update();
        batch.end();
    }
}
