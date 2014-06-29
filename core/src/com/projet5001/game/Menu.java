package com.projet5001.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class Menu implements Screen {


    Skin skin;
    BitmapFont font;
    Projet5001 game;
    ShapeRenderer shapeRenderer;
    Label label;
    Director director;
    TextButton button;
    Table table;
    Test aTest;





    public Menu(final Projet5001 game) {
        String extRoot = Gdx.files.getExternalStoragePath();
        String locRoot = Gdx.files.getLocalStoragePath();
        System.out.println("ext" + extRoot);
        System.out.println("loc" + locRoot);

        this.game = game;


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
        table = new Table();
        table.debug();
        table.setFillParent(true);
        table.add(button);
        table.add(label);

        //n'est pas la seul facon d'ajout les input mais permet de le faire a la vole
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Lance le screen test
                //ajouter une methode qui sauve l'etat du screen actuel
                aTest = new Test(game);
                game.setScreen(aTest);
                return true;
            }
        });


        director = new Director();
        director.addActor(table);

        //enregistre un seul input processor
        Gdx.input.setInputProcessor(director);

    }

    public void draw() {
        GL20 gl = Gdx.gl20;
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        director.draw();
        director.debug();

        game.batcher.begin();
        Table.drawDebug(director);
        game.batcher.end();


    }

    @Override
    public void render(float delta) {
        draw();
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
