package org.ioopm.calculator.ast;

public class IllegalAssignmentException extends Exception{
    /**
     * 
     * @param the message containing exactly what happened
     */
    public IllegalAssignmentException(String message){
        super(message);
    }
}
