package org.ioopm.calculator.ast;


/**
 * Negation class usedd for storing equations that has been negated
 */
public class Negation extends Unary{


    public Negation(SymbolicExpression value) {
        super(value,25,"-");
    }
    /**
     * @return its name in form of string, a minus sign
     */

    
    /** 
     * checks if same class and then compare both child objects
    *@param object to be compared 
    *@return true if equal, false if not
    */
    public boolean equals(Object e){
        if(e instanceof Negation){
            return super.equals(e);
        }
        else return false; 
    }

    /**
     * evaluates its child values using variables if it has to.
     * If its child evaluates to a constant it returns a negation of a constant
     * else it returns a new Negation holding the new  Symbolic Expression
     * @param e Environment that holds all existing variables
     * @throws IllegalAssignmentException if an attempt for an illegal assignment is made
     *  @return negation of Constant or a new Negative class
     */
    public SymbolicExpression eval(Environment e) throws IllegalAssignmentException{
        SymbolicExpression se = get().eval(e);
        if(se.isConstant()){
            return new Constant(0-se.getValue());
        }
        else{
            return new Negation(se);
        }
    }
}
