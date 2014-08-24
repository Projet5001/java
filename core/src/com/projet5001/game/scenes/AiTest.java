package com.projet5001.game.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.projet5001.game.Config.GameConfig;
import com.projet5001.game.Config.GameConfigView;
import com.projet5001.game.Projet5001;
import com.projet5001.game.collisions.WorldCollector;
import com.projet5001.game.controleur.MapControleur;

public class AiTest extends ScreenAdapter {

    OrthographicCamera worldCamera;
    OrthographicCamera uiCamera;
    InputMultiplexer multiplexer;
    MapControleur mapControleur;
    GameConfigView gameConfig;

    private Projet5001 game;

    public AiTest(Projet5001 p) {
        this.game = p;

        mapControleur = new MapControleur(new InternalFileHandleResolver(),"data/tmx/sandbox2.tmx");
        gameConfig = new GameConfigView();

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(Projet5001.uiDirector);
        multiplexer.addProcessor(Projet5001.worldDirector);

        Gdx.input.setInputProcessor(multiplexer);

    }
    public void dispose () {
        Projet5001.worldDirector.dispose();
        Projet5001.uiDirector.dispose();
    }

    @Override
    public void render(float delta) {
        WorldCollector.collection().addAll(Projet5001.worldDirector.getGroupActeurs());
        act();
        draw();
        WorldCollector.collection().clear();
    }

    public void act(){
        Projet5001.worldDirector.act();
        Projet5001.uiDirector.act();
    }

    public void draw() {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldCamera = (OrthographicCamera) Projet5001.worldDirector.getCamera();
        worldCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        worldCamera.position.set(Projet5001.worldDirector.getPlayer().getX(),Projet5001.worldDirector.getPlayer().getY(),0f);
        worldCamera.update();

        mapControleur.setView(worldCamera);
        mapControleur.renderGround();

        uiCamera = (OrthographicCamera)Projet5001.uiDirector.getCamera();
        uiCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCamera.update();

        if(GameConfig.debugMode){
            mapControleur.debug(worldCamera);
            Projet5001.worldDirector.debug();
        }

        Projet5001.worldDirector.draw();
        this.mapControleur.renderTop();
        Projet5001.uiDirector.draw();

    }
}

