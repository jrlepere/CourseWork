
public enum Size {
	SMALL(10), MEDIUM(20), LARGE(30);
	
	private double value;
	
	private Size(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
}
