/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexandre Leblanc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.projet5001.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.projet5001.game.Config.GameConfig;
import com.projet5001.game.Projet5001;
import com.projet5001.game.controleur.Director;


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
        System.out.println(GameConfig.isAndroid);

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
