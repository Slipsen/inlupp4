package org.ioopm.calculator.ast;

public class Sin extends Unary{
    /**
     * iniates class. Stores value and sets order
     * @param value class 
     */
    public Sin(SymbolicExpression value) {
        super(value,25,"sin");
    }
    
    /** 
     * @return name of class in form of String
     */
    public String getName(){
        return getName();
    }
    
    /** 
     * @param e
     * @return boolean
     */
    public boolean equals(Object e){
        if(e instanceof Sin){
            return super.equals(e);
        }
        else{
            return false;
        }
    }
    /**
     * 
     * @param e Environment  containing the currently existing variables
     * @throws IllegalAssignmentException if a value is assigned to a constant variable  * 
     */
    public SymbolicExpression eval(Environment e)  throws IllegalAssignmentException{
        SymbolicExpression se = get().eval(e);
        if(se.isConstant()){
            return new Constant(Math.sin(se.getValue()));
        }
        else{
            return new Sin(se);
        }
    }
    
}
