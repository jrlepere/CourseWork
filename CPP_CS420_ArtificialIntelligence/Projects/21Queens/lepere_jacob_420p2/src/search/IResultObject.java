package search;

import java.util.Set;

/**
 * An object to be returned from executing a search algorithm.
 * @author JLepere2
 * @date 02/01/2018
 */
public interface IResultObject {
	
	/**
	 * Gets the fields available for this results object.
	 * @return the fields available
	 */
	public Set<String> getFields();
	
	/**
	 * Gets the object associated with the field key
	 * @param field the field key
	 * @return the object
	 */
	public Object getObject(String field);
	
}
