package org.ioopm.calculator.ast;

public class Log extends Unary {

    public Log(SymbolicExpression value) {
        super(value,25,"log");
    }


    /**
     * checks if same class. then checks child value
     * @param e Environment holding all defined value
     * @return true if same, false if not
     */
    public boolean equals(Object e) {
        if(e instanceof Log){
            return super.equals(e);
        }
        else{
            return false; 
        }
    }
    /**
     * runs eval on  child tree. 
     * If a constant is returns it 
     * If Symnbolic expression is returned return new Log with it as value
     * 
     * @param e Environment holding all known variables
     * @throws IllegalAssignmentException never
     * @return new constant or new Log
     */
    public SymbolicExpression eval(Environment e) throws IllegalAssignmentException{
        SymbolicExpression se = get().eval(e);
        if(se.isConstant()){
            return new Constant(Math.log(se.getValue()));
        }
        else{
            return new Log(se);
        }
    }
    
}
