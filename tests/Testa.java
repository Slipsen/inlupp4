package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import junit.framework.AssertionFailedError;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.parser.*;


public class Testa{
    /**
 * IMportant permament values;
 */
 Class<RuntimeException> EXPECTEDGETNAME = RuntimeException.class;
 final int  NUMBER1 = 5;
 final int NUMBER2 = -4;
 final int NUMBER3 = 3;
 final int NUMBER4 = 20;
 final int NUMBER5 = 10000000;
 Constant c1 = new Constant(NUMBER1);
 Constant c2 = new Constant(NUMBER2);
 Constant c3 = new Constant(NUMBER3);
 Constant c4 = con(NUMBER4);
 Constant c5 = con(NUMBER5);
 String namedName1 = "pi";
 String namedName2 = "ip";
String varName;
String varName2;
String varName3;
Environment env = new Environment();

private CalculatorParser cp;
public void inateParser(){
    cp = new CalculatorParser();
}
public void inateEnvironment(){
    env = new Environment();
}
 static  Constant con(double num){
    return new Constant(num);
}
static  Variable var(String name){
    return new Variable(name);
}

static NamedConstant named(String name, double num){
    return new NamedConstant(name, num);
}

//Create Double objects
static Assignment assign(SymbolicExpression lhs, SymbolicExpression rhs){
    return new Assignment(lhs, rhs);
}

static Addition add(SymbolicExpression lhs, SymbolicExpression rhs){
    return new Addition(lhs, rhs);
}
static Subtraction sub(SymbolicExpression lhs ,SymbolicExpression rhs){
    return new Subtraction( lhs,  rhs);
}

static Division div(SymbolicExpression lhs ,SymbolicExpression rhs){
    return new Division(lhs, rhs);    
}
static Multiplication mul(SymbolicExpression lhs ,SymbolicExpression rhs){
    return new Multiplication(lhs,rhs);
}
///create unaries
static Cos cos(SymbolicExpression branch){
    return new Cos(branch);
}
static Sin  sin(SymbolicExpression branch){
    return new Sin(branch);
}
static Exp  exp(SymbolicExpression branch){
    return new Exp(branch);
}
static Log  log(SymbolicExpression branch){
    return new Log(branch);
}

static Negation neg(SymbolicExpression branch){
    return new Negation(branch);
}

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
        inateParser();
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
    public void testGetValue(boolean throwError, SymbolicExpression sy){
        testGetValue(throwError, sy,-100);
    }
    public void testGetValue( boolean throwError,SymbolicExpression sy, double val){
        try{
            double returnedValue = sy.getValue();
            if(throwError==true){
                assertFalse(throwError,"Threw an error when expected not to");
            }
            else{
                assertTrue(sy.getValue() == val,  "returned the value " + returnedValue + "when "+ val +  " was expected"); 
            }
        }catch(Exception e){
            assertTrue(throwError, "Threw error when expected not to");
        }
    }
    public void testIsConstant(boolean isConstant,SymbolicExpression sy){
        if(isConstant == false){assertFalse(sy.isConstant(),"isconstant returned true when expected not return false");
        }
        else{
            assertTrue(sy.isConstant(), "isConstant returned false when expected to return true" );
        }
    }
    public void testGetName(boolean throwException, SymbolicExpression Sy){
        testGetName(throwException, Sy, "");
    }
    public void testGetName(boolean throwException, SymbolicExpression sy, String name){
        String acquiredName; 
        try{
            acquiredName = sy.getName();
            if(throwException == true){
                assertFalse(throwException, "getName threw no error for " + sy.toString() + " when none was expected");
            }else{
                assertTrue(acquiredName.equals(name),"Expected name  " + name + " did not match given name "+ acquiredName); 
            }

        }catch( Exception e){
            assertTrue(throwException,  "getName threw an exception for " +sy.toString() + "when none was expected");
        }
    }
    public void testIsCommand(boolean isCommand, SymbolicExpression sy){
        if(isCommand == true){
            assertTrue(sy.isCommand(), "got false for isCommand when true was expected");
        }
        else{    assertTrue(!sy.isCommand(), "got true for isCommand when false was expected");}
    }
    public void testPriority(SymbolicExpression sy, int expectedPriority){
       int acquiredPriority = sy.getPriority();
        assertTrue(acquiredPriority==expectedPriority,"expected a priority of " + expectedPriority + "but got " +acquiredPriority);
    }
    

