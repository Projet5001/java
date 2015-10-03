/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexandre Leblanc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.projet5001.game.Ai.BehaviorTree.Leaf;

import com.projet5001.game.Ai.BehaviorTree.Routine;
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
