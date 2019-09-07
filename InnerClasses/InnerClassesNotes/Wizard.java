public class Wizard
{

	private String magicType;

	public Wizard(String magicType)
	{
		this.magicType = magicType;
	}

	public String toString()
	{
		return "The magic type is " + magicType + ".";
	}

	public static class Wand
	{

		private String core;

		public Wand()
		{
			core = "Phoenix Feather";
		}

		public String toString()
		{
			return "The core type is " + core + ".";
		}

	}

}