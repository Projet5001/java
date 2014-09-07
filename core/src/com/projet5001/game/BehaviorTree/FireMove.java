package com.projet5001.game.BehaviorTree;

import com.projet5001.game.actors.Npc;
import com.projet5001.game.events.MovementEvents;
import com.projet5001.game.pathfinding.Node;

public class FireMove extends Routine {
    @Override
    public void act(Npc npc) {
        if (isNodeListValid(npc)){
            Node node = npc.getPathFinding().pop();
            if(node.x < npc.getX()) {
                npc.fire(new MovementEvents(MovementEvents.Type.moveLeft));
            }
            else if(node.x > npc.getX()) {
                npc.fire(new MovementEvents(MovementEvents.Type.moveRight));
            }
            else if(node.y < npc.getY()) {
                npc.fire(new MovementEvents(MovementEvents.Type.moveDown));
            }
            else if(node.y > npc.getY()) {
                npc.fire(new MovementEvents(MovementEvents.Type.moveUp));
            }
        }
    }
    
    private boolean isNodeListValid(Npc npc) {
        return (npc.getPathFinding() != null) && (npc.getPathFinding().peek() != null);
    }

    @Override
    public void reset() {

    }
}
