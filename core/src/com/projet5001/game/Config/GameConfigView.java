/*******************************************************************************
 * Copyright 2014 Projet5001
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.projet5001.game.Config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.projet5001.game.Projet5001;
import com.projet5001.game.controleur.Director;

/**
please look away ugly code... */
public class GameConfigView {

    private final Skin skin;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    VerticalGroup verticalGroup;
    Window window;


    public GameConfigView(){


        skin = new Skin(Gdx.files.internal("data/button/uiskin.json"));
        checkBox1 = new CheckBox("OptionMenu",skin, "default");
        checkBox1.setPosition(500,50);
        Projet5001.uiDirector.addActor(checkBox1);
        checkBox1.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                window.setVisible(!window.isVisible());
                if (window.isVisible()){
                    //todo
                }
                return false;
            }
        });
        checkBox2 = new CheckBox("debug",skin, "default");
        checkBox2.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameConfig.debugMode = !GameConfig.debugMode;
                return false;
            }
        });


        checkBox3 = new CheckBox("joypadConfigPosition",skin, "default");
        checkBox3.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameConfig.joypadConfigPosition = !GameConfig.joypadConfigPosition;
                return false;
            }
        });

        checkBox4 = new CheckBox("joypadConfigSize",skin, "default");
        checkBox4.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameConfig.joypadConfigSize = !GameConfig.joypadConfigSize;
                return false;
            }
        });

        checkBox5 = new CheckBox("joypadVisible",skin, "default");
        checkBox5.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameConfig.joypadView = !GameConfig.joypadView;
                if (GameConfig.joypadView){
                    checkBox4.setVisible(true);
                    checkBox3.setVisible(true);
                }else{
                    checkBox4.setVisible(false);
                    checkBox3.setVisible(false);
                }
                return false;
            }
        });

        window = new Window("Window",skin);
        window.setVisible(false);
        window.setCenterPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        window.setMovable(false);
        verticalGroup = new VerticalGroup();
        verticalGroup.addActor(checkBox2);
        verticalGroup.addActor(checkBox3);
        verticalGroup.addActor(checkBox4);
        verticalGroup.addActor(checkBox5);

        if (GameConfig.joypadView){
            checkBox4.setVisible(true);
            checkBox3.setVisible(true);
        }else{
            checkBox4.setVisible(false);
            checkBox3.setVisible(false);
        }

        window.setSize(250,150);
        window.add(verticalGroup);
        Projet5001.uiDirector.addActor(window);

    }
}
