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

package com.projet5001.game.Utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by macmata on 27/08/14.
 */
public class Utils {
    public static Vector2 getKeyFromVector(Vector2 vector2, int hashSize) {
        float x = (float) Math.floor(vector2.x / hashSize);
        float y = (float) Math.floor(vector2.y / hashSize);
        return vector2.set(x, y);
    }

    public static double calulEuclideanDist(Actor actor1, Actor actor2){
        return Math.sqrt(Math.pow(actor1.getX() - actor2.getX(), 2) + Math.pow(actor1.getY() - actor2.getY(), 2));
    }

    public static double calulManhattanDist(Actor actor1, Actor actor2){
        return (Math.abs(actor1.getX() - actor2.getX()) + (Math.abs(actor1.getY() - actor2.getY())));
    }

    public static boolean equals(Vector2 v1 , Vector2 v2){
        return v1.x == v2.x && v1.y == v2.y;
    }
}
