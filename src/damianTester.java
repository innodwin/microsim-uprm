
import microsim.Instruction;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author damian
 */
public class damianTester {
     public static void main(String[] args) {
      Instruction test = new Instruction ("FFAA");   
      Instruction test2 = new Instruction ("0000");
      
      System.out.println(test.getHexInstruction());
      System.out.println(test.getIntInstruction());
      System.out.println(test.getBinInstruction());
      System.out.println(test2.getBinInstruction());
     }
}
