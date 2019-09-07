public class Thor
{

	private String power;
	private String enemy;
	private int health;

	public Thor(int health)
	{
		power = "Hammer";
		enemy = "Loki";
		this.health = health;
	}

	public String toString()
	{
		return "Thor has a " + power + " and hates " + enemy + ".";
	}

	public static class Hammer
	{

		private int weight;

		public Hammer()
		{
			weight = 1000;
		}

		public String toString()
		{
			return "The hammer weighs " + weight + " kg.";
		}

	}

}