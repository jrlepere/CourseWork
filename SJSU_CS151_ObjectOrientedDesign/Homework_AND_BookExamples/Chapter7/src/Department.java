
public enum Department {

	SALES("A1"), TEST("B1"), ENGINEERING("B2");
	
	private Department(String buildingLocation) {
		this.location = buildingLocation;
	}
	
	private String location;
	
	public String getValue() {
		return location;
	}
	
}
