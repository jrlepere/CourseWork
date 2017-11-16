import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Salaries
{
   public static void main(String[] args)
   {
      String filename = args[0];
      File salariesFile = new File(filename);
      try (Scanner in = new Scanner(salariesFile)){
    	  boolean firstLine = true;
    	  while (in.hasNextLine()) {
    		  if (firstLine) {
    			  String first = in.nextLine();
    			  firstLine = false;
    		  } else {
    			  String line = in.nextLine();
    			  String[] words = line.split("\\s+"); //splits the line into an array
	    		  
	    		  //Gets the index of the first dollar amount
	    		  boolean indexFound = false;
	    		  int i = 0;
	    		  for (i = 0; i < words.length && !indexFound; i ++) {
	    			  if (words[i].startsWith("$")) {
	    				  indexFound = true;
	    			  }
	    		  }
	    		  i --;
	    		  
	    		  //Gets the name
	    		  String name = words[i - 1];
	    		  if (words[i - 2].length() == 2) {
	    			  name = words[i - 3] + " " + words[i - 2] + " " + name;
	    		  } else {
	    			  name = words[i - 2] + " " + name;
	    			  if (words[i- 3].length() == 2) {
	    				  name = words[i - 3] + " " + name;
	    			  }
	    		  }
	    		  
	    		  // Gets the total dollar amount for each line
	    		  int total = 0;
	    		  for (int j = i; j < words.length; j ++) {
	    			  if (words[j].startsWith("$")) {
	    				  String valueAsString = words[j].trim().replace("$","").replace(",", "");
	    				  int value = Integer.parseInt(valueAsString);
	    				  total += value;
	    			  }
	    		  }
	    		  
	    		  System.out.printf("%-2s $%,6d%n", name, total);
	    	  }
    	  }
      } catch (FileNotFoundException e) {
    	  System.out.println("File not found: " + filename);
      }
   }
}