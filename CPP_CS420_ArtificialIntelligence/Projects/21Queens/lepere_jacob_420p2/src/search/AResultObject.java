package search;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class AResultObject implements IResultObject {

	public AResultObject(TreeMap<String, Object> objectMap) {
		map = objectMap;
	}
	
	public Set<String> getFields() {
		return map.keySet();
	}
	
	public Object getObject(String field) {
		if (!map.containsKey(field)) {
			throw new IllegalArgumentException("This result object does not contain: " + field + ".");
		}
		return map.get(field);
	}
	
	private Map<String, Object> map;
	
}
