public class ThreadsDemo {

	public static void main(String[] args) {

		Thread t1 = new Thread("Thread One") {
			public void run() {
				for (int i = 1; i <= 10; i++)
					System.out.println(getName() + " " + i);
			}
		};

		Thread t2 = new Thread("Thread Two") {
			public void run() {
				for (int i = 1; i <= 10; i++)
					System.out.println(getName() + " " + i);
			}
		};

		t1.start();
		t2.start();

		try {
			t1.join();
			System.out.println("t1 Joined");
			t2.join();
			System.out.println("t2 Joined");
		} catch (Exception e) {
			System.out.println(" :( ");
		}

		for (int i = 1; i <= 10; i++)
			System.out.println("Main Thread " + i);

	}

}