package com.projet5001.game.BehaviorTree;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by macmata on 01/09/14.
 */
public abstract class Routine {

    public enum RoutineState {
        Success,
        Failure,
        Running
    }

    protected RoutineState RoutineState;

    protected Actor RoutineActor;

    public Actor getRoutineActor() {
        return RoutineActor;
    }

    public void setRoutineActor(Actor routineActor) {
        RoutineActor = routineActor;
    }

    public void start() {
        this.RoutineState = RoutineState.Running;
    }

    public abstract void act(float delta);

    public abstract void reset();

    protected void succeed() {
        this.RoutineState = RoutineState.Success;
    }

    protected void fail() {
        this.RoutineState = RoutineState.Failure;
    }

    public boolean isSuccess() {
        return RoutineState.equals(RoutineState.Success);
    }

    public boolean isFailure() {
        return RoutineState.equals(RoutineState.Failure);
    }

    public boolean isRunning() {
        return RoutineState.equals(RoutineState.Running);
    }

    public RoutineState getState() {
        return RoutineState;
    }
}
