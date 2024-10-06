package org.ioopm.calculator.ast;

public class Constant extends Atom {
    private double value;
    public Constant(double value){
        this.value = value;
    }
    public boolean isConstant(){
        return true; 
        
    }
    @Override
    public double getValue() {
        return value;
    }
    public String toString(){
        
        if(value<0) return "- "+String.valueOf(Math.abs(this.value));
        else return String.valueOf(this.value);
    }
    public boolean equals(Object e){
        if(e instanceof Constant){
            Constant c = (Constant) e;
            if(this.value==c.value){
                return true;
            }
        }
        return false; 

    }
    public SymbolicExpression eval(Environment e){
        return new Constant(value);
    }
    @Override
    boolean varExists(Environment vars, SymbolicExpression sy) {
        return false;
    }
        /**adds the variable for clarity
     * @return class name withthe variable
     */
    @Override
    public String getOverlay() {
        return super.getOverlay() + " (" + value+")" ;
    }
}
