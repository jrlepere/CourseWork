public class MazeSolver
{
   public static void main(String[] args)
   {
      Maze maze = new Maze(
          "*****************************\n"
         + "** ***                      *\n"
         + "** *** * ********************\n"
         + "** *** *         *          *\n"
         + "** *** * *******   **********\n"
         + "**     * ********************\n"
         + "****** *************** ******\n"
         + "******         ******* ******\n"
         + "*      ******* ******* ******\n"
         + "* **** ******* **           *\n"
         + "*    * ******* ******* ******\n"
         + "* **** ******* ***     ******\n"
         + "************** **************\n");

      Robot fred = new Robot(maze, 5, 8);
      fred.setDebug(true);
      fred.escape2();
      System.out.println(fred.getVisited());
   }
}