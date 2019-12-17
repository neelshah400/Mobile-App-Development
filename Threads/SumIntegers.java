import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SumIntegers {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		Thread t1 = new Thread("Thread One") {
			public void run() {
				try {
					BufferedReader br = new BufferedReader(new FileReader("integers.txt"));
					String line = null;
					while ((line = br.readLine()) != null) {
						list.add(Integer.parseInt(line));
					}
					br.close();
				} catch (Exception e) {

				}
			}
		};

		t1.start();
		try {
			t1.join();
		} catch (Exception e) {

		}
		
		int sum = 0;
		for(int num : list)
			sum += num;
		System.out.println(sum);

	}

}