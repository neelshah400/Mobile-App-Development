import java.util.ArrayList;

public class SuperheroRunner
{

	public static void main(String [] args)
	{

		Superhero superhero = new Superhero();
		System.out.println(superhero);
		String name = "Iron Man";
		System.out.println(superhero.isEnemy(name) ? name + " is an enemy." : name + " is not an enemy.");
		superhero.attacked();
		System.out.println(superhero.getName() + " has " + superhero.getHealth() + " health remaining.\n");

		Thor thor = new Thor(100);
		System.out.println(thor);
		Thor.Hammer hammer = new Thor.Hammer();
		System.out.println(hammer + "\n");

		ArrayList<Superhero> list = new ArrayList<Superhero>();
		for(int i = 0; i < 5; i++)
			list.add(new Superhero());
		for(Superhero hero : list)
		{
			hero.attacked();
		}
		System.out.println(list);
		for(int i = 0; i < list.size() - 1; i++)
		{
			int minIndex = i;
			for(int j = i; j < list.size(); j++)
			{
				if(list.get(j).compareTo(list.get(minIndex)) < 0)
					minIndex = j;
			}
			Superhero temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		System.out.println(list);

	}

	public static class Superhero implements Comparable<Superhero>
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
			return name + " has " + health + " health.";
		}

		public int compareTo(Superhero obj)
		{
			if(health > obj.getHealth())
				return 1;
			else if(health < obj.getHealth())
				return -1;
			else
				return getName().compareTo(obj.getName());
		}

	}

}