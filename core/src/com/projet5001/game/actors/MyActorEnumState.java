package com.projet5001.game.actors;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

/**
 * Created by macmata on 11/08/14.
 */
public enum MyActorEnumState implements State<MyActor> {

    RUN_AWAY() {
        @Override
        public void update(MyActor myActor) {
            if (myActor.isSafe()) {
                myActor.changeState(SLEEP);
            } else {
                //myActor.moveAwayFromEnemy();
            }
        }
    },

    SLEEP() {
        @Override
        public void update(MyActor myActor) {
            if (!myActor.isSafe()) {
                myActor.changeState(RUN_AWAY);
            } else {
                //myActor.snore();
            }
        }
    },

    PATROL() {
        @Override
        public void update(MyActor myActor) {
            if (myActor.isSeeingEnemies()) {
                //myActor.changeState(RUN_AWAY);
            } else {
                //myActor.snore();
            }
        }
    };

    @Override
    public void enter(MyActor myActor) {
    }

    @Override
    public void exit(MyActor myActor) {
    }

    @Override
    public boolean onMessage(Telegram telegram) {
        // We don't use messaging in this example
        return false;
    }
}