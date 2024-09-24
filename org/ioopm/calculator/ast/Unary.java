package org.ioopm.calculator.ast;

/**
 * class meant for holding classes that form one-branch trees. Holds another Symbolic expression
 */

public abstract class Unary  extends SymbolicExpression{
    private SymbolicExpression value = null;
    public Unary(SymbolicExpression value ,int priority){
        this.value = value; 
    }

    /**
     * @return string of itself and its child objects with parentheses around the child objects string if its priority is lower 
     */
    public String toString(){
        String str = this.getName() + " ";
        if(this.getPriority()<value.getPriority()){
            return str+= " (" + this.get() + ") ";
        }else{
        return str + this.get();
    
        }
    
    }
    /**
     * compares
     *  calls equals on the value it holds to check its sub tree
     * @return true if both sub-trees are the same
     */
    public boolean equals(Object e){
        return this.equals((Unary) e);
    }
    /**
     * 
     * @return the value stored in this unary
     */
    public SymbolicExpression get(){
        return value;
    }

    
    boolean varExists(Environment vars, SymbolicExpression sy) {
        return value.varExists(vars, sy); 
    }
   

}
