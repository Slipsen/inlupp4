package org.ioopm.calculator.ast;

public class Addition extends Binary{
    int priority = 200;
    public Addition(SymbolicExpression lhs, SymbolicExpression rhs){
        super(lhs, rhs, 200,"+");
    }    
    public boolean equals(Object e){
        if(!(e instanceof Addition)){
            return false;
        }
        else{
            return super.equals(e);
        }
    }
    
    /** 
     * @param e
     * @return SymbolicExpression
     * @throws IllegalAssignmentException
     */
    public SymbolicExpression eval(Environment e) throws IllegalAssignmentException{
        SymbolicExpression  lhs = getLeft().eval(e);
        SymbolicExpression rhs = getRight().eval(e);
        if(lhs.isConstant()&&rhs.isConstant()){
            return new Constant(rhs.getValue() + lhs.getValue());
        }
        else{
            return new Addition(lhs,rhs);
        }
    }
    


    
    public static void main(String[] args) {
        
    }
    
   
    
}
