package org.ioopm.calculator.ast;

public class Quit  extends Command{
    private static Quit theInstance = new Quit();
    private static int occurances = 0;
    /**
     * iniates object, makes sure it can only be iniated once
     */
    private Quit(){
        occurances++;
        assert(occurances==1);
    }    
    
    /**
     * @return the name of the object "quit"
     */
    public String getName(){
        return "quit";
    }
    
    /**
     * checks if other other object a Quit
     * @return returns true if both are of Quit class 
     */
    public boolean equals(Object e){
        if(e instanceof Quit){
            return true;
        }
        else{
            return false; 
        }
    }
    /**
     * 
     * @return returns the Quit object
     */
    public static SymbolicExpression getInstance(){
        return theInstance; 

    }
}