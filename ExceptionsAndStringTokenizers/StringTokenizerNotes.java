import java.util.StringTokenizer;

public class StringTokenizerNotes
{

	public static void main(String [] args)
	{
		m2();
	}

	public static void m1()
	{
	    String str = "Use the force.";
	    StringTokenizer tokenizer = new StringTokenizer(str);
	    while(tokenizer.hasMoreTokens())
	    {
	        String currentToken = tokenizer.nextToken();
	        System.out.println(currentToken);
	    }
	}

	public static void m2()
	{
	    String str = "Use the force.";
	    StringTokenizer tokenizer = new StringTokenizer(str, "e", true);
	    while(tokenizer.hasMoreTokens())
	    {
	        String currentToken = tokenizer.nextToken();
	        System.out.println(currentToken);
	    }
	}

}
