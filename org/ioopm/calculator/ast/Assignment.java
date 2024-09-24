package org.ioopm.calculator.ast;

public class Assignment extends Binary {

    public Assignment(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs,400);
    }
    @Override
    public String getName() {
        return"=";
    }

    /**
     * Will resolve equation in left branch and then assign them to variable in right branch
     */
    public SymbolicExpression eval(Environment e) throws IllegalAssignmentException{

        SymbolicExpression  lhs = super.lhs.eval(e);
        if(Constants.isConstant(rhs.getName())){
            throw new IllegalAssignmentException("Tried to define a value to a constant variable");
        }
        if(false == rhs instanceof Variable){
            throw new IllegalAssignmentException("tried to assign a value to another object than a variable");
        }
        e.put((Variable) rhs,lhs);        
        return new Assignment(lhs,rhs);
    }

    private boolean varFound(Environment e, SymbolicExpression se){
        return getLeft().varFound(e, se) && getRight.varFound(e, se);

    }

    
}
