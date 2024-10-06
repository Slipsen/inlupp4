package org.ioopm.calculator.ast;

public class CommandException extends Exception {
    private Command command;
    public CommandException(Command command){
        this.command = command;
    }
    /**get the command that caused the error 
     * @return the command
    */
    public Command getCommand(){
        return command;
    }
}
