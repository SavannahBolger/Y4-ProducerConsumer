import java.util.LinkedList;

public class BurgerJoint {
	//static int buf;
	static LinkedList<Integer> buf = new LinkedList<>();
	static int p = 0;
	static int c = 0;
	static int n = 5;
	public static class Producer extends Thread{
		int a = 0;
		public void run(){
			System.out.println("Producer Starting");
			
			
                    // producer waits while buffer 
                    // is full 
					while (buf.size() == n) 
					{
						try{
							Thread.sleep(1000);
							}
							catch(InterruptedException e){}
					}
                    System.out.println("Making a Burger: " + a); 
  
                    // to insert the jobs in the list 
                    buf.add(a++); 
  
                    // notifies the consumer thread that 
                    // now it can start consuming 
                   // notify(); 
  
                    // makes the working of program easier 
                    // to  understand 
                    //Thread.sleep(1000); 
                //} 
			System.out.println("Finished Making Burgers");
		}
	}
	public static class Consumer extends Thread{
		int b = 0;
		public void run(){
			System.out.println("Consumer Starting");
			
                    // consumer waits while buffer 
                    // is empty 
                    while (buf.size() == 0) 
					{
						try{
							Thread.sleep(1000);
							}
							catch(InterruptedException e){}
					}  
                    // to retrive the ifrst job in the list 
                    final int val = buf.removeFirst();
  
                    System.out.println("Eating Burger: " + val); 
  
                    // Wake up producer thread 
                    //notify(); 
  
                    // and sleep 
                    //Thread.sleep(1000); 
                	System.out.println("Finished Eating Burgers");

            //} 
		}
	}
	public static void main(final String[] args) {
		final Producer producer = new Producer();
		final Consumer consumer = new Consumer();
		producer.start();
		consumer.start();
	}
}
