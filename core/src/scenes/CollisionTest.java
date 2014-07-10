package scenes;

import actors.MyActor;
import collisions.WorldCollector;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.projet5001.game.Projet5001;
import controls.Director;
import controls.JoypadControleur;
import controls.KeyboardControleur;
import views.TouchpadStyle;

/**
 * Created by macmata on 31/05/14.
 */
public class CollisionTest extends ScreenAdapter {

    TouchpadStyle tps;
    SpriteBatch batch;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera worldCamera;
    OrthographicCamera uiCamera;


    Director worldDirector;
    Director uiDirector;

    TiledMap tiledmap;
    MapProperties mapProperties;
    JoypadControleur joyPadControleur;
    InputMultiplexer multiplexer;
    KeyboardControleur Keyboard;

    MyActor myActor;
    MyActor myActor2;


    private Game game;


    public CollisionTest(Projet5001 game) {
        this.game = game;
        this.batch = game.batcher;
        float unitScale = 1/32f;

        tiledmap = new TmxMapLoader(new InternalFileHandleResolver()).load("data/tmx/ageei2.tmx");
        mapProperties = tiledmap.getProperties();
        renderer = new OrthogonalTiledMapRenderer(tiledmap, unitScale);


        myActor = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));

        worldDirector = new Director();

        uiDirector = new Director();

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiDirector);
        multiplexer.addProcessor(worldDirector);

        Gdx.input.setInputProcessor(multiplexer);

        worldDirector.setKeyboardFocus(myActor);

        Keyboard.register(myActor);

        tps  = new TouchpadStyle();
        joyPadControleur = new JoypadControleur(10f,tps.getTouchpadStyle());

        joyPadControleur.setBounds(0, 0, 200, 200);

        joyPadControleur.register(myActor);

        uiDirector.addActor(joyPadControleur);

        worldDirector.addActor(myActor);

        for (int i = 3; i< 10; i ++ ){
            myActor2 = new MyActor(new Texture(Gdx.files.internal("data/sprites/perso.png")));
            myActor2.setPosition(i,i);
            worldDirector.addActor(myActor2);

        }
    }


    @Override
    public void render(float delta) {
       WorldCollector.collection().addAll(worldDirector.getGroupActeurs());
       worldDirector.act();
       uiDirector.act();
       draw();
    }

    public void draw() {

        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldCamera = (OrthographicCamera) worldDirector.getCamera();
        worldCamera.setToOrtho(false, 30, 20);
        worldCamera.position.set(myActor.getX(),myActor.getY(),0f);
        worldCamera.update();

        renderer.setView(worldCamera);
        renderer.render();

        uiCamera = (OrthographicCamera)uiDirector.getCamera();
        uiCamera.setToOrtho(false, 640, 480);
        uiCamera.update();

        worldDirector.debug();
        worldDirector.draw();
        uiDirector.draw();

    }
}
