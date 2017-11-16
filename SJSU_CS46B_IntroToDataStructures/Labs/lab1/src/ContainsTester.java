public class ContainsTester
{
   public static void main(String[] args)
   {
      AddressBook book = new ArrayListAddressBook();
      book.put("Fred", "wife", "Wilma");
      book.put("Dino", "food", "meat");
      System.out.println(book.contains(new Item("Dino", "food", "meat")));
      System.out.println("Expected: true");
      System.out.println(book.contains(new Item("Dino", "wife", "Wilma")));
      System.out.println("Expected: false");
   }
}
