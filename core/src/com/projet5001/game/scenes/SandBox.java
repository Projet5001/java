/*
 * Copyright [2014] [Alexandre Leblanc]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.projet5001.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.projet5001.game.Config.GameConfig;
import com.projet5001.game.Config.GameConfigView;
import com.projet5001.game.Projet5001;
import com.projet5001.game.controleur.MapControleur;
import com.projet5001.game.collisions.WorldCollector;


/**
 * Created by macmata on 31/05/14.
 */
public class SandBox extends ScreenAdapter {

    OrthographicCamera worldCamera;
    OrthographicCamera uiCamera;
    InputMultiplexer multiplexer;
    MapControleur mapControleur;
    GameConfigView gameConfig;

    private Projet5001 game;
    private float elapsedTime = 0;

    public SandBox(Projet5001 p) {
        this.game = p;
        if(GameConfig.devMode){


            if(GameConfig.isWindows){

                System.out.println("dev mode sandbox loaded");
                mapControleur = new MapControleur(new ExternalFileHandleResolver(),"projet5001\\tmx\\sandbox.tmx");

            }else if(GameConfig.isPosix){

                System.out.println("dev mode sandbox loaded");
                mapControleur = new MapControleur(new ExternalFileHandleResolver(),"projet5001/tmx/sandbox.tmx");

            }else{

                System.out.println("os not compatible");
                game.setScreen(new Menu(game));

            }

        }else{
            mapControleur = new MapControleur(new InternalFileHandleResolver(),"data/tmx/sandbox.tmx");
        }
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
