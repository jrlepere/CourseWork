public class PuzzleTester {
    public static void main(String[] args){
        Puzzle p0 = new Puzzle("3A6", "36A", "71B");
        Puzzle p1 = new Puzzle("3A6", "36B", "71C");
        Puzzle p2 = new Puzzle("234", "567", "801");
        Puzzle p3 = new Puzzle("321", "654", "300");
        Puzzle p4 = new Puzzle("234", "067", "301");

        System.out.println(p0.replace("A", 4));
        System.out.println("Expected: 346+364=71B");

        System.out.println(p1.replace("C", 4));
        System.out.println("Expected: 3A6+36B=714");
        
        System.out.println(p1.firstLetter());
        System.out.println("Expected: A");
        
        System.out.println(p3.firstLetter().length());
        System.out.println("Expected: 0");

        System.out.println(p1.contains(3));
        System.out.println("Expected: true");
        
        System.out.println(p1.contains(4));
        System.out.println("Expected: false");
        
        System.out.println(p1.isSolved());
        System.out.println("Expected: false");

        System.out.println(p2.isSolved());
        System.out.println("Expected: true");
        
        System.out.println(p3.isSolved());
        System.out.println("Expected: false");
               
        System.out.println(p4.isSolved());
        System.out.println("Expected: false");
    }
}