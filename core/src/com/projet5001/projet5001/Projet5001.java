package com.projet5001.projet5001;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class Projet5001 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TmxMapLoader tmxloader;
	TiledMap tiledmap;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		float unitScale = 1 / 16f;
		
		batch = new SpriteBatch();
		tmxloader = new TmxMapLoader();
		tiledmap = tmxloader.load("../assets/ageei2.tmx");
		
		camera = new OrthographicCamera((Integer)tiledmap.getProperties().get("tileheight"),
										(Integer)tiledmap.getProperties().get("tilewidth"));
		
		renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.begin();
		renderer.setView(camera);
		renderer.render();
		//batch.draw(img, 0, 0);
		camera.translate(1, 1);
		camera.update();
		batch.end();
	}
}