    public void commandTests(Command command, String name){
        testGetName(true,command);
        testPriority(command, 0);
        testGetValue(true,command);
        testIsConstant(false,command);
        testIsCommand(true,command);
    }
    public  void conTests(Constant con, double number){
        testGetName(true,con);
        testPriority(con, 0);
        testGetValue(false,con,number);
        testIsConstant(true,con);
        testIsCommand(false,con);
    }
    public void binaryTests(Binary doub, String name, int priority){
        testGetName(false, doub,name);
        testPriority(doub, priority);
        testGetValue(true,doub);
        testIsConstant(false,doub);
        testIsCommand(false,doub);
    }
    public void unaryTests(Unary one, String name, int priority){
        testGetName(false, one,name);
        testPriority(one, priority);
        testGetValue(true,one);
        testIsConstant(false,one);
        testIsCommand(false,one);
    
    }
    public void namedConstantTests(NamedConstant nmcon, String name, double value, int priority){
        testGetName(false, nmcon,name);
        testPriority(nmcon, priority);
        testGetValue(false,nmcon,value);
        testIsConstant(true,nmcon);
        testIsCommand(false,nmcon);
       
    }
    public void inateConstants(){
        c1 = con(NUMBER1);
        c2 = con(NUMBER2);
        c3 = con(NUMBER3);
        c4 = con(NUMBER4);
        c5 = con(NUMBER5);
    }

    @Test
    public void testConstants(){
        inateConstants();
        conTests(c1,NUMBER1);
        conTests(c2,NUMBER2);
    }
    public void varTests(Variable var, String name, int priority){
        testGetName(false, var,name);
        testPriority(var, priority);
        testGetValue(true,var);
        testIsConstant(false,var);
        testIsCommand(false,var);

    }
    
    @Test 
    public void testVariable(){
        int priority = 0; 
        inateConstants();
        String name = "xy";
        varTests(var(name), name,priority);
        name = "xzzjjjjjjuuuuuuuuraaaaaaa_____y";
        varTests(var(name), name,priority);
    }
    /** tests binaries */
    @Test
    public void testBinary(){
        inateConstants();
        String name = "+";
        int priority = 200;
        binaryTests(add(c1,c2),name,priority);
        name = "-";
        binaryTests(sub(c1,c3),name,priority);
        name = "/";
        priority = 100;
        binaryTests(div(c1,c2),name,priority);
         name = "*";
        binaryTests(mul(c1,c3),name,priority);
    }
/**Tests unary objects */
    @Test
    public void testUnary(){   
        inateConstants();
        int priority = 25;
        unaryTests(cos(c1),"cos",priority);
        unaryTests(sin(c1),"sin",priority);
        unaryTests(exp(c1),"exp",priority);
        unaryTests(log(c1),"log",priority);
        unaryTests(neg(c1),"-",priority);   
    }
    @Test
    public void testNamedConstants(){
        namedConstantTests(named("pi",3.14),"pi",3.14,0);

    }
    /**Test commands */
    @Test
    public void testCommands(){
        commandTests(Quit.getInstance(),"quit");
        commandTests(Vars.getInstance(),"vars");
        commandTests(Clear.getInstance(),"clear");
    }


    public void  treeTests(SymbolicExpression symbol1, SymbolicExpression symbol2){
        treeTests(symbol1, symbol2, null, null);
    }

    public void  treeTests(SymbolicExpression symbol1, SymbolicExpression symbol2,  Exception shouldThrow){
        treeTests(symbol1, symbol2, shouldThrow, null);}
    
