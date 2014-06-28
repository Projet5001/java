package com.projet5001.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public class Director extends Stage {

    Director director;
    private Actor keyboardFocus;

    /**
     * Crée un nouveau directeur et l'enregistre automatique au system de input
     */
    public Director() {
        super();
    }

    /**
     * Lance les envent privé a tous les Acteur contenue dans le directeur.
     *
     * @param e
     */
    public void fire(Event e) {
        SnapshotArray array = this.getRoot().getChildren();

        Object[] items = array.begin();
        for (int i = 0, n = array.size; i < n; i++) {
            MyActor item = (MyActor) items[i];
            item.fire(e);
        }
        array.end();
    }

    public void debug( ) {
        ShapeRenderer rect = new ShapeRenderer();
        rect.setProjectionMatrix(this.getCamera().combined);
        Array<Actor> array = this.getActors();
        for (int i = 0, n = array.size; i < n; i++) {

            Actor actor = array.get(i);
            if (actor instanceof MyActor){
                rect.begin(ShapeRenderer.ShapeType.Filled);
                rect.setColor(0, 1, 0, 1);
                rect.rect(actor.getX()-0.5f,actor.getY()-0.5f,actor.getWidth()+ 1 ,actor.getHeight()+1);
                rect.end();
            }
        }
    }
}
