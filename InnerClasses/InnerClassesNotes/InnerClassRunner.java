public class InnerClassRunner
{

	public static void main(String [] args)
	{

		SortingHat hat = new SortingHat();
		System.out.println(hat);

		Wizard wizard = new Wizard("Fire");
		System.out.println(wizard);

		Wizard.Wand wand = new Wizard.Wand();
		System.out.println(wand);

	}

	public static class SortingHat
	{

		private String house;
		private String name;

		public SortingHat()
		{
			house = "Gryffindor";
			name = "Harry Potter";
		}

		public String toString()
		{
			return "The house for " + name + " is " + house + ".";
		}

	}

}