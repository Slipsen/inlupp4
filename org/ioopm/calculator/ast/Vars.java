package org.ioopm.calculator.ast;

public class Vars extends Command {
    private  static final Vars theInstance = new Vars();
    private static int occurances = 0; 
    private Vars(){
        occurances++;
        assert(occurances==1);
    };
    public static Vars instance(){
        return theInstance;
    }
    public String getName(){
        return "vars";
    }
    public String toString(){
        return "vars";
    }
    /**
     * @param e object holding existing variables
     * @return the result of the equation
     */
    public boolean equals(Object e){
        if (e instanceof Vars){
            return true;
        }
        return false;
    }
    static SymbolicExpression getInstance(){
        return theInstance; 

    }
}
