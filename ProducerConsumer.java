
public class BurgerBuffer { 
	private final int MaxBuffSize; 
	private char[] store; 
	private int BufferStart; 
	private int BufferEnd;
	private int BufferSize;
	int p = 0;
	int c = 0;
	public BurgerBuffer(int size) { 
		MaxBuffSize = size; 
		BufferEnd = -1; 
		BufferStart = 0; 
		BufferSize = 0; 
		store = new char[MaxBuffSize]; 
	}
	public synchronized void insert(char ch) { 
		try { 
			while (BufferSize == MaxBuffSize) { 
				wait(); 
			} 
			BufferEnd = (BufferEnd + 1) % MaxBuffSize; 
			store[BufferEnd] = ch; 
			BufferSize++; 
			System.out.println("Making a Burger: " + p);

		} catch (InterruptedException e) { 
			Thread.currentThread().interrupt();
		} 
	}
	public synchronized char delete() { 
		try { 
			while (BufferSize == 0) { 
				wait(); 
			} 
			char ch = store[BufferStart]; 
			BufferStart = (BufferStart + 1) % MaxBuffSize; 
			BufferSize--; 
			System.out.println("Eating Burger: " + ch);
			return ch; 
		} catch (InterruptedException e) { 
			Thread.currentThread().interrupt(); 
			return 'i'; 
		} 
	}
}
class Consumer extends Thread { 
	private final BurgerBuffer buffer;
	public Consumer(BurgerBuffer b) { buffer = b; }
	public void run() { 
		System.out.println("Consumer Starting");

		while (!Thread.currentThread().isInterrupted()) { 
			char c = buffer.delete(); 
			System.out.print(c); 
		} 
	}
}
	class Producer extends Thread { 
		private final BurgerBuffer buffer; 
		public Producer(BurgerBuffer b) { buffer = b; }
		public void run() { 
			try { 
				while (!Thread.currentThread().isInterrupted()){ 
					int c = buffer.p; 
					if (c == -1) break; // -1 is eof 
					buffer.insert((char)c); 
				} 
			} catch (InterruptedException e) {} 
		}
	}
	class BoundedBuffer { 
		public static void main(String[] args) { 
			System.out.println("program starting"); 
			BurgerBuffer buffer = new BurgerBuffer(5); // buffer has size 5 
			Producer prod = new Producer(buffer); 
			Consumer cons = new Consumer(buffer); 
			prod.start(); 
			cons.start(); 
			
			System.out.println("End of Program");
		}
	}
		