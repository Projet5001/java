package controls;

import actors.MyActor;

/**
 * Interface ControlEntity
 * Sert à uniformiser les controlleurs afin qu'on puisse les manipuler tous dans une liste (propriété d'une scene->SceneTemplate).
 */
public interface ControlEntity {
    public void register(final MyActor myActor);
}