    public void  treeTests(SymbolicExpression symbol1, SymbolicExpression symbol2,   Error shouldError){
        treeTests(symbol1, symbol2, null, shouldError);}    
    public void  treeTests(SymbolicExpression symbol1, SymbolicExpression symbol2,  Exception shouldThrow, Error shouldError){    
                try {
                    symbol1 = symbol1.eval(env);
                    boolean sameString = symbol1.toString().equals(symbol2.toString());
                    boolean sameSymbol = symbol1.equals(symbol2);
                    assertTrue(sameString,"String expected to be same but string 1 was "+ symbol1.toString() + " and string 2 was "+ symbol2.toString());
                    assertTrue(sameSymbol, "method failed to find symbolicvalues to be equal symbol1 = " + symbol1 + " and symbol 2 " + symbol2);
                    assertTrue(shouldThrow==null,"Threw no exception when it should have thrown" + shouldThrow);
                    assertTrue(shouldError==null,"Threw no error when it should have thrown" + shouldError);

                } catch (Exception e) {
                    if(null==shouldThrow)assertTrue(false, "threw  error " + e +"  when no exception should have been thrown");
                    else assertTrue((shouldThrow.getClass().equals(e.getClass())), "threw " + e + " when " + shouldThrow + "was expected");
                }
                catch (Error e){
                    if(null==shouldError) assertTrue(false, "threw error " + e +  "when no error should have been trown");
                    else assertTrue(shouldError.getClass().equals(e.getClass()), "threw error " + e.toString() + " but " + shouldError.toString() );
                }

    }
    @Test
    public void testEqualsandEvals(){
        NullPointerException nullExcept = new NullPointerException(); 
        IllegalAssignmentException illegalExcept = new IllegalAssignmentException( null);
        AssertionFailedError assertError = new AssertionFailedError();
        ArithmeticException   arithmExcept = new ArithmeticException();
        inateEnvironment();
        SymbolicExpression sy1 = add(con(2),con(3));
        SymbolicExpression sy2 = con(5);
        treeTests(sy1,sy2);
        sy1 = assign(null,null);
        sy2 = assign (null, null) ;
        treeTests(sy1,sy2,  nullExcept);
        sy1 = assign(con(5),con(5));
        treeTests(sy1,null, illegalExcept);   
        sy1 = assign(con(5),var("y"));
        sy2=con(5);
        treeTests(sy1,sy2);
        treeTests(sy2,sy2); //test sy
        sy2=assign(con(0),named("pi",3));
        treeTests(sy2,null,illegalExcept);
        sy1 = assign(con(0),var("pi"));
        treeTests(sy1,null,illegalExcept);
        sy1 = neg(neg(neg(neg(neg(neg(neg(con(3))))))));
        sy2 = con(-3);
        treeTests(sy1,sy2);
        String x = "x";
        String y = "y";
        sy1 = add(assign(con(4),var(x)),assign(con(4),var(y))); // 4=y + 4=x eq 8
        sy2 = con(8);
        treeTests(sy1,sy2);
        sy1 = var(y);
        sy2 = con(4);
        treeTests(sy1,sy2);//y = 4
        sy1 = mul(var(x),var(y));
        sy2 = con(16);
        treeTests(sy1,sy2);//Y*x  =16
        sy1 = div(con(10),con(0));
        treeTests(sy1,sy1,arithmExcept);
    }

