package org.ioopm.calculator.ast;

/**
 * class meant for holding classes that form one-branch trees. Holds another Symbolic expression
 */

public abstract class Unary  extends SymbolicExpression{
    private SymbolicExpression branch = null;
    String name;
    public Unary(SymbolicExpression branch ,int priority, String name){
        super(priority);
        this.branch = branch; 
        this.name = name; 
    }

    /**
     * @return string of itself and its child objects with parentheses around the child objects string if its priority is lower 
     */
    public String toString(){
        String str = branch.toString() ;
        if(this.getPriority()<branch.getPriority()){
            str = "(" +str + ")";
        }
        return this.getName()+ " " + str;
    
    }
    /**
     * compares
     *  calls equals on the branch it holds to check its sub tree
     * @return true if both sub-trees are the same
     */
    public boolean equals(Object e){
        return branch.equals(((Unary) e).get());
    }
    /**
     * 
     * @return the branch stored in this unary
     */
    public SymbolicExpression get(){
        return branch;
    }
    public String getName() {
        return name;
    }
    /**
     * simple test class for seeing the structure of a tree
     * @return String containing the class name of this and every sub object
     */
    /**
     * 
     * @return this class name and every class under it 
     */
     public String getOverlay() {
        return this.getObjectName() + " " +getParantheses(branch, branch.getOverlay());
    }


    


}
