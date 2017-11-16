import java.util.Scanner;

public class DemolitionDemo
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      while (in.hasNextLine())
      {
         String line = in.nextLine();
         Demolition demo = new Demolition(line);
         System.out.println(demo);
         System.out.println("Cost: " + demo.getCost());
      }
      in.close();
   }
}