package tests;

import org.ioopm.calculator.Calculator;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;
import org.junit.Ignore;

import junit.framework.AssertionFailedError;
import static tests.Testa.*;
public class Tests {
    public static void main(String[] args){
        CalculatorParser cp = new CalculatorParser();
        System.out.println(Quit.getInstance().getClass().getSimpleName());
        System.out.println(new Cos(new Addition(new Constant(1),new Constant(2))));
        System.out.println(new Multiplication(new Addition(new Constant(1),new Constant(2)),new Constant(10)));
        SymbolicExpression symb = neg(assign(mul(div(con(3),var("x")),cos(sin(con(2)))),assign(cos(con(2)),var("doggiev"))));
        try{
            SymbolicExpression expression2 = cp.testParse(symb.toString());
            System.out.println(expression2.equals(symb));
        }
        catch(Exception e){
            System.out.println("wat?");
        }
      }
}
