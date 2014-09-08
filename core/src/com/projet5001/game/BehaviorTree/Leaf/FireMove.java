/*******************************************************************************
 * Copyright 2014 Projet5001
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
 ******************************************************************************/

package com.projet5001.game.BehaviorTree.Leaf;

import com.projet5001.game.BehaviorTree.Routine;
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
            succeed();
        }else{
            fail();
            System.out.println("no path");
        }
    }
    
    private boolean isNodeListValid(Npc npc) {
        return (npc.getPathFinding() != null) && (npc.getPathFinding().peek() != null);
    }

    @Override
    public void reset() {

    }
}
