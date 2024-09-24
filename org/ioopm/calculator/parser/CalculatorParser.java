


package org.ioopm.calculator.parser;

import org.ioopm.calculator.ast.*;
//import org.junit.platform.console.shadow.picocli.CommandLine.Parameters;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.HashMap;
public class CalculatorParser {
    
    private  StreamTokenizer st;
    private int attempts = 0, successfull = 0, complete = 0;  
    private final String[] commands = {"quit","var","clear"};
    Environment vars = createEnvironment();
    //Important characters 
    final static char ADDITION = '+';
    final static  char SUBTRACTION = '-';
    final static  char MULTIPLY = '*';
    final static char DIVISION = '/';
    final static char  NEGATION = '-';

    private Environment createEnvironment(){
        Environment en = new Environment();
        return en; 

    }

    public CalculatorParser(){       
    }


    /**
     * 
     * @param string
     * 
     * @return the double of the test
     * @throws IllegalAssignmentException   If something is attributed to a non variable;
     * @throws IOException if something missfires 
     * @throws CommandException if quit is given
     */
    public double parse(String string) throws IllegalAssignmentException, IOException, CommandException{
       
        SymbolicExpression result = parseExpression(string);
        return result.getValue();

    }
    /**
     * 
     * @param string the string to be parsed
     * @return the SymbolicExpression from parsing the input 
     * @throws IOException  if something bad happens, dunnow what
     * @throws IllegalAssignmentException   if something is assigned to a named constant or other nonvariable
     * @throws CommandException If a command is returned the parser can't handle 
     */
    public SymbolicExpression parseExpression(String string) throws IOException, IllegalAssignmentException, CommandException{
       
        st = new StreamTokenizer(new StringReader(string));
        st.eolIsSignificant(true);
        st.ordinaryChar('-');
        st.nextToken();
        attempts = 0; 
        SymbolicExpression result  = assignment();
        if(isCommand()&& !result.isCommand()){
            throw new IllegalAssignmentException("Not a properly written command");
        }
        else if (result.isCommand()){
            if(result instanceof Clear){
                vars.clear();
            }
            if(result instanceof Quit){
                throw new CommandException(result);
            }
            return result;
        }
        else if(st.ttype!=st.TT_EOF) { throw new RuntimeException("Not a complete assignments");}

        result = result.eval(vars);
        successfull++;
        if(result instanceof Constant){
            complete++;
        }
        return result;

    }
    /**
     * Evaluates a symbolicExpression 
     * 
     * @param e the symbolicExpression to be retuned
     * @return
     * @throws IllegalAssignmentException
     */
    public SymbolicExpression evaluate(SymbolicExpression e) throws IllegalAssignmentException {
        return e.eval(vars);
    }
    
    private boolean isCommand(){
        if(st.TT_WORD!=st.ttype) return false;
            for(String str : commands){
                    if(str.equals(st.sval.toLowerCase()));
            }
        return true;

    }
    
    
    private SymbolicExpression statement() throws IOException,IllegalAssignmentException{
        return assignment();
    }

    // public SymbolicExpression statement() throws IOException{
        
    //     if(isCommand()){
    //         throw new IOException("Is Command");
    //     }
    //     return expression();
    // }

    private SymbolicExpression assignment() throws IOException, IllegalAssignmentException{
        SymbolicExpression result =   expression();
        while(st.ttype=='='){
            st.nextToken();
            result = new Assignment(result, expression());
        }
        return result;
    }
    private SymbolicExpression expression() throws IOException,IllegalAssignmentException{
        SymbolicExpression result = term();
        while (st.ttype == '+' || st.ttype == '-') {
           int operation = st.ttype;
           st.nextToken();
           if (operation == '+') {
               result = new Addition(result, term());
           } else {

               result = new Subtraction( result, term());
           }
        }
        return result;
     }
    /**
     * 
     * @return 
     * @throws IOException
     * @throws IllegalAssignmentException
     */
     private SymbolicExpression term() throws IOException,IllegalAssignmentException{
        SymbolicExpression result  =factor();
        while(st.ttype=='*'|| st.ttype =='/'){
            int operation = st.ttype; 
            st.nextToken();
            if(operation=='*'){
                return new Multiplication(result, factor());
            }
            else if(operation=='/');{
                return new Division(result, factor());
            }
        }
        return result; 
    }
    /*Handles the distribution of '('
      */
    private SymbolicExpression factor() throws IOException, IllegalAssignmentException{
        if(st.ttype=='('){
            
            st.nextToken();

            SymbolicExpression result = expression();

            if(st.ttype!=')') throw new IOException( "Failed to complete paragraph");
            else {
                st.nextToken();
                return  result;
       } } 
        return primary();
    }
    
    private SymbolicExpression primary() throws IOException,IllegalAssignmentException{
        if(st.ttype==st.TT_NUMBER){
            double doub = st.nval;
            st.nextToken();
            return new Constant(doub);
        }
        else return unary();
    }
    private SymbolicExpression  unary() throws IllegalAssignmentException, IOException{
        
        if(st.ttype==st.TT_WORD){
            SymbolicExpression result = readCommand();
            
            if(result!= null){
                return result;
            }
            String value = st.sval;
            st.nextToken();    
            if(value.equalsIgnoreCase("exp")){
                return new Exp(primary());
            }
            else if(value.equalsIgnoreCase("log")){
                return new Log(primary());
            }
            else if(value.equalsIgnoreCase("cos")){
                return new Cos(primary()); 
            }
            else if(value.equalsIgnoreCase("sin")){
                return new Sin(primary()); 
            }
            else{
                return new Variable(value);
            }
        }
        else if(st.ttype=='-'){
            st.nextToken();
            return new Negation(primary());
        }
        throw new IOException("Failed to complete expression last sign parsed : \'"+ (char)st.ttype+"\'");
       
    }
    SymbolicExpression readCommand() throws IOException{
        String value = st.sval;
        if(value.equalsIgnoreCase("quit")){
            return  Quit.getInstance();
        }
        else if(value.equalsIgnoreCase("vars")){
            return Vars.instance(); 
        }
        else if(value.equalsIgnoreCase("clear")){
            return Clear.getInstance();
        }
        else return null;
        
    }
    
    

    public void testEvaluating(double expected, SymbolicExpression e) throws IllegalAssignmentException {
        SymbolicExpression r = e.eval(vars);
        if (r.isConstant() && r.getValue() == expected){
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }

    public void testEvaluating(SymbolicExpression expected, SymbolicExpression e) throws IllegalAssignmentException{
        SymbolicExpression r = e.eval(vars);
        if (r.equals(expected)) {
            System.out.println("Passed: " + e);
        } else {
            System.out.println("Error: expected '" + expected + "' but got '" + e + "'");
        }
    }
    
    


    public static void main(String[] args){
        

        CalculatorParser  cp = new CalculatorParser();
        try{  

            cp.parse("quit");
        }
            catch(Exception e){
            //System.out.println("Hello");
            
        }
    }

}
