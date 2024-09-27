package org.ioopm.calculator.ast;
import java.util.HashMap;
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


    public boolean isReserved(Variable var){
        return isReserved(var.getName());
    }
    public boolean isReserved(String var){
        return Constants.isConstant(var) || reserved.contains(var);
    }
    public void putReserved(Variable var){
        putReserved(var.getName());
    }
    public void putReserved(String var){
        reserved.add(var);
    }
    public void printVars(){
        System.out.println("Current variables are: \n");
        System.out.println(this.keySet());

    }
    public void clear(){
        super.clear();
    }

    
}
