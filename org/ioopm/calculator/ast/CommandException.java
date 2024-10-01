package org.ioopm.calculator.ast;

public class CommandException extends Exception {
    private SymbolicExpression command;
    public CommandException(SymbolicExpression command){
        this.command = command;
    }
    /**get the command that caused the error 
     * @return the command
    */
    public SymbolicExpression getCommand(){
        return command;
    }
}
