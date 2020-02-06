import java.util.concurrent.atomic.AtomicInteger;

public class Multithreading {
  
  static int i = 0;
  static AtomicInteger ai;
  static int si = 0;
  
  public static void main(String[] args) {
    
	ai = new AtomicInteger();
	
	for (int j = 1; j <= 500; j++)
		new MyThread().start();
	try {
		Thread.sleep(3000);
	} catch (Exception e) {
		System.out.println("error :(");
	}

	System.out.println("i: " + i);
	System.out.println("ai: " + ai.get());
	System.out.println("si: " + si);

  }

  public static class MyThread extends Thread {
	
	public void run() {
		
		try {
			Thread.sleep(2);
		} catch (Exception e) {
			System.out.println("error :(");
		}

		i++;
		ai.getAndAdd(1);
		add(1);

	}

  }

  public static synchronized void add(int k) {

	si += k;

  }

}