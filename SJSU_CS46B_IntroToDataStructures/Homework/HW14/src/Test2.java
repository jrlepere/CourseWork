import org.junit.*;
import static org.junit.Assert.*;
import java.util.Iterator;

public class Test2
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
      LispTree tree1 = t("*", t("+", t(3), t(4)), t(5));
      assertEquals(3, tree1.height());
      LispTree tree2 = t("+", t(3), t("*", t(4), t(5)));
      assertEquals(3, tree2.height());
      assertEquals(4, t(1, tree1, NIL).height());
      assertEquals(4, t(1, NIL, tree1).height());
      assertEquals(0, NIL.height());
   }

   @Test public void test2()
   {
      LispTree t1 = t("*", t("+", t(3), t(4)), t(5));
      assertEquals(35, t1.eval());
      LispTree t2 = t("+", t(3), t("*", t(4), t(5)));
      assertEquals(23, t2.eval());
      assertEquals(45, t("+", t(1), t("+", t(2), t("+", t(3), t("+", t(4), t("+", t(5), t("+", t(6), t("+", t(7), t("+", t(8), t(9))))))))).eval());
      assertEquals(1, t("-", t("/", t(7), t(3)), t("%", t(7), t(3))).eval());
   }

   @Test(expected=Exception.class) public void test3()
   {
      t("^", t(1), t(2)).eval();
   }

   @Test(expected=Exception.class) public void test4()
   {
      t("+", NIL, t(2)).eval();
   }

   @Test(expected=Exception.class) public void test5()
   {
      t("+", t("-"), t(2)).eval();
   }
}