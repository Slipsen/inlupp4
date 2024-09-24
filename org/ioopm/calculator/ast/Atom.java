package org.ioopm.calculator.ast;

public abstract class Atom extends SymbolicExpression {


    
    boolean varExists(Environment vars, SymbolicExpression sy) {
        return false; 
    }
     
    
}
