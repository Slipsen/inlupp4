package org.ioopm.calculator.ast;

public class Division extends Binary {

    /**
     * iniates the Divison and pushes the parameters into a syub tree treee
     * 
     * @param lhs
     * @param rhs
     */
    public Division(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs,100,"/");
    }
    /**
     * @return the name, a string of "/"
     */
    /**
     * checks if if given object is an boolean.
     * then it sends to Equals super class 
     * @param e object to be compared to this object
     * @return true if they and sub trees are the sme 
     */
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
     *@param e environment holding variables
     *@throws IllegalAssignmentExceptionn 
     * 
     */
    public SymbolicExpression eval(Environment e ) throws IllegalAssignmentException{
        SymbolicExpression  lhs = getLeft().eval(e);
        SymbolicExpression rhs = getRight().eval(e);

        if(lhs.isConstant()&&rhs.isConstant()){
            double val = lhs.getValue()/rhs.getValue();
            if(Double.isInfinite(val)) throw new ArithmeticException();
            return new Constant(val);
        }
        else{
            return new Division(lhs,rhs);
        }
    }
}
