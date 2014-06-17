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
    OrthographicCamera guiCam;
    Projet5001 game;
    ShapeRenderer shapeRenderer;
    Label label;
    Director director;
    TextButton button;
    Table table;
    JoypadControleur joyPadControleur;
    Touchpad.TouchpadStyle touchpadStyle;
    Skin touchpadSkin;
    Drawable touchBackground;
    Drawable touchKnob;


    public Menu(final Projet5001 game) {
        String extRoot = Gdx.files.getExternalStoragePath();
        String locRoot = Gdx.files.getLocalStoragePath();
        System.out.println("ext" + extRoot);
        System.out.println("loc" + locRoot);

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
        table = new Table();
        table.debug();
        table.setFillParent(true);
        table.add(button);
        table.add(label);
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Lance le scree test
                game.setScreen(new Test(game));
                return true;
            }
        });


        touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("touchBackground", new Texture("data/joyPadControleur/touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("data/joyPadControleur/touchKnob.png"));
        //Create TouchPad Style
        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
        joyPadControleur = new JoypadControleur(10f, touchpadStyle);
        //setBounds(x,y,width,height)
        joyPadControleur.setBounds(30, 30, 200, 200);

        director = new Director();
        director.addActor(table);

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
