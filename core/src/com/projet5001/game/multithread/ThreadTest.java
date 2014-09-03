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

package com.projet5001.game.multithread;

import com.projet5001.game.Projet5001;

/**
 * Created by macmata on 05/08/14.
 */
public class ThreadTest extends Thread {
    int accumulateur;
    public ThreadTest(){
        this.accumulateur = 0;
    }

    public void run(){

        while (accumulateur < 1000 ){
            accumulateur++;
            System.out.println(accumulateur);

            try{

                System.out.println("added to queue");
            }catch (Exception e){
                System.out.println("queue full");
            }
        }

    }

}
