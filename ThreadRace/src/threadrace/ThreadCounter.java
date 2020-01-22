/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadrace;

/**
 *
 * @author trbol
 */
public class ThreadCounter extends Thread{
    int currNum;
    Thread waitThread;
    
    public ThreadCounter(String name) {
        this.setName(name);     
        waitThread = null;
        currNum = 1;
    }
    public ThreadCounter(String name,Thread waitThread){
        this.setName(name);     
        this.waitThread = waitThread;
        currNum = 1;
    }
    
    public int getCurrNum(){
        return currNum;
    }
    
    @Override
    public void run(){
        if(waitThread!=null){
            try{
                waitThread.join();
            }catch(InterruptedException e){}
        }
        
        while(currNum < 1000){
            currNum++;
            System.gc();
        }
    }
}
