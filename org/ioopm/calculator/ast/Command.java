package org.ioopm.calculator.ast;

public abstract class Command extends SymbolicExpression {
   private String name; 
        protected Command(String name){
            super(0);
            this.name = name;  

    }
    public SymbolicExpression eval(Environment e) throws RuntimeException{
        throw new RuntimeException("eval can't be called on command");
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
        return name;
    }
    public String getName(){
        return name;
    }    
    
}
