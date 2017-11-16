import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;

public class Test1
{
   public static EmptyTree NIL = new EmptyTree();

   public static  LispTree t(Object data, LispTree left, LispTree right)
   {
      return new NonEmptyTree(data, left, right);
   }

   public static LispTree t(Object data) 
   {
      return new NonEmptyTree(data, NIL, NIL);
   }

   @Test public void test1()
   {
      // Figure 17.6 of the textbook
      LispTree tree1 = t("*", t("+", t(3), t(4)), t(5));
      assertEquals("*", tree1.data());
      assertEquals("+", tree1.left().data());
      assertEquals(NIL, tree1.right().left());
   }

   @Test public void test2()
   {
      LispTree tree1 = t("*", t("+", t(3), t(4)), t(5));
      assertEquals("t(*, t(+, t(3), t(4)), t(5))", tree1.toString());
      LispTree tree2 = t("+", t(3), t("*", t(4), t(5)));
      assertEquals("t(+, t(3), t(*, t(4), t(5)))", tree2.toString());
      assertEquals("t(1, NIL, t(2))", t(1, NIL, t(2)).toString());
      assertEquals("t(1, t(2), NIL)", t(1, t(2), NIL).toString());
      assertEquals("NIL", NIL.toString());
   }
}