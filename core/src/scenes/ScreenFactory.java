package scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenFactory extends ScreenAdapter{

    private static volatile ScreenAdapter theScreen = null;

    public ScreenAdapter getInstance(){

        if(theScreen == null) {
            theScreen = new ScreenAdapter();
        }

        return theScreen;

    }

}
