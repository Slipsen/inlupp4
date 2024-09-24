package org.ioopm.calculator.ast;

public class Subtraction extends Binary {
    public Subtraction(SymbolicExpression lhs, SymbolicExpression rhs){
        super(lhs,rhs,200);
    }
    public String getName(){
        return "-";
    }
    public boolean equals(Object e){
        if(!(e instanceof Subtraction)){
            return false;
        }
        else{
            return super.equals(e);
        }
    }
    public SymbolicExpression eval(Environment e ) throws IllegalAssignmentException {
        SymbolicExpression  lhs = super.lhs.eval(e);
        SymbolicExpression rhs = super.rhs.eval(e);
        if(rhs.isConstant()&&rhs.isConstant()){
            return new Constant(lhs.getValue() - rhs.getValue());
        }
        else{
         return new Subtraction(lhs,rhs);
        }
    }
    
}
