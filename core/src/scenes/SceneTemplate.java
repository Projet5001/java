package scenes;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import actors.MyActor;
import controls.Director;
import controls.SceneControls;

abstract public class SceneTemplate {

    protected InputMultiplexer multiplexer;
    protected ArrayList<Director> directors;
    protected ArrayList<MyActor> actors;
    protected SceneControls controls;
    protected ArrayList<OrthographicCamera> cameras;

    //Ajout de methodes
}
