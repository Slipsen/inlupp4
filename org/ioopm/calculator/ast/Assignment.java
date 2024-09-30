package org.ioopm.calculator.ast;

public class Assignment extends Binary {
    /**binary tree node for storving both tes of ann assignment
     * 
     * @param lhs to be hold in elft branch
     * @param rhs to be held in right branch
     */
    public Assignment(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs, 400,"=");
    }

    @Override


    /**
     * Will resolve equation in left branch and then assign them to variable in
     * right branch
     */
    public SymbolicExpression eval(Environment e) throws IllegalAssignmentException {

        SymbolicExpression lhs = super.getLeft().eval(e);
        if (Constants.isConstant(getRight().getName())) {
            throw new IllegalAssignmentException("Tried to define a value to a constant variable");
        }
        if (false == getRight() instanceof Variable) {
            throw new IllegalAssignmentException("tried to assign a value to another object than a variable");
        }
        e.put((Variable) getRight(), lhs);
        return lhs;
    }
    

}
