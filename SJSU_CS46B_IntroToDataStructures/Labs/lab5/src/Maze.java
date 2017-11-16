import java.util.ArrayList;

/**
   A maze has its walls marked by * characters and corridors 
   by spaces. 
*/
public class Maze
{
   private char[][] cells;

   /**
      Constructs a maze from a string describing its contents.
      @param contents a string consisting of *, spaces, and newlines terminating
      the rows.
   */
   public Maze(String contents)
   {
      int rows = 0;
      int columns = 0;
      int currentLength = 0;
      for (int i = 0; i < contents.length(); i++)
      {
         if (contents.charAt(i) == '\n')
         {
            columns = Math.max(columns, currentLength);
            currentLength = 0;
            rows++;
         }
         else
         {
            currentLength++;
         }
      }
      cells = new char[rows][columns];
      int row = 0;
      int column = 0;
      for (int i = 0; i < contents.length(); i++)
      {
         if (contents.charAt(i) == '\n')
         {
            row++;
            column = 0;
         }
         else
         {
            cells[row][column] = contents.charAt(i);
            column++;
         }
      }
   }

   /**
      Checks whether a position is an exit.
      @param row the row of the position
      @param column the column of the position
      @return true if the position is an exit
   */
   public boolean isExit(int row, int column)
   {
      return (row == 0 || row == cells.length - 1 
         || column == 0 || column == cells[0].length - 1)
         && cells[row][column] != '*';
   }

   /**
      Checks whether a position is within the maze and not a wall.
      @param row the row of the position
      @param column the column of the position
      @return true if the position is valid
   */
   public boolean isValid(int row, int column)
   {
      return 0 <= row && row < cells.length 
         && 0 <= column && column < cells[0].length
         && cells[row][column] == ' ';
   }
   
   /**
    * Adds or remove a robot in a position.
    * @param row the row of the position
    * @param column the column of the position
    * @param d +1 to add, -1 to remove
    */
   public void updateRobot(int row, int column, int d)
   {
      if (0 <= row && row < cells.length 
         && 0 <= column && column < cells[0].length)
      {
            if (cells[row][column] == ' ' && d == 1)
               cells[row][column] = 'R';
            else if (cells[row][column] == 'R' && d == -1)
               cells[row][column] = ' ';
            else
               cells[row][column] += d;   
      }        
   }
   
   public void print()
   {
      for (int i = 0; i < cells.length; i++)
      {
         for (int j = 0; j < cells[0].length; j++)
            System.out.print(cells[i][j]);
         System.out.println();
      }
      System.out.println();
   }
}