package com.projet5001.game.ai;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * this will be the leafController of our tree
 */
public abstract class Task extends Action{

    public enum TaskState {
        Success,
        Failure,
        Running
    }

    protected TaskState taskState;

    protected Task() { }

    public void start() {
        this.taskState = TaskState.Running;
    }

    public abstract void reset();

    public abstract void act(Actor actor);

    protected void succeed() {
        this.taskState = TaskState.Success;
    }

    protected void fail() {
        this.taskState = TaskState.Failure;
    }

    public boolean isSuccess() {
        return taskState.equals(TaskState.Success);
    }

    public boolean isFailure() {
        return taskState.equals(TaskState.Failure);
    }

    public boolean isRunning() {
        return taskState.equals(TaskState.Running);
    }

    public TaskState getTaskState() {
        return taskState;
    }
}

