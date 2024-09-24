import org.ioopm.calculator.ast.*;

public class Test {
   public static void main(String[] args){
    SymbolicExpression m =  new Multiplication(new Addition(new Constant(5), new Variable("x")), new Constant(2));
    System.out.println("(5 + x) *  2 ==> " + m);
   }
    
}
