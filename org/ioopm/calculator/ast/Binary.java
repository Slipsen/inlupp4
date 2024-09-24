package org.ioopm.calculator.ast;

public abstract class  Binary extends SymbolicExpression {
    private SymbolicExpression lhs = null;
    private SymbolicExpression rhs = null; 
    public Binary(SymbolicExpression lhs, SymbolicExpression rhs, int priority){
        super(priority);
        this.lhs = lhs;
        this.rhs = rhs; 
    }
    public String toString(){
        

        return super.getParantheses(lhs) + " "+ getName() + " " + super.getParantheses(rhs); 
    }
    
    public boolean equals(Object se){
        Binary bin = (Binary) se;
        return lhs.equals(bin.lhs) && rhs.equals(bin.rhs);
    }
    protected SymbolicExpression  getLeft(){
        return lhs; 
    }
    protected SymbolicExpression getRight(){
        return rhs; 
    }
    boolean varExists(Environment vars, SymbolicExpression sy) {
        return lhs.varExists(vars, sy) && lhs.varExists(vars, sy); 
    }
}
