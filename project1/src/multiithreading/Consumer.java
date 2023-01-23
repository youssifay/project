package multiithreading;
import java.util.List;

class Consumer implements Runnable {
    private List<Integer> sharedQueue;
 public Consumer(List<Integer> sharedQueue) {
     this.sharedQueue = sharedQueue;
 }
 
    @Override
 public void run() {
     while (true) {
         try {
             consume();
             Thread.sleep(100);
         } catch (InterruptedException e) {  e.printStackTrace(); }
     }
 }

 private void consume() throws InterruptedException {
   
    synchronized (sharedQueue) {
       //if sharedQuey is empty wait until producer produces.
       while (sharedQueue.size() == 0) {
           System.out.println(Thread.currentThread().getName()+", Queue is empty, consumerThread is waiting for "
                           + "producerThread to produce, sharedQueue's size= 0");
             sharedQueue.wait();
         }

       Thread.sleep((long)(Math.random()*1000));

      
         System.out.println(Thread.currentThread().getName()+", CONSUMED : "+ sharedQueue.remove(0));
         sharedQueue.notify();
     }
 }
 
}