    public String pathError(SymbolicExpression oldSym, SymbolicExpression newSym){
    String response = "";
    String objName = oldSym.getObjectName() +"\n";
    if(oldSym instanceof Binary){
        Binary oldBin = (Binary)  oldSym;
        Binary newBin = (Binary) newSym;
        response = pathError(oldBin.getLeft(), newBin.getLeft());
        if(response!=null) return objName + response;
        response = pathError(oldBin.getRight(),newBin.getRight());
        if(response!=null) return  objName + response;    }
    else if(oldSym instanceof Unary){
        Unary oldUn = (Unary)  oldSym;
        Unary newUn = (Unary)  newSym;
        response = pathError(oldUn.get(),newUn.get());
        if(response!=null) return objName + response;
    }
    else if(!oldSym.equals(newSym)) return "failed at:"+oldSym.getObjectName() + " " + newSym.getObjectName();
    return null;
    
}
//@Test
public void testPatherror(){
    SymbolicExpression sym1 = assign(add(con(1),con(2)),sub(var("y"),named("x",2)));
    String str = pathError(sym1,sym1);
    assertTrue(str==null,"found error but should not " + str);
    SymbolicExpression sym2 = assign(add(con(1),con(2)),sub(var("y"),var("xyyy")));
    str = pathError(sym1,sym2);
    assertTrue(str!=null,"found no error but should for " +sym1 + " and "+ sym2);
    if(str!=null){assertTrue(false,str);}
}     


public void integrationTests(SymbolicExpression se){
        try{
            SymbolicExpression newSe = cp.testParse(se.toString());
            if(!se.equals(se)){ 
                assertTrue(false, "the object "+ se + " did not even compare to itself\n"+pathError(se,se));
        }
        if(!se.equals(newSe)){
            String path = pathError(se,newSe);
         
            assertTrue(true,"failed to make " +se + " and " + newSe + " as equals \n class structure is:\n" + se.getOverlay() + "\n"+ newSe.getOverlay());
        }
            assertTrue(se.toString().trim().equals(newSe.toString().trim()));
            }
            catch(CommandException e){
                assert(e.getCommand().equals(se));
            }
            catch(Exception e){
            assertFalse(true,"Expected no exception but got " + e +"\n");
        }
    }
    

    
    //TODO do stuff
    @Test
    public void testIntegration(){
        inateEnvironment();
        inateParser();
        ArrayList<SymbolicExpression> symbols = new ArrayList();
        SymbolicExpression symb1;
        SymbolicExpression symb2;
        SymbolicExpression symb3;
        integrationTests(con(10));
        integrationTests(add(con(10),con(29)));
        integrationTests(div(con(3),var("x")));
        integrationTests(cos(sin(con(2))));
        integrationTests(assign(cos(con(2)),var("doggiev")));
        integrationTests(neg(assign(mul(div(con(3),var("x")),cos(sin(con(2)))),assign(cos(con(2)),var("doggiev")))));
        symb1 = new Constant(20);
        symbols.add(symb1);
        symb2 = new Constant(-20);
        symbols.add(symb2);
        symb1 = assign(add(symb1,assign(symb2,var("tjog"))),var("y"));
        symbols.add(symb2);
        symb3 = con(10000);
        symbols.add(mul(symb2,symb3));
        symbols.add(cos(sin(exp(log(div(symb1,symb3))))));
        symbols.add(named("pi",3.14));
        symbols.add(add(named("tjugo",90),neg(cos(con(20)))));
        symbols.add(neg(neg(neg(neg(symb1)))));
        symb1 = con(3);
        symb2 = con(90);
        symb3 = con(-2525);
        symb1 = add(mul(symb1,symb3),sub(symb3,div(symb2,symb1)));
        symbols.add(symb1);
        symb3 = div(symb1,symb3);
        symbols.add(symb3);
        symbols.add(div(con(20),con(100)));
        symb1 = Quit.getInstance();
        symbols.add(symb1);
        symb1 = Vars.getInstance();
        symbols.add(symb1);
        symb1 = Clear.getInstance();
        symbols.add(symb1);
        symb2 = assign(named("pi",20),con(0));
        for(SymbolicExpression sy : symbols){
            integrationTests(sy);
        }
    }

    @Test
    public void astTests(){
        final double namedNum1 = 3;
        final double namedNum2 = 4;
        Addition ad1  =new Addition(c1, c2);
        Addition ad2  =new Addition(c2, c3);
        Subtraction sub1 = new Subtraction(c1, c2);
        Subtraction sub2 = new Subtraction(c2, c3);
        Multiplication mul1 = new Multiplication(c1,c2);
        Multiplication mul2 = new Multiplication(c2,c3);
        Division div1 = new Division(c1,c2);;
        Division div2 = new Division(c2,c3);
        Negation neg1 = new Negation(c1);
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

          
        // testGetValue()
        // testIsConstant()
        // test GetName()
        // testPriority()
        // getValue()
        // isConstant()
        // getName()
        // isCommand()
        // priority()
       
        //assertDoesNotThrow(EXPECTEDGETNAME,() ->c2.getName());
        
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