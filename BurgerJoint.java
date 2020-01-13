public class BurgerJoint {
	static int buf;
	static int p = 0;
	static int c = 0;
	static int n = 5;
	public static class Producer extends Thread{
		int a = 0;
		public void run(){
			System.out.println("Producer Starting");
			while(p < n){
				try{
				Thread.sleep(1000);
				}
				catch(InterruptedException e){}
				while(p != c){
					System.out.println("Short order cook spinning");
				}
				a = (int)Math.random() * 100;
				System.out.println("Making a Burger: " + p);
				buf = a;
				p = p + 1;
			}
			System.out.println("Finished Making Burgers");
		}
	}
	public static class Consumer extends Thread{
		int b = 0;
		public void run(){
			System.out.println("Consumer Starting");
			while(c < n){
				try{
					Thread.sleep(2000);
					}
					catch(InterruptedException e){}
				while(p <= c){
					System.out.println("Starving waiting on a burger!");
				}
				System.out.println("Eating Burger: " + c);
				b = buf;
				c = c + 1;
				
			}
			System.out.println("Finished Eating Burgers");
		}
	}
	/*public static void main(final String[] args) {
		final Producer producer = new Producer();
		final Consumer consumer = new Consumer();
		producer.start();
		consumer.start();
	}*/
}
