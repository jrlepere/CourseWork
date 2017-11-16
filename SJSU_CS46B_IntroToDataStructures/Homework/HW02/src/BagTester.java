
public class BagTester 
{
	public static void main(String[] args)
	{
		Bag bag = new Bag();
		bag.add("carrot");
		bag.add("carrot");
		bag.add("carrot");
		bag.add("carrot");
		bag.add("celery");
		bag.add("carrot");
		bag.add("cookies");
		bag.add("cookies");
		
		System.out.println(bag.count("carrot"));
		System.out.println(bag.count("cereal"));
		System.out.println(bag.count("celery"));
	}
}
