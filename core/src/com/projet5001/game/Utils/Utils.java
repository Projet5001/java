/*
 * Copyright [2014] [Alexandre Leblanc]
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
 */

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
