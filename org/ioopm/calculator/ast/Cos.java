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
    /**returns the cos value of its valie if it evaliates into a constant, else a a new Cos holding it
     * @param e the environment with variables
     * @throws IllegalAssignmentException if there is an attemmpt to assign anything to a named constant
     * @return a new Constant holding the cos-value of its double or a new Cos
     */
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
