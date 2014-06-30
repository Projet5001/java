package controls;

import java.util.ArrayList;

/**
 * Classe SceneControls ,
 * Usage : champ de la classe SceneTemplate en Arraylist,
 * permet d'ajouter et de supprimer des controlleur d'une scene.
 */
public class SceneControls {

    String NULL_PTR_EXC_MSG = "Probleme SceneControls.java:accControl(ob)";
    String NULL_PTR_EXC_MSG2 = "Probleme SceneControls.java:addControlList(ob)";
    String NULL_PTR_EXC_MSG3 = "Probleme SceneControls.java:removeControl(ob)";

    private static volatile SceneControls controls = null;
    private static volatile ArrayList<ControlEntity> controlList = null;

    private SceneControls() {
        this.controlList = new ArrayList<ControlEntity>();
    }

    public SceneControls getInstance() {
        if (controls == null) {
            controls = new SceneControls();
        }
        return controls;
    }

    public boolean addControl(ControlEntity controlToAdd) {
        try {
            this.controlList.add(controlToAdd);
            return true;
        } catch (NullPointerException sceneControlNullPtrException) {
            System.out.println(NULL_PTR_EXC_MSG);
            return false;
        }
    }

    public boolean addControlList(ArrayList<ControlEntity> listToAdd) {
        try {
            this.controlList.addAll(listToAdd);
            return true;
        } catch (NullPointerException sceneControlNullPtrException) {
            System.out.println(NULL_PTR_EXC_MSG2);
            return false;
        }
    }

    public boolean removeControl(ControlEntity controlToRemove) {
        try {
            this.controlList.remove(controlToRemove);
            return true;
        } catch (NullPointerException sceneControlNullPtrException) {
            System.out.println(NULL_PTR_EXC_MSG3);
            return false;
        }
    }

}
