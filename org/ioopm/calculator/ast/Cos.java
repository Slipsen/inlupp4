package org.ioopm.calculator.ast;

public class Cos  extends Unary{

    public Cos(SymbolicExpression value) {
        super(value,25);
        //TODO Auto-generated constructor stub
    }
    @Override
    public String getName() {
        return "cos";
    }
    @Override
    public boolean equals(Object e) {
        if(e instanceof Cos)
        return super.equals(e);
        else return false; 
    }

    public SymbolicExpression eval(Environment e) throws IllegalAssignmentException{
        SymbolicExpression se = get().eval(e);
        if(se.isConstant()){
            return new Constant(Math.cos(se.getValue()));
        }
        else{
            return new Cos(se);
        }
    }
    
}
