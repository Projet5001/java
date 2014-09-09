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
package com.projet5001.game.Ai.BehaviorTree;

import com.projet5001.game.actors.Npc;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * If true, continue
 */
public class Sequence extends Routine {

    protected Routine currentRoutine;
    List<Routine> routines = new LinkedList<Routine>();
    Queue<Routine> routineQueue = new LinkedList<Routine>();

    public Sequence() {
        super();
        this.currentRoutine = null;
    }

    public void addRoutine(Routine routine) {
        routines.add(routine);
    }

    @Override
    public void reset() {
        for (Routine routine : routines) {
            routine.reset();
        }
    }

    @Override
    public void start() {
        super.start();
        routineQueue.clear();
        routineQueue.addAll(routines);
        currentRoutine = routineQueue.poll();
        currentRoutine.start();
    }


    @Override
    public void act(Npc npc) {

        currentRoutine.act(npc);


        if (currentRoutine.isFailure()) {
            this.state = currentRoutine.getState();
            return;
        }

        while (routineQueue.peek() != null) {
            currentRoutine = routineQueue.poll();
            currentRoutine.start();
            currentRoutine.act(npc);
            if (currentRoutine.isFailure()) {
                this.state = currentRoutine.getState();
                return;
            }
        }

        this.state = currentRoutine.getState();
    }
}