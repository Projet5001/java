package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
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
import actors.MyActor;
import com.projet5001.game.Projet5001;
import views.TouchpadStyle;

import controls.Director;
import controls.JoypadControleur;
import controls.KeyboardControleur;

/**
 * Created by macmata on 31/05/14.
 */
public class Test implements Screen {

    TouchpadStyle tps;
    SpriteBatch batch;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera worldCamera;
    OrthographicCamera uiCamera;
    Sprite sprite;
    MyActor myActor;
    Director worldDirector;
    Director uiDirector;
    TiledMap tiledmap;
    MapProperties mapProperties;
    JoypadControleur joyPadControleur;
    InputMultiplexer multiplexer;
    KeyboardControleur Keyboard;

    private Game game;


    public Test(Projet5001 game) {
        this.game = game;
        this.batch = game.batcher;
        //scale qui represente  le ratio de render de la map dans ce cas si 1/4 de taille de tilset
        float unitScale = 1/16f;

        /**
         * La map et son renderer
         */
        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load("data/tmx/ageei2.tmx");
        mapProperties = tiledmap.getProperties();
        renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);


        /**
         * Tous ce  qui concerne la creation du player
         */
        sprite = new Sprite(new Texture(Gdx.files.internal("data/sprites/alttp-link1.png")));
        sprite.setSize(4,4);
        myActor = new MyActor(sprite);

        /**
         * Le directeur s'occupe de passé les event anisi que de faire le draw de model
         */
        worldDirector = new Director();


        /**
         * Le ui worldDirector va prendre en charge le draw de tous les objets du ui
         * va aussi achemier tous les event de keyboard et touch
         */
        uiDirector = new Director();

        /***
         * il est important d'utiliser un multiplexer pour
         * enregister tous les inputs. Pour le moment il est ici  mais idealement
         * il fautdrait avoir une seul multiplexeur pour le jeux sachant que l'on va
         * changer les "screen" souvent
         */
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiDirector);
        multiplexer.addProcessor(worldDirector);
        //playerItemMenu = new worldDirector()
        //ex: multiplexer.addProcessor(playerItemMenu);
        Gdx.input.setInputProcessor(multiplexer);


        /**
         * Permet a myActor de recevoir les event du keyboard
         */
        worldDirector.setKeyboardFocus(myActor);

        /**
         * On peu assi ajouter des listerner a partir de directeur vers un acteur specific voir
         * 	public void addTouchFocus (EventListener listener, Actor listenerActor, Actor target, int pointer, int button)
         * 	dans stage
         */
        Keyboard.register(myActor);

        /**
         * Cree un nouceau touchpad
         */
        tps  = new TouchpadStyle();
        joyPadControleur = new JoypadControleur(10f,tps.getTouchpadStyle());

        //set size et position
        joyPadControleur.setBounds(0, 0, 200, 200);

        //enregister l'acteur qui va recevoir les evenements
        joyPadControleur.register(myActor);

        //enregistre le joypad au bon directeur
        uiDirector.addActor(joyPadControleur);

        //enregistre myactor pour etre render dans le worldDirector
        worldDirector.addActor(myActor);
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

    public void draw() {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldCamera = (OrthographicCamera) worldDirector.getCamera();
        //render sur la surface de la fenetre le 30x20 de la map
        worldCamera.setToOrtho(false, 60, 40);


        worldCamera.position.set(myActor.getX(),myActor.getY(),0f);
        worldCamera.update();

        renderer.setView(worldCamera);
        renderer.render();

        uiCamera = (OrthographicCamera)uiDirector.getCamera();
        uiCamera.setToOrtho(false, 640, 480);

        uiDirector.act();
        uiDirector.draw();

        worldDirector.debug();
        worldDirector.act();
        worldDirector.draw();

    }
}
