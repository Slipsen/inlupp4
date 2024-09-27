import org.ioopm.calculator.ast.SymbolicExpression;
import org.ioopm.calculator.parser.CalculatorParser;

public class Test1 {
    public static void main(String[] args){
        String lista[] = {"1+1","3+3","x+x","3+y","3=y","y","clear","quit", "quit 1"};
        CalculatorParser parser = new CalculatorParser();
        for(String str : lista){
            try{
            SymbolicExpression symb = parser.testParse(str);
            System.out.println("the string " + str + " parsed into " + symb );
            }
            catch(Exception e ){
                System.out.println("the string " + str + " gave the error " + e);
            }
       }
    }
}