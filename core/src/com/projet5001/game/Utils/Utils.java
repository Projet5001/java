package com.projet5001.game.Utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by macmata on 27/08/14.
 */
public class Utils {
    public static Vector2 getKeyFromVector(Vector2 vector2, int hashSize) {
        float x = (float) Math.floor(vector2.x / hashSize);
        float y = (float) Math.floor(vector2.y / hashSize);
        return vector2.set(x, y);
    }

    public static boolean equals(Vector2 v1 , Vector2 v2){
        return v1.x == v2.x && v1.y == v2.y;
    }
}
