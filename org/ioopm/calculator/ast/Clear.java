package org.ioopm.calculator.ast;

public class Clear extends Command {
    private static Clear theInstance = new Clear();
    private static int  i = 0; 
    private Clear (){
        i++;
        assert(i==1);
    }
    /**
     * 
     * @return returns the class object of which there exists only one
     */
    public static Clear getInstance(){
        return theInstance;
    }
    /**
     * @returnthe name in form of String 
     */
    public String getName(){
        return "clear";
    }
    /**
     * return the name in in form of a string
     */
    public String toString(){
        return "clear";
    }
    /**
     * checks if other object is an instance of Quit
     * @return true if other object is Quit else false
     */
    public boolean equals(Object e){
        if(e instanceof Quit){
            return true;
        }
        else{
            return false; 
        }
    }
    
}
