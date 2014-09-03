/*
 * Copyright [2014] [Alexandre Leblanc]
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
 */

package com.projet5001.game.BehaviorTree;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by macmata on 01/09/14.
 */
public abstract class Routine {

    public static enum RoutineState {
        Success,
        Failure,
        Running
    }

    protected RoutineState state;

    protected Actor RoutineActor;

    public Actor getRoutineActor() {
        return RoutineActor;
    }

    public void setRoutineActor(Actor routineActor) {
        RoutineActor = routineActor;
    }

    public void start() {
        this.state = state.Running;
    }

    public abstract void act(Actor actor);

    public abstract void reset();

    protected void succeed() {
        this.state = state.Success;
    }

    protected void fail() {
        this.state = state.Failure;
    }

    public boolean isSuccess() {
        return state.equals(state.Success);
    }

    public boolean isFailure() {
        return state.equals(state.Failure);
    }

    public boolean isRunning() {
        return state.equals(state.Running);
    }

    public RoutineState getState() {
        return state;
    }
}
