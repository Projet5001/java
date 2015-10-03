/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexandre Leblanc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.projet5001.game.controleur;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.projet5001.game.Config.GameConfig;
import com.projet5001.game.actors.MyActor;
import com.projet5001.game.events.MovementEvents;

public class JoypadControleur extends Touchpad {
    private Actor myActor;

    public JoypadControleur(float deadzoneRadius, TouchpadStyle skin) {

        super(deadzoneRadius, skin);
        setVisible();
    }

    public void register(MyActor myActor) {

        this.myActor = myActor;

    }

    public void setVisible() {
        if (GameConfig.isAndroid) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }

    public void resetSizePosition() {

        this.setBounds(0f, 0f, 200, 200);

    }

    public void act(float time) {
        if (GameConfig.joypadView) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
        if (GameConfig.joypadConfigPosition) {

            if (isTouched()) {

                this.setPosition(getX() + getKnobPercentX(), getY() + getKnobPercentY());

            }
        } else if (GameConfig.joypadConfigSize) {

            this.setSize(getWidth() + getKnobPercentX(), getHeight() + getKnobPercentY());

        } else {

            if (isTouched()) {

                if (Math.abs(getKnobPercentX()) > 0.5 && getKnobX() > 100) {
                    this.myActor.fire(new MovementEvents(MovementEvents.Type.moveRight));
                } else if (Math.abs(getKnobPercentX()) > 0.5 && getKnobX() < 100) {
                    this.myActor.fire(new MovementEvents(MovementEvents.Type.moveLeft));
                } else if (Math.abs(getKnobPercentY()) > 0.5 && getKnobY() < 100) {
                    this.myActor.fire(new MovementEvents(MovementEvents.Type.moveDown));
                } else if (Math.abs(getKnobPercentY()) > 0.5 && getKnobY() > 100) {
                    this.myActor.fire(new MovementEvents(MovementEvents.Type.moveUp));
                }
                if (Math.abs(getKnobPercentY()) > 0.5 ^ Math.abs(getKnobPercentX()) > 0.5) {
                    return;
                }
                if (Math.abs(getKnobPercentY()) > 0.5 && Math.abs(getKnobPercentX()) > 0.5) {
                    return;
                }
            }
        }
    }
}
