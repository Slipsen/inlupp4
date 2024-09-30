package org.ioopm.calculator.ast;

public abstract class Atom extends SymbolicExpression {


    /**atoms can't have anyvariable unless a variable */
    boolean varExists(Environment vars, SymbolicExpression sy) {
        return false; 
    }
     
    
}
