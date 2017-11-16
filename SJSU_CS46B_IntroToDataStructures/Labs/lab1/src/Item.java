public class Item 
{
	private String name;
	private String key;
	private String value;

	public Item(String name, String key, String value) 
	{
		this.name = name;
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString()
	{
		return(getName() + " " + getKey() + ": " + getValue());
	}
	
	public boolean equals(Item anItem)
	{
		if(anItem.getName().equals(name) && anItem.getKey().equals(key) && anItem.getValue().equals(value)) {
			return true;
		}
		return false;
	}
}

