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
package com.projet5001.game.BehaviorTree.CompositeLeaft;

import com.projet5001.game.BehaviorTree.Leaf.FireMove;
import com.projet5001.game.BehaviorTree.Sequence;
import com.projet5001.game.BehaviorTree.Leaf.TagetImmobile;

/**
 * Created by macmata on 07/09/14.
 */
public class MoveNoPath extends Sequence{
    public void start(){
        this.addRoutine(new TagetImmobile());
        this.addRoutine(new FireMove());
        super.start();
    }
}
