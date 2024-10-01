package org.ioopm.calculator.ast;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
public class Environment extends HashMap<Variable, SymbolicExpression> {
    /**Seperate map to hold constants to clear main hashmap when needed
    *has variable as key to make it easier to look existingup values
    */
    private ArrayList<String> reserved = new ArrayList<String>();
    private final  String ANSWER = "ans"; //should do  this dynamoc but anothe t ime 
    private SymbolicExpression last = new Constant(0);
    public SymbolicExpression put(Variable key, SymbolicExpression value){
        //Nothing supposed to be found if it is a named constant
        if(Constants.isConstant(key)) return null;
        else if(key.getName().equalsIgnoreCase(ANSWER)){
            last = value;
            return value;
        }
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

    @SuppressWarnings("unused")
    public SymbolicExpression get(Variable var){
        if(var.getName().equalsIgnoreCase(ANSWER)){
            return last;
        }
        else if(Constants.isConstant(var)){
            return Constants.get(var);
        }
        else{
            SymbolicExpression vari = super.get(var);
            if(var!=null) return vari;
            
        }
        return new Variable(var.getName());
    }
    /**checks if name s is reserverdf
     * 
     * @param var variable with name to be checked 
     * @return true if reseved else not 
     */
    public boolean isReserved(Variable var){
        return isReserved(var.getName()) || var.getName().equalsIgnoreCase(ANSWER);
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
    /** //
     * @return list of variables the  Environment holds
     */
    public Set<Variable> printVars(){
        return  this.keySet();
        

    }/**
    removes all values stored in enVironment except reserved ones */
    public void clear(){
        super.clear();
    }
    
}
