package scenes;

import java.util.ArrayList;

/**
 * Classe SceneCareTaker
 * Sert à stocker les SceneTemplate (les scenes)
 * TODO Restaurer une scene.
 */

public class SceneCareTaker {

    private static ArrayList<SceneTemplate> listOfScenes = null;

    String NULL_PTR_EXC_CARE_TAKER = "Pointeur null sur arraylist SceneCaretTaker:getSelectedScene";

    public SceneCareTaker(){
        listOfScenes = new ArrayList<SceneTemplate>();
    }

    public SceneTemplate getSelectedScene(SceneTemplate sceneToGet){

        try {

           int indexOfScene = listOfScenes.indexOf(sceneToGet);
           return listOfScenes.get(indexOfScene);

        } catch(NullPointerException nullPtrExceptionCareTaker){
            System.out.println(NULL_PTR_EXC_CARE_TAKER);
            return null;
        }

    }

    public boolean addScene(SceneTemplate sceneToAdd) {
        try {
           listOfScenes.add(sceneToAdd);
           return true;
        } catch (NullPointerException exc){
            System.out.println();
            return false;
        }
    }

    public boolean removeScene(SceneTemplate sceneToRemove){
        try{
            listOfScenes.remove(sceneToRemove);
            return true;
        }catch(NullPointerException excp){
            System.out.println();
            return false;
        }
    }

    public boolean clearAllScenes(){
      try {
          listOfScenes.clear();
          return true;
      } catch (Exception e){
          System.out.println();
          return false;
      }
    }
}
