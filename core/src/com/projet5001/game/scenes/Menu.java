/*******************************************************************************
 * Copyright 2014 Projet5001
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
 ******************************************************************************/

package com.projet5001.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.projet5001.game.Config.GameConfig;
import com.projet5001.game.controleur.Director;
import com.projet5001.game.Projet5001;


public class Menu implements Screen {



    Projet5001 game;
    Director director;


    public Menu( Projet5001 p) {
        this.game = p;


        //test de label
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);

        director = new Director();

        //voir le dossier boutton pour les fichier qui sont recquis et aussi le fichier json
        //Le boutton cree une nouvelle vue(screen) qui contient le jeux.

        // un skin contient toutes les information pour cree le ui d'un boutton, voir le dossier json
        Skin skin = new Skin(Gdx.files.internal("data/button/uiskin.json"));


        TextButton sandBoxButton = new TextButton("Lance le testCollision", skin, "default");
        sandBoxButton.setWidth(200);
        sandBoxButton.setCenterPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        director.addActor(sandBoxButton);


        CheckBox checkBox = new CheckBox("dev",skin, "default");
        checkBox.setPosition(100, 100);
        director.addActor(checkBox);


        Label label = new Label(Gdx.files.getExternalStoragePath(), new Label.LabelStyle(font, font.getColor()));
        label.setPosition(10, 10);
        director.addActor(label);




        sandBoxButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new SandBox(game));
                return true;
            }
        });


        checkBox.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameConfig.devMode = !GameConfig.devMode;
                return false;
            }
        });


        //enregistre un seul input processor
        Gdx.input.setInputProcessor(director);

    }

    @Override
    public void render(float delta) {
        GL20 gl = Gdx.gl20;
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        director.draw();
        director.debug();
        director.act();
    }

    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void show() {

    }
    @Override
    public void hide() {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
