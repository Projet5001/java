package controls;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import actors.MyActor;

import java.util.Arrays;
import java.util.List;

public class Director extends Stage {

    String ERREUR_EFFACER_ACTEUR = "Impossible d'effacer cet acteur.";
    String ERREUR_EFFACER_TOUS_LES_ACTEUR = "Impossible d'effacer la liste d'acteur";
    String ERREUR_AJOUT_LISTE_ACTEUR = "Impossible d'ajouter la liste d'acteur.";

    private Actor keyboardFocus;

    /**
     * Constructeur Director
     * Crée un nouveau directeur et l'enregistre automatique au system de input
     */
    public Director() { super(); }

    /**
     * Fonction fire(...)
     * Lance les envent privé a tous les Acteur contenue dans le directeur.
     * @param e l'évènement à propager.
     */
    public void fire(Event e) {
        SnapshotArray array = this.getRoot().getChildren();

        Object[] items = array.begin();
        for (int i = 0, n = array.size; i < n; i++) {
            MyActor item = (MyActor) items[i];
            item.fire(e);
        }
        array.end();
    }

    /**
     * fonction addActorListToDirector
     * @param listToAdd
     * @return boolean true si ajouté, false sinon.
     */
    public boolean addActorListToDirector(Array<Actor> listToAdd){
        try{
            this.getListeActeur().addAll(listToAdd);
            return true;
        }catch(OutOfMemoryError memoryException){
            System.out.println(ERREUR_AJOUT_LISTE_ACTEUR);
            return false;
        }
    }

    /**
     * fonction getListeActeur()
     * Wrapper pour getActor() provenant de la super classe.
     *
     * @return Array<Actor> la liste des acteurs.
     */
    public Array<Actor>  getListeActeur(){
       return this.getRoot().getChildren();
    }

    /**
     * fonction getGroupActeurs()
     * Wrapper pour getRoot() provenant de la super classe.
     *
     * @return Group contenant la liste des acteurs.
     */
    public Group  getGroupActeurs(){
        return this.getRoot();
    }

    /**
     * fonction deleteActor
     * Efface l'acteur passé en paramètre.
     * @param actorToDelete l'acteur à effacer de la liste.
     * @return boolean true si l'acteur à été effacer, false sinon.
     */
    public boolean deleteActor(Actor actorToDelete){
        return this.getRoot().removeActor(actorToDelete);
    }

    /**
     * fonction deleteAllActor
     * Efface tous les acteurs d'un director.
     * @return boolean true si la liste d'acteur à été effacer, false sinon.
     */
    public void deleteAllActors(){
        this.getRoot().clear();
    }

    /**
     * fonction debug()
     */
    public void debug( ) {
        ShapeRenderer rect = new ShapeRenderer();
        rect.setProjectionMatrix(this.getCamera().combined);
        Array<Actor> array = this.getActors();
        for (int i = 0, n = array.size; i < n; i++) {

            Actor actor = array.get(i);
            if (actor instanceof MyActor){
                rect.begin(ShapeRenderer.ShapeType.Filled);
                rect.setColor(0, 1, 0, 1);
                rect.rect(actor.getX()-0.5f,actor.getY()-0.5f,actor.getWidth()+ 1 ,actor.getHeight()+1);
                rect.end();
            }
        }
    }

}
