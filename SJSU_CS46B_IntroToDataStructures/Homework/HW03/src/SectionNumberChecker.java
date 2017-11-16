import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SectionNumberChecker
{
   public static void main(String[] args) throws FileNotFoundException
   {
      System.out.print("File: ");
      Scanner in = new Scanner(System.in);
      String filename = in.nextLine();
      in.close();
      Comparator<String> comp = new SectionNumberComparator();
      File sectionFile = new File(filename);
      Scanner sectionScanner = new Scanner(sectionFile);
      ArrayList<String> sections = new ArrayList<>();
      while (sectionScanner.hasNextLine())
      {
    	  String line = sectionScanner.nextLine();
    	  Scanner lineScanner = new Scanner(line);
    	  boolean sectionFound = false;
    	  while (lineScanner.hasNext() && !sectionFound) {
    		  String str = lineScanner.next();
    		  if (Character.isDigit(str.charAt(0))) {
    			  sections.add(str);
    		  }
    		  sectionFound = true;
    	  }
    	  lineScanner.close();
      }
      sectionScanner.close();
      String previous = sections.get(0);
      for (int i = 1; i < sections.size(); i ++) {
    	  String section = sections.get(i);
    	  if (comp.compare(section, previous) < 1) {
    		  System.out.println(section + " should not come after " + previous);
    	  }     
    	  previous = section;
      }
   }
}
