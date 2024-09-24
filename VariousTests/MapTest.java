package VariousTests;
import java.util.HashMap;

import org.ioopm.calculator.ast.*;
import org.ioopm.calculator.ast.NamedConstant;
public class MapTest {  


    public static void main(String[] args){
         HashMap<String,String> karta  =new HashMap<>();
         System.out.println(karta.put("test", "try"));
         System.out.println(karta.put("test", "attempt"));
         System.out.println(karta.put("test", "run"));
         System.out.println(new NamedConstant("name", 20.0));


        }
    
}
