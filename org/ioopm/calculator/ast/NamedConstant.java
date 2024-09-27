package org.ioopm.calculator.ast;
/**
 * variable used to separate variables that are tied to named constants from other constants
 */
public class NamedConstant extends Variable {
        private double value; 
    public NamedConstant(String name, Double value) {
        super(name);
        this.value = value; 
    }

    /**
     * return string value it holds
     */
    public String getName(){
        return super.getName();
    }
    /**
     * return string value it holds
     */
    public double getValue(){
        return value; 
    }
    /**
     * returns the String value of the constant tied to named constant
     */
    public String toString(){
        return super.getName();
    }
    /**
     * @return true since this is a cosntant
     */
    public boolean isConstant(){ 
        return true;
    }
    /**
     * @return Hashcode of value it holds
     */
    public int hashCode(){
        return getName().hashCode();
    } 
    /**
     * @param e Environment that holds all existing variables
     * @eturn evaluation of Constant tied to it
     */
    public SymbolicExpression eval(Environment e) {
        return new Constant(value);
    }
    
}
