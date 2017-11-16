public class Box
{
	int n;
	public Box(int n) { this.n = n; }
	public int hashCode() { return Math.abs(n) % 10; }
	public boolean equals(Object otherObject) 
	{ 
		return otherObject != null && otherObject.getClass() == Box.class
            && n == ((Box) otherObject).n; 
	}
	public String toString() { return "" + n; }
	
	public static void main(String[] args)
	{
		HashSet set = new HashSet(10);
	    HashSet set2 = set.add(new Box(1));
	    HashSet set3 = set2.add(new Box(3));
	    HashSet set4 = set3.add(new Box(13));
	    
	}
}