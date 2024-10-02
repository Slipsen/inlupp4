package org.lib;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;

public class Testa{
    private CalculatorParser cp = new CalculatorParser();
    public Testa(){
    }
    void newString(String str){
    
    }
    /**
     * Tests if a a symbolic expression evals to what is expected 
     * 
     * @param expected  The expression to be evaluated
     * @param  e        The expression we expect to be
     * @return         prints what we got and what we expected of errort
     */
    public void testEvaluating(SymbolicExpression expected, SymbolicExpression e) {
        SymbolicExpression r = null;
        boolean result = false; 
       try{ 
        r = e.eval(null);; 
        if (r.equals(expected)) {
            result =  true;
        } else {
            result = false; 
        }
            } catch(Exception er){
                result = false; 
            }
        assertTrue(result,"Error: expected '" + expected + "' but got '" + e + "'");
    }

    void testParse(String read, String result, CalculatorParser parser){
        SymbolicExpression se = new Variable("faulty reading");; 
        boolean threwException = false;
        try{
            se = parser.parseExpression(result);
        }
        catch (Exception e){
            threwException = true; 

        }
        assertFalse(threwException,"Threw an exception when parsing " + read);
        System.out.println(se +" " + result);
        assertTrue(se.toString().equals(result), "read "+ se + " as " + se + " instead of " + result );
    }
    @Test
    public void testReading(){
        CalculatorParser readParser = new CalculatorParser();
        testParse("1 + 3 + 4 + 5", "13.0", readParser);
        testParse("1 + 3 + 4 + 5 = x", "13.0", readParser);
        testParse("y", "13.0", readParser);
        testParse("y + y", "26.0", readParser);
        testParse ("y+y+y = y ", "39.0",readParser);
        testParse("y", "39.0", readParser);
        testParse("y y", "3.0", readParser);
        System.out.println("hello");
    }

    public void shitTest(){
        assertTrue(false);
    }

    public void parseText(String read, String compare, CalculatorParser cp){
        try{
            SymbolicExpression se = cp.parseExpression(read);
            assertTrue(se.toString().equalsIgnoreCase(compare), "Parsed string was supposed to equal "+ compare + " but we got "+ se.toString() + "and " + read + " was read");
        }catch (CommandException e){
            if(e.getCommand().getName().equals(compare)){
                assertTrue(true);
            }
            else {
                assertTrue(false);
            }
        }
        catch (Exception e){
            assertTrue(false,"Text " + read + " could not be read error was " + e);
        }
    }
    @Test
    public void testParser1(){
        try{
        File object = new File("test.txt");
        Scanner reader = new Scanner(object);
        while(reader.hasNextLine()){
            String toTest = reader.nextLine().trim();
            String toCompare = reader.nextLine().trim();
            parseText(toTest, toCompare, cp);
        }
        reader.close();

        }
        
        catch(FileNotFoundException e){
            System.out.println("Something went wrong");
        }

    }
    @Test
    public void astTests(){
        final int  num1 = 5;
         final int num2 = -4;
         final int num3 = 3;
        String variable1 = "x1";
        String variable2 = "x2";
        String namedName1 = "pi";
        String namedName2 = "ip";
        final double namedNum1 = 3;
        final double namedNum2 = 4;
        Constant c1 = new Constant(num1);
        Constant c2 = new Constant(num2);
        Constant c3 = new Constant(num3);
        Variable var1 = new Variable(variable1);
        Variable var2 = new Variable(variable2);
        NamedConstant nc1 = new NamedConstant(namedName1, namedNum1);
        NamedConstant nc2 = new NamedConstant(namedName2, namedNum2);
        Addition ad1  =new Addition(c1, c2);
        Addition ad2  =new Addition(c2, c3);
        Subtraction sub1 = new Subtraction(c1, c2);
        Subtraction sub2 = new Subtraction(c2, c3);
        Multiplication mul1 = new Multiplication(c1,c2);
        Multiplication mul2 = new Multiplication(c2,c3);
        Division div1 = new Division(c1,c2);;
        Division div2 = new Division(c2,c3);
        Negation neg1 = new Negation(c1);
        Negation neg2 = new Negation(var1);
        Cos cos1 = new Cos(c1);
        Cos cos2 = new Cos(c2);
        Exp exp1 = new Exp(c1);
        Exp exp2 = new Exp(c2);
        Log log1 = new Log(c1);
        Log log2 = new Log(c2);
        Sin sin1 = new Sin(c1);
        Sin sin2  = new Sin(c1);
        Quit q1 = Quit.getInstance();
        Quit q2 = Quit.getInstance();
        Vars v1  =Vars.getInstance();
        Vars v2 = Vars.getInstance();
        Clear clear1 = Clear.getInstance();
        Clear clear2 = Clear.getInstance();
        

        //Asserts getVar();
        assertTrue(c1.getValue()==num1);
        


        assertTrue(c3.equals(c3));
        assertTrue(c2.equals(c2));
        assertTrue(c1.equals(c1));
        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));
        

    }






    public static void main(String[] args){
        //someMathTests();
    }
}

    // SymbolicExpression se = new Multiplication(new Addition(new Constant(5), new Constant(2)), new Constant(2));
    // System.out.println(se);
    // System.out.println(se.eval(vars).getValue());
    // se = new Assignment(new Multiplication(new Addition(new Constant(5), new Constant(1)), new Cos(new Addition(new Constant(3.1415),new Constant(3.1415)))),new Variable("y"));
    // System.out.println(se);
    // System.out.println(se.eval(vars));
    // System.err.println(new Multiplication(new Variable("y"), new Variable("y")).eval(vars));
    // vars.put(new Variable("y"),new Constant(1));
    // System.out.println(vars.get(new Variable("y")));
    // HashMap<String,SymbolicExpression> map = new HashMap<>();
    // map.put("y", new Constant(3));
    // Variable var1 = new Variable("x");
    // Variable var2 = new Variable( "x");
    // System.out.println(var2.hashCode() + " " + var2 + " hashcode ofvar 2 " + var1 + " " + var1.hashCode() );
    // System.out.println(map.get("y"));}