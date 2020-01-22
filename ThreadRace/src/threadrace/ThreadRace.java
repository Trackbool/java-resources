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
public class ThreadRace {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ThreadCounter a = new ThreadCounter("A");
        a.start();
        ThreadCounter b = new ThreadCounter("B",a);
        b.start();
        ThreadCounter c = new ThreadCounter("C",b);
        c.start();
        do{
            System.out.println("Thread "+a.getName()+": "+a.getCurrNum()+
                    " Thread "+b.getName()+": "+b.getCurrNum()+
                    " Thread "+c.getName()+": "+c.getCurrNum());
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
            }
        }while(a.isAlive() || b.isAlive() || c.isAlive());
    }
    
}
