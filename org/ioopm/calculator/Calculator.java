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

import org.ioopm.calculator.ast.*;
import java.util.Scanner;



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
        Scanner sc = new Scanner(System.in);
        while(true){
            
        if(System.console()!=null){
            try{
            /**If we read logs from a txtfile then System.console will be empty */

                System.out.println("Write input");
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

                    String result = "Program has been quit\n" + cp.getAttempts() + " attempts made\n " + cp.getSuccess() + " successfull\n" + cp.getComplete() + " complete";
                    System.out.println(result);
                    break;
                }
            }
        }
        else{   
            try{String input = sc.nextLine();
            SymbolicExpression result = cp.parseExpression(input);
            System.out.println(result);
            }
        catch(CommandException e){
                System.out.println(e.getCommand()); 
                if(e.getCommand() instanceof Quit){
                    break;
                }
            }
            catch(Exception e ){
                System.out.println(e.getMessage());
                break;
            } 
        }
    }
}
}
