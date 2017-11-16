import java.util.Random;

public class RandomSequence implements Sequence
{
   private Random generator = new Random(42);

   public int next()
   {
      return generator.nextInt();
   }
}