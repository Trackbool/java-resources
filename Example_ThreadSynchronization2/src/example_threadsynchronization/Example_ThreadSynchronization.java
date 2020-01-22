package example_threadsynchronization;

public class Example_ThreadSynchronization 
{
    public static void main(String[] args) 
    {
        Counter c = new Counter(100);
        
        Thread tinc = new Thread(() -> {
           for (int i = 0; i < 100; i++)
           {
                c.increment();
               try
               {
                   Thread.sleep(100);
               } catch(InterruptedException e) {}
           }
           
           System.out.println("Finishing inc. Final value= " + c.getValue());
        });
        
        Thread tdec = new Thread(() -> {
           for (int i = 0; i < 100; i++)
           {
                c.decrement();
               try
               {
                   Thread.sleep(100);
               } catch(InterruptedException e) {}
           }
           
           System.out.println("Finishing dec. Final vaue= " + c.getValue());
        });
        
        tinc.start();
        tdec.start();
    }
}
