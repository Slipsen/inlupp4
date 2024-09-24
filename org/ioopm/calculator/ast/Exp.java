package org.ioopm.calculator.ast;

public class Exp extends Unary {


    public Exp(SymbolicExpression value) {
        super(value,25);
    }
    @Override
    public String getName() {
        
    return "exp";
    }
        /**
     * checks if same class. then checks child value
     * @param e Environment holding all defined value
     * @return true if same, false if not
     */
    public boolean equals(Object e){
        if(e instanceof Exp){
            return super.equals(e);
        }
        else{
            return false; 
        }
    } 

    public SymbolicExpression eval( Environment e) throws IllegalAssignmentException{
        SymbolicExpression se = get().eval(e);
        if(se.isConstant()){
            return new Constant(Math.exp(se.getValue()));
        }
        else{
            return new Exp(se);
        }
    }
}
