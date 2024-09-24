package org.ioopm.calculator.ast;
import java.util.HashMap;

public class Environment extends HashMap<Variable, SymbolicExpression> {



    public SymbolicExpression put(Variable arg0, SymbolicExpression arg1) {
        SymbolicExpression sy = this.get(arg0);
        return super.put(arg0, arg1);
    }
    
}
