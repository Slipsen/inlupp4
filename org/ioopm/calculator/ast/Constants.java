package org.ioopm.calculator.ast;

import java.util.HashMap;

public class Constants {
    private static final HashMap<String, Double> constants = new HashMap<>(); 

    static {
        constants.put("pi",Math.PI);
        constants.put("e",Math.E);
        constants.put("Answer", 42.0);
        constants.put("L", 6.022140857*Math.pow(10,23));
    }
    
    /**takes string and checks if named variable
     * 
     * @param e tells if the name is contained in the list of hash containing constants
     * @return true if it exists else false
     */
    public static boolean    isConstant(String e){
            return constants.get(e)!=null;
    }
    /**takes variable and checks if named
     * 
     * @param e the variable whose name is to be checked
     * @return true if it exists else false
     */
    public static boolean isConstant(Variable e){
        return constants.get(e.getName())!=null;
}   
//TODO maybe all evals should share the same NamedConstant object?
/**
 * returns t he named constant as a NamedConstant
 * @param e
 * @return
 */
    public static NamedConstant get(String e){
        return new NamedConstant(e,constants.get(e));
    }
    /**
     * returns the same as before
     * @param e variable with the name to be checked
     * @return returns a new Named Constant
     */
    public static NamedConstant get(Variable e){
        return new NamedConstant(e.getName(),constants.get(e.getName()));
    }
}
