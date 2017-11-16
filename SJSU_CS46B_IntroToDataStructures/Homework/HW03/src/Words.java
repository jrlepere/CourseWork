import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Words
{
   public static void main(String[] args) throws FileNotFoundException
   {
      System.out.print("File name: ");
      String fileName = new Scanner(System.in).nextLine();
      File wordFile = new File(fileName);
      Scanner in = new Scanner(wordFile);
      in.useDelimiter("");
      int words = 0;
      int sentences = 0;
      while (in.hasNext()) {
         String next = in.next(); // A string of length 1
         if (next.equals(" ") || next.equals("\n")) {
        	 words++;
         }
         if (next.equals("!") || next.equals("?")) {
        	 sentences++;
        	 if (!in.hasNext()) {
        		 words++;
        	 }
         }
         if (next.equals("-")) {
        	 if (in.hasNext()) {
        		 String next2 = in.next();
        		 if (next2.equals("-")) {
        			 words ++;
        		 }
        	 }
         }
         if (next.equals(".")) {
        	 if (in.hasNext()) {
        		 String words2 = in.next();
        		 if (words2.equals(".")) {
        			 if (in.hasNext()) {
        				 String words3 = in.next();
        				 if (words3.equals(".")) {
        					 words ++;
        				 }
        			 }
        		 } else {
        			 sentences ++;
        			 words++;
        		 }
        	 } else {
        		 sentences ++;
        		 words++;
        	 }
         }
      }
      in.close();
      System.out.println("Words: " + words);
      System.out.println("Sentences: " + sentences);
   }
}
