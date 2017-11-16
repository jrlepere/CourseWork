import java.util.ArrayList;

/**
 * This program represents a bag with multiple items
 * @author JLepere2
 */
public class Bag 
{
	private ArrayList<Item> bag;
	
	public Bag()
	{
		bag = new ArrayList<>();
	}
	
	public void add(String itemName)
	{
		Item item = new Item(itemName);
		boolean duplicate = false;
		for (int i = 0; i < bag.size(); i ++) {
			if (bag.get(i).itemName.equals(itemName)) {
				duplicate = true;
				bag.get(i).increaseCount();
			}
		}
		if (!duplicate) {
			bag.add(item);
		}
	}
	
	public int count(String itemName)
	{
		for (int i = 0; i < bag.size(); i ++) {
			if (bag.get(i).itemName.equals(itemName)) {
				return bag.get(i).quantity;
			}
		}
		return 0;
	}
	
	class Item
	{	
		String itemName;
		int quantity;
		
		private Item(String itemName)
		{
			this.itemName = itemName;
			quantity = 1;
		}
		
		private void increaseCount()
		{
			quantity++;
		}
	}
}
