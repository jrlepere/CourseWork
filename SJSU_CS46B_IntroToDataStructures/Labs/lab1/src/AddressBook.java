import java.util.Iterator;

/**
   An address book for looking up information about people.
*/
public interface AddressBook {

   /** 
       Load the dictionary from a source.
       @param sourceName the name of the source
   */
   void load(String sourceName);

   /** 
       Look up an item.
       @param name the name of the person to look up
       @param key the name of the item to look up 
       @return the item value or null if not found
   */
   String get(String name, String key);

   /** 
       Add or change an item
       @param name the name of the person to look up
       @param key the name of the item to look up 
       @param value the value for the item
       @return the old item value or null if not found
   */
   String put(String name, String key, String value);

   /** 
       Remove an item.
       @param name the name of the person to look up
       @param key the name of the item to look up 
       @return the old item value or null if not found
   */
   String remove(String name, String key);
   
   /**
      Save the address book.
   */
   
   void save();
   
   Item getFirst();
   
   boolean contains(Item anItem);

}
