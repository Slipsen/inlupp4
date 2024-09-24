package org.ioopm.calculator.ast;

public class Clear extends Command {
    private static Clear theInstance = new Clear();
    private static int  i = 0; 
    private Clear (){
        i++;
        assert(i==1);
    }
    public static Clear getInstance(){
        return theInstance;
    }

    public String getName(){
        return "clear";
    }
    public String toString(){
        return "clear";
    }
    public boolean equals(Object e){
        if(e instanceof Quit){
            return true;
        }
        else{
            return false; 
        }
    }
    
}
