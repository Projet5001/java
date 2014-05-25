package com.projet5001.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;

public class Director extends Stage implements InputProcessor {

    Director director;

    public Director() {
        super();
        Gdx.input.setInputProcessor(this);

    }

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
