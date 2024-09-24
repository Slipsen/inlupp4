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
    public static boolean isConstant(String e){
            return constants.get(e)!=null;
    }
    
}
