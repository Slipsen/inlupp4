package org.ioopm.calculator.ast;

public class Division extends Binary {

    public Division(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs,100);
    }
    @Override
    public String getName() {
        return "/";
    }
    public boolean equals(Object e){
        if(!(e instanceof Division)){
            return false;
        }
        else{
            return super.equals(e);
        }
    }

    /**
     * divides left hand term with right hand term
     */
    public SymbolicExpression eval(Environment e ) throws IllegalAssignmentException{
        SymbolicExpression  lhs = getLeft().eval(e);
        SymbolicExpression rhs = getRight().eval(e);

        if(lhs.isConstant()&&rhs.isConstant()){
            return new Constant(lhs.getValue()/rhs.getValue());
        }
        else{
            return new Division(lhs,rhs);
        }
    }
}
