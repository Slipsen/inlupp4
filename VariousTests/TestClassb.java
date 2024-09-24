package VariousTests;
public class TestClassb extends TestClass {
    public TestClassb(){

    }
    public static void  main(String [] args){
        TestClassa a = new TestClassa();
        TestClassa b = new TestClassa();
        System.out.println(a.getI() + " " + b.getI());
    }
}
