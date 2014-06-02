package com.projet5001.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class Menu implements Screen {


    Skin skin;
    BitmapFont font;
    OrthographicCamera guiCam;
    Projet5001 game;
    ShapeRenderer shapeRenderer;
    Label label;
    Director director;
    TextButton button;

    public Menu(final Projet5001 game) {

        this.game = game;
        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);

        shapeRenderer = new ShapeRenderer();

        //test de label
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        label = new Label("Bonjour", new Label.LabelStyle(font, font.getColor()));

        //voir le dossier boutton pour les fichier qui sont recquis et aussi le fichier json
        //Le boutton cree une nouvelle vue(screen) qui contient le jeux.

        // un skin contient toutes les information pour cree le ui d'un boutton, voir le dossier json
        skin = new Skin(Gdx.files.internal("data/button/uiskin.json"));

        //Le textButton qui contient un text et un skin avec le param default (voir fichier json)
        button = new TextButton("Lance le jeux", skin, "default");
        button.setPosition(100, 100);


        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Lance le scree test
                game.setScreen(new Test(game));
                return true;
            }
        });


        director = new Director();
        director.addActor(label);
        director.addActor(button);

    }

    public void draw() {
        GL20 gl = Gdx.gl20;
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(0, 0, 50, 50);
        shapeRenderer.end();

        game.batcher.begin();
        director.draw();
        game.batcher.end();


    }

    @Override
    public void render(float delta) {
        draw();
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
