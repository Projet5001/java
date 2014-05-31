package com.projet5001.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;

public class Director extends Stage {

    Director director;
    private Actor keyboardFocus;

    /**
     * Crée un nouveau directeur et l'enregistre automatique au system de input
     */
    public Director() {
        super();
        Gdx.input.setInputProcessor(this);
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
}
