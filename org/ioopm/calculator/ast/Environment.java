package org.ioopm.calculator.ast;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
public class Environment extends HashMap<Variable, SymbolicExpression> {
    /**Seperate map to hold constants to clear main hashmap when needed
    *has variable as key to make it easier to look existingup values
    */
    private ArrayList<String> reserved = new ArrayList<String>();

    public SymbolicExpression put(Variable key, SymbolicExpression value){
        //Nothing supposed to be found if it is a named constant
        return super.put(key, value);    
    }
    /**
     * 
     * @param st the the name of the variable
     * @return Symbolic if it exists else 
     */
    public SymbolicExpression get(String str){
        return get(new Variable(str));
    }
    public SymbolicExpression get(Variable var){
        if(Constants.isConstant(var)){
            return Constants.get(var);
        }
        else{
            SymbolicExpression vari = super.get(var);
            if(var!=null) return vari;
            else return var;
        }
    }

    /**checks if name s is reserverdf
     * 
     * @param var variable with name to be checked 
     * @return true if reseved else not 
     */
    public boolean isReserved(Variable var){
        return isReserved(var.getName());
    }
    
    /**checks if name s is reserverdf
     * 
     * @param var string name to be checked 
     * @return true if reseved else not 
     */
    public boolean isReserved(String var){
        return Constants.isConstant(var) || reserved.contains(var);
    }
    
    /**Adds a reserved name
     * @param var variable with name to be checked 
     * @return true if reseved else not 
     */
    public void putReserved(Variable var){
        putReserved(var.getName());
    }
    public void putReserved(String var){
        reserved.add(var);
    }
    /** //TODO fix
     * @return list of variables that Environment holds
     */
    public void printVars(){
        System.out.println("Current variables are: \n");
        Set<Variable> keys= this.keySet();
        for(Variable var : keys){
            System.out.println(var + " :" + this.get(var));
        }

    }/**
    removes all values stored in enVironment except reserved ones */
    public void clear(){
        super.clear();
    }

    
}
