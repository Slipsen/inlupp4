package org.ioopm.calculator.ast;

public abstract class  Binary extends SymbolicExpression {
    private SymbolicExpression lhs = null;
    private SymbolicExpression rhs = null;
    private String name = null;  
    public Binary(SymbolicExpression lhs, SymbolicExpression rhs, int priority, String name){
        super(priority);
        this.lhs = lhs;
        this.rhs = rhs; 
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    /**@return name in form of string */
    public String toString(){
        return super.getParantheses(lhs) + " "+ this.getName() + " " + super.getParantheses(rhs); 
    }
    /**
     * return true if both sub trees evaluate to the same value 
     */
    public boolean equals(Object se){
        Binary bin = (Binary) se;
        return lhs.equals(bin.lhs) && rhs.equals(bin.rhs);
    }
    public SymbolicExpression  getLeft(){
        return lhs; 
    }
    public SymbolicExpression getRight(){
        return rhs; 
    }
    public String getOverlay(){
        return getParantheses(lhs, lhs.getOverlay()) + " " + this.getClass().getSimpleName() + " " + getParantheses(rhs,rhs.getOverlay());
    }
}
