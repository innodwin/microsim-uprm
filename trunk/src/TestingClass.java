
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author damian
 */
public class TestingClass {
   
    public static void main(String[] args){
    System.out.println("Hello Damian!");
    Random ram = new Random();
    ArrayList<String> names= new ArrayList<String>();
    names.add("Luis");
    names.add("Daniel");
    names.add("Damian");
    
    System.out.println(ram.nextInt(10));
    
    }
}
