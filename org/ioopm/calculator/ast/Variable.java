package org.ioopm.calculator.ast;

public class Variable extends Atom {
    private  String variable; 
    public Variable(String variable){
       this.variable  =variable; 
    }
    public String getName(){
        return variable;
    }
    public String toString(){
        return variable;
    }

    /**
     * 
     * @return true if variables are the same
     */
    public boolean equals(Object e){
        if(e instanceof Variable){
            Variable var = (Variable) e;
            return variable.equals(var.getName());
        }
        return false;
        
    }
 
     /**
    *Function evaluates the value tied to its variable and return a Constant or other SymbolicExpression 
     * @param e object holding existing variables
     * @return the result of the equation
     * @throws IllegalAssignmentException  
     */
    public SymbolicExpression eval(Environment e) throws IllegalAssignmentException{
        if(e.get(this)!=null){
            return (e.get(this).eval(e));
        }else{
          return new Variable(variable);
        }
    }
    /**
     * @return the hashcode for the value stored by variable
     * 
     */
    public int hashCode(){
        return variable.hashCode(); 
    }
    /**adds the variable for clarity
     * @return class name withthe variable
     */
    @Override
    public String getOverlay() {
        return super.getOverlay() + " (" + variable +")" ;
    }
}
