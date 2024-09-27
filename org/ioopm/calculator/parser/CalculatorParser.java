


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

    private Environment vars;
    
    //Important characters 
    private final static char ADDITION = '+';
    private final static char SUBTRACTION = '-';
    private final static char MULTIPLY = '*';
    private final static char DIVISION = '/';
    private final static char NEGATION = '-';
    private final static String COS = "cos";
    private final static String SIN = "sin";
    private final static String EXP = "exp";
    private final static String LOG = "log";
    private final static char  ASSIGNMENT = '=';
    private final static String QUIT = "quit";
    private final static String VARS = "vars";
    private final static String CLEAR = "clear"; 


    String writingError = "termwas incorrectly written";
    private Environment createEnvironment(){
        Environment en = new Environment();
        en.putReserved("ans");
        en.put(new Variable("ans"),new Constant(0));
        return en; 

    }

    public CalculatorParser(){ 
        vars = createEnvironment();
        System.out.println(vars.keySet());      
    }
    /**
     * 
     * @param string
     * 
     * @return the result of the aq
     * @throws IllegalAssignmentException   If something is attributed to a non variable;
     * @throws IOException if something missfires 
     * @throws CommandException if quit is given
     */
    @Deprecated
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
        st.ordinaryChar(ADDITION);
        st.ordinaryChar(DIVISION);
        st.ordinaryChar(ASSIGNMENT);

        st.nextToken();
        SymbolicExpression result  = statement();
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
    

    
    /**
     *Makes sure that the term has been properly turned into a symbolicexpression and sees so that it 
     * it will be interpreted the right way
     * 
     * @return the term translated into a tree of statements
     * @throws IOException
     * @throws IllegalAssignmentException
     */
    private SymbolicExpression statement() throws IOException,IllegalAssignmentException,  CommandException{
        try{if(st.ttype==StreamTokenizer.TT_WORD) command();
        }catch(CommandException e){
 
            if(StreamTokenizer.TT_EOF != st.ttype){
                throw new IllegalAssignmentException("Incorrectly written function");
            }
            else if(e.getCommand() instanceof Clear){
                vars.clear();
               throw new CommandException(Clear.getInstance());
            }
             else if(e.getCommand() instanceof Vars){
                vars.printVars();
                throw new CommandException(Vars.instance());
            }
            else if (e.getCommand() instanceof Quit){
                throw new CommandException(Quit.getInstance());
             
         }
        }
        SymbolicExpression result = assignment();
        if(st.ttype!=StreamTokenizer.TT_EOF) { throw new IllegalAssignmentException("Incorrectly written function");}
            else{
            vars.put(new Variable("ans"),result);
            successfull++;
            if(result instanceof Constant) complete++;
            return result;
         }
 
    }
  
  /**
   * Function that for assigning valuables to a variable if demanded 
   * @return
   * @throws IOException
   * @throws IllegalAssignmentException
   */
    private SymbolicExpression assignment() throws IOException, IllegalAssignmentException{

        SymbolicExpression result =   expression();
        while(st.ttype=='='){
            st.nextToken();
            result = new Assignment(result, expression());
        }
        return result;
    }

    /**
     * Looks for additional and subtraction signs in term
     * @return
     * @throws IOException
     * @throws IllegalAssignmentException
     */
    private SymbolicExpression expression() throws IOException,IllegalAssignmentException{

        SymbolicExpression result = term();
        while (st.ttype == '+' || st.ttype == '-') {
           int operation = st.ttype;
           st.nextToken();
           if (operation == '+') {
               result = new Addition(result, expression());
           } else {

               result = new Subtraction( result, expression());
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
        SymbolicExpression result  =unary();
        while(st.ttype=='*'|| st.ttype =='/'){
            int operation = st.ttype; 

            st.nextToken();

            if(operation==MULTIPLY){
                return new Multiplication(result, term());
            }
            else if(operation==DIVISION);{
                return new Division(result, term());
            }
        }
        return result; 
    }

    private SymbolicExpression unary() throws IllegalAssignmentException, IOException{
        if (st.ttype==NEGATION){
            st.nextToken();
            return new Negation(unary());
        }
        else if(st.ttype==StreamTokenizer.TT_WORD){      
            String value = st.sval;
            if(value.equalsIgnoreCase(EXP)){
                st.nextToken();    
                return new Exp(unary());
            }
            else if(value.equalsIgnoreCase(LOG)){
                st.nextToken();    

                return new Log(unary());
                
            }
            else if(value.equalsIgnoreCase(COS)){
                st.nextToken();    

                return new Cos(unary()); 
            }
            else if(value.equalsIgnoreCase(SIN)){
                st.nextToken();    

               return new Sin(unary()); 
             }
             //failing to tie the string to a function we have to push the stringreader back a step and wait till down to command and vars


             
         }
        return factor();
    }
    /**handles the use of brackets and will call Assignment because it might fall within brackets
     * Will call an error if the string tokenizer does not hold a ')' at return
     * If no bracket is found continues to command
    */
    private SymbolicExpression factor() throws IOException, IllegalAssignmentException{
        SymbolicExpression result;

        if(st.ttype=='('){
            
            st.nextToken();

            result = assignment();

            if(st.ttype!=')') throw new IOException( "Failed to complete paragraph last read sign was "+ result);
            else {
                st.nextToken();
                return  result;
       } 
    }
 
     return primary();
    }
   
   
    /**Checks to see if it is a comand
    *@return a command if it is a command, else an number or a variable
    */

    private void  command() throws IOException, IllegalAssignmentException, CommandException{
    String value = st.sval;
    if(value.equalsIgnoreCase(QUIT)){
        st.nextToken();
        throw new CommandException(Quit.getInstance());
    }
    else if(value.equalsIgnoreCase(VARS)){
        st.nextToken();
        throw new CommandException(Vars.instance()); 
    }
        else if(value.equalsIgnoreCase(CLEAR)){
        st.nextToken();
        throw new CommandException(Clear.getInstance());
    };
    }
    private SymbolicExpression primary() throws IOException,IllegalAssignmentException{            
        if(st.ttype==StreamTokenizer.TT_NUMBER){
            double doub = st.nval;
            st.nextToken();
            return new Constant(doub);
        }
        
        else if(st.ttype == StreamTokenizer.TT_WORD){
            try {command();} 
            catch(CommandException e) {
            throw new IllegalAssignmentException("Tried to use the command "+ e.getCommand()+ " as a term"); 
        }
            String variable = st.sval;
            st.nextToken();
            return new Variable(variable);
        }
        else{ 
            throw new IllegalAssignmentException("Could not complete terms \n last read was " + st.toString());}
    }
    

    public SymbolicExpression testParse(String str)  throws Exception{
        SymbolicExpression symb;
        try{
             symb = this.parseExpression(str);
        }
        catch(Exception e){
            throw new Exception(e + " happened");
        }
        return symb;
    }    
    // public SymbolicExpression testParseEvaled(String str) throws Exception{ 
    //     try{
    //         SymbolicExpression symb  
    //         return symb.eval(vars);
    //     }catch(Exception e){
    //         throw new Exception("Error, Error. Error, Error");
    //     }
    // }



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
