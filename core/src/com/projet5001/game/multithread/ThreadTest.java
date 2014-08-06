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
