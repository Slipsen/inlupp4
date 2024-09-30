/**
 * Class that uses recursion for parsing strings and assigning each term to a specific
 * mathematical Class. 
 * <p>
 * The class takes a string and works on the assumption that assignments arethe
 * highest order of 
 */

package org.ioopm.calculator;

import java.io.IOException;
import org.ioopm.calculator.parser.CalculatorParser;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.HashMap;

import org.ioopm.calculator.ast.*;




public class Calculator {
    
    public static CalculatorParser parse = new CalculatorParser();
    
    /** 
     * @param se
     */
    public static void print (SymbolicExpression se){
        System.out.println(se.toString());
    }
    
    /** 
     * @param str
     * @throws IOException
     * @throws IllegalAssignmentException
     * @throws CommandException
     */
    public static void printExpression(String str) throws IOException, IllegalAssignmentException, CommandException{
        print(parse.parseExpression(str));
        
    }

    public static void testExpression(String test){
        testExpression(test, test);
    }
    
    
    /** 
     * @param test
     * @param result
     */
    public static void testExpression(String test, String result){
        try{
        System.out.println("parser gave " + parse.parseExpression(test) + " when " + result + " was expected");
            
    } catch(Exception e){System.out.println("error!");}
}
    public static void main(String [] args){
        final CalculatorParser  cp = new CalculatorParser();
        while(true){
            try{
            String input = System.console().readLine();
            System.out.println(cp.parseExpression(input));
            }
            catch (RuntimeException e){
                System.out.println(e.getMessage());
                throw(e);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
            catch (IllegalAssignmentException e){
                System.out.println(e.getMessage());
                assert(false);
            }
            catch(CommandException e){
                if(e.getCommand() instanceof Quit){
                    System.out.println("Program has been quit");
                    break;
                }
            }

        }
    }
    
}
