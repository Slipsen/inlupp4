package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression {
    static Command command; 
    protected Command(Command command){

    }

    public SymbolicExpression eval(Environment e) throws RuntimeException{
        throw new RuntimeException("eval can't be called on command");
    }
    
    boolean varExists(Environment vars, SymbolicExpression sy) {
        throw new RuntimeException("varExists can't be called on command");
    }
    
     /** 
    * 
     * @return true because object is a command
     */
    public boolean isCommand(){
        return true; 
    }
    /**
     * @return the name of the children of the Command class in the form of a string
     */
    public String toString(){
        return getName();
    }
    static SymbolicExpression getInstance(){
        return command; 

    }
    
}
