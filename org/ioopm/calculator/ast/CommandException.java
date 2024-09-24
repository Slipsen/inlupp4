package org.ioopm.calculator.ast;

public class CommandException extends Exception {
    private SymbolicExpression command;
    public CommandException(SymbolicExpression command){
        this.command = command;
    }
    public SymbolicExpression getCommand(){
        return command;
    }
}
