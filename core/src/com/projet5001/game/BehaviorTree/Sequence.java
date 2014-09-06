package com.projet5001.game.BehaviorTree;

import com.projet5001.game.actors.Npc;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A sequence is like a a while(condition == true)
 */
public class Sequence extends Routine {

    public Sequence() {
        super();
        this.currentRoutine = null;
    }

    private Routine currentRoutine;
    List<Routine> routines = new LinkedList<Routine>();
    Queue<Routine> routineQueue = new LinkedList<Routine>();


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

        while(routineQueue.peek() != null && currentRoutine.isSuccess()){
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