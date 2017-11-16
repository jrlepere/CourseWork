import java.awt.print.Book;

import javax.swing.JFileChooser;

public class AddressBookDemo 
{
	public static void main(String[] args) 
	{
	   JFileChooser chooser = new JFileChooser();
	   int result = chooser.showOpenDialog(null);
	   if (result == JFileChooser.APPROVE_OPTION) {
		   String filename = chooser.getSelectedFile().getPath();
		   AddressBook book = new ArrayListAddressBook();
		   book.load(filename);
		   System.out.println(book.get("Khuri", "Phone"));
		   book.put("Jake Lepere", "Email", "jlepere2@yahoo.com");
		   book.save();
		   System.out.println(book.getFirst().toString());	 
		   }
	}
}
