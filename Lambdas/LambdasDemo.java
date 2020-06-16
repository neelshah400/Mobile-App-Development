public class LambdasDemo {

    public static void main(String [] args) {

        MultiplierInterface multiplierInterface = (a, b) -> System.out.println(a * b);
        multiplierInterface.multiply(4, 9);
        multiplierInterface.multiply(24, 7);
        System.out.println();

        PrintLoopInterface printLoopInterface = (s, n) -> {
            for (int i = 0; i < n; i++)
                System.out.print(s + "\t");
        };
        printLoopInterface.print("Hello world!", 5);
        System.out.println();
        printLoopInterface.print("Neel", 8);
        System.out.println("\n");

        ListSumInterface listSumInterface = list -> {
            int sum = 0;
            for (int x : list)
                sum += x;
            return sum;
        };
        int [] list = {9, 4, 15, 3, 8, 1, 2, 6};
        int sum = listSumInterface.add(list);
        System.out.println(sum);
        int [] list2 = {-1, 9, -3, -14, 7, 1, 3};
        int sum2 = listSumInterface.add(list2);
        System.out.println(sum2);

    }

    public interface MultiplierInterface {

        abstract void multiply(int a, int b);

    }

    public interface PrintLoopInterface {

        abstract void print(String s, int n);

    }

    public interface ListSumInterface {

        abstract int add(int [] list);

    }
    
}