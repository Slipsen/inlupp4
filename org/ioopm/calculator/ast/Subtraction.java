package org.ioopm.calculator.ast;

public class Subtraction extends Binary {
    public Subtraction(SymbolicExpression lhs, SymbolicExpression rhs){
        super(lhs,rhs,200, "-");
    }
    /**
     * @return name which is "-"
     */
    public String getName(){
        return getName();    }
    /**checks if this and other object are both subtract and then checks sub trees
     * @param e the object to compare to
     * @return true if sub trees and name are equal false else
     */
    public boolean equals(Object e){
        if(!(e instanceof Subtraction)){
            return false;
        }
        else{
            return super.equals(e);
        }
    }
    /**Evaluates sub trees and trues to subtract lefthand with righthan dbranch
     * 
     * @param e the environment used to store the vars
     *@returns if both sub trees evaluate to constants subtract them and return difference else return new Subtract holding them
     */
    public SymbolicExpression eval(Environment e ) throws IllegalAssignmentException {
        SymbolicExpression  lhs = super.getLeft().eval(e);
        SymbolicExpression rhs = super.getRight().eval(e);
        if(rhs.isConstant()&&rhs.isConstant()){
            return new Constant(lhs.getValue() - rhs.getValue());
        }
        else{
         return new Subtraction(lhs,rhs);
        }
    }
    
}
