package org.ioopm.calculator.ast;

public class Multiplication extends Binary {

    public Multiplication(SymbolicExpression lhs, SymbolicExpression rhs) {
        super(lhs, rhs,100);
        //TODO Auto-generated constructor stub
    }
    public String getName(){
        return "*";
    }
    /**
     * checks if both objects are the same. Then compares left and right tree
     * @param e Environment holding all defined variables
     * @return true if same false else
     */
    public boolean equals(Object e){
        if(!(e instanceof Multiplication)){
            return false;
        }
        else{
            return super.equals(e);
        }
    }
    /**
     * evaluates its chld symbolic expressions
     * if it evaulates into constants return product as a new constant
     * if at least one is a SymbolicExpression return a Multiplication with them as trees
     * @throws IllegalAssignmentException never
     * @return constant or new symbolicexpression
     */
    public SymbolicExpression eval(Environment e) throws IllegalAssignmentException{
        SymbolicExpression  lhs = super.lhs.eval(e);
        SymbolicExpression rhs = super.rhs.eval(e);
        if(lhs.isConstant()&&rhs.isConstant()){
            return new Constant(rhs.getValue() * lhs.getValue());
        }
        else{
            return new Multiplication(lhs,rhs);
        }
    }
    
    
    
}
