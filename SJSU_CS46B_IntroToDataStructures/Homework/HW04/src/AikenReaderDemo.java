import java.io.FileNotFoundException;
import java.util.Scanner;

public class AikenReaderDemo
{
   public static void main(String[] args) throws FileNotFoundException
   {
      String filename = "question14.txt";
      AikenReader reader = new AikenReader(filename);
      for (Question q : reader.readQuestions())
         q.display();
   }
}