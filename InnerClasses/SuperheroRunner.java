public class SuperheroRunner
{

	public static void main(String [] args)
	{

		Superhero superhero = new Superhero();
		System.out.println(superhero);
		String name = "Iron Man";
		System.out.println(superhero.isEnemy(name) ? name + " is an enemy." : name + " is not an enemy.");
		superhero.attacked();
		System.out.println(superhero.getName() + " has " + superhero.getHealth() + " health remaining.");

		Thor thor = new Thor(100);
		System.out.println(thor);
		Thor.Hammer hammer = new Thor.Hammer();
		System.out.println(hammer);

	}

	public static class Superhero
	{

		private String name, power, enemy;
		private int health;


		public Superhero()
		{
			name = "Thor";
			power = "Hammer";
			enemy = "Loki";
			health = 100;
		}

		public String getName()
		{
			return name;
		}

		public boolean isEnemy(String name)
		{
			return name.equals(enemy);
		}

		public void attacked()
		{
			health -= (int)(Math.random() * 100) + 1;
			System.out.println("Thor was attacked.");
		}

		public int getHealth()
		{
			return health;
		}

		public String toString()
		{
			return name + " will fights his enemy " + enemy + " with his " + power + ".";
		}

	}

}