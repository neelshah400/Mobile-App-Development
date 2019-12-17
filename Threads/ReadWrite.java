import java.io.BufferedWriter;
import java.io.FileWriter;

public class ReadWrite {

  public static void main(String[] args) {

    Thread t1 = new Thread("Thread One") {
		public void run() {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("numbers.txt"));
				String str = "";
				for(int i = 1; i <= 100; i++)
					str += i + "\n";
				bw.write(str);
				bw.close();
			} catch(Exception e) {

			}
		}
	};

	Thread t2 = new Thread("Thread Two") {
		public void run() {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("letters.txt"));
				bw.write("abcdefghijklmnopqrstuvwxyz");
				bw.close();
			} catch(Exception e) {

			}
		}
	};

	t1.start();
	t2.start();

  }

}