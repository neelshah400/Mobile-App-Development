public class ExceptionsPractice
{
    
    public static void main(String [] args)
    {
        m1();
        System.out.println();
        m2();
        System.out.println();
        m3();
        System.out.println();
        m4();
    }
    
    public static void m1()
    {
        try 
        {
            int x = 1;
            int y = 0;
            int z = x/y;
            System.out.println(z);
        } 
        catch(ArithmeticException e) 
        {
            System.out.println("Divide by 0 error");
            e.printStackTrace();
        } 
        finally {
            System.out.println("Try again!");
        }
    }
    
    public static void m2()
    {
        try 
        {
            int [] arr = {1, 2, 3};
            System.out.println(arr[3]);
        } 
        catch(ArrayIndexOutOfBoundsException e) 
        {
            System.out.println("Out of bounds error");
            e.printStackTrace();
        } 
        finally {
            System.out.println("Try again!");
        }
    }
    
    public static void m3()
    {
        try 
        {
            String str = null;
            System.out.println(str.charAt(0));
        } 
        catch(NullPointerException e) 
        {
            System.out.println("Null pointer error");
            e.printStackTrace();
        } 
        finally {
            System.out.println("Try again!");
        }
    }
    
    public static void m4()
    {
        try 
        {
            String a = "a";
            String b = "b";
            int c = Integer.parseInt(a) + Integer.parseInt(b);
            System.out.println(c);
        } 
        catch(NumberFormatException e) 
        {
            System.out.println("Not a number error");
            e.printStackTrace();
        } 
        finally {
            System.out.println("Try again!");
        }
    }
    
}