public class TreeTest {
    public static void main(String[] args){
        Tree t1 = new Tree("Tom");
        Tree s11 = new Tree("Jerry");
        Tree s12 = new Tree("Kevin");
        Tree s111 = new Tree("Kate");
        s11.addSubtree(s111);
        t1.addSubtree(s11);
        t1.addSubtree(s12);
        System.out.println(t1.interiorNodes());
        System.out.println("Expected: 2\n");

        Tree t2 = new Tree("Tom");
        Tree s21 = new Tree("Jerry");
        Tree s22 = new Tree("Kevin");
        Tree s23 = new Tree("John");
        Tree s211 = new Tree("Allen");
        Tree s212 = new Tree("Lisa");
        Tree s221 = new Tree("Kate");
        s21.addSubtree(s211);
        s21.addSubtree(s212);
        s22.addSubtree(s221);
        t2.addSubtree(s21);
        t2.addSubtree(s22);
        t2.addSubtree(s23);
        System.out.println(t2.interiorNodes());
        System.out.println("Expected: 3\n");
        
        Tree t3 = new Tree("Tom");
        Tree s31 = new Tree("Jerry");
        Tree s32 = new Tree("Kevin");
        Tree s33 = new Tree("John");
        Tree s321 = new Tree("Allen");
        Tree s331 = new Tree("Lisa");
        Tree s332 = new Tree("Kate");
        Tree s3211 = new Tree("Will");
        s321.addSubtree(s3211);
        s32.addSubtree(s321);
        s33.addSubtree(s331);
        s33.addSubtree(s332);
        t3.addSubtree(s31);
        t3.addSubtree(s32);
        t3.addSubtree(s33);
        System.out.println(t3.interiorNodes());
        System.out.println("Expected: 4\n");
    }
}