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

    Skin skin;
    BitmapFont font;
    Projet5001 game;
    Director director;
    TextButton button;
    TextButton button2;
    TextButton button3;

    public Menu( Projet5001 p) {
        this.game = p;


        //test de label
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        director = new Director();

        //voir le dossier boutton pour les fichier qui sont recquis et aussi le fichier json
        //Le boutton cree une nouvelle vue(screen) qui contient le jeux.

        // un skin contient toutes les information pour cree le ui d'un boutton, voir le dossier json
        skin = new Skin(Gdx.files.internal("data/button/uiskin.json"));

        //Le textButton qui contient un text et un skin avec le param default (voir fichier json)
        button = new TextButton("Lance le jeux", skin, "default");
        button.setWidth(200);
        button.setCenterPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
        director.addActor(button);

        button2 = new TextButton("Lance le testCollision", skin, "default");
        button2.setWidth(200);
        button2.setCenterPosition(button.getCenterX(),button.getCenterY()+100);
        director.addActor(button2);

        button3 = new TextButton("Options", skin, "default");
        button3.setWidth(200);
        button3.setCenterPosition(button2.getCenterX(),button2.getCenterY()+100);
        director.addActor(button3);

        CheckBox checkBox = new CheckBox("dev",skin, "default");
        checkBox.setPosition(100,100);
        director.addActor(checkBox);


        Label label = new Label(Gdx.files.getExternalStoragePath(), new Label.LabelStyle(font, font.getColor()));
        label.setPosition(10, 10);
        director.addActor(label);


        //n'est pas la seul facon d'ajout les input mais permet de le faire a la vole
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Lance le screen test
                //ajouter une methode qui sauve l'etat du screen actuel
                game.setScreen(new Test(game));
                return true;
            }
        });

        button2.addListener(new InputListener() {
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
