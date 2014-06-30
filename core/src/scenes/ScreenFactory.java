package scenes;

import java.util.ArrayList;

/**
 * Classe ScreenFactory produit une scene
 * Patron de conception GoF : Singleton
 */
public class ScreenFactory extends SceneTemplate {

    private static volatile SceneTemplate theScreen = null;

    private ScreenFactory(){
        super();
    }

    public SceneTemplate getInstance(){
        if(theScreen == null) {
            theScreen = new ScreenFactory();
        }

        return theScreen;
    }

}
