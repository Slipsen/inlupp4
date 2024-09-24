package org.ioopm.calculator.ast;

public class Quit  extends Command{
    private static Quit theInstance = new Quit();
    private static int occurances = 0;
    private Quit(){
        occurances++;
        assert(occurances==1);
    }    
    public static Quit getInstance(){
        return theInstance;
    }

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
}