
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
      Instruction test = new Instruction ("F4A6");   
      Instruction test2 = new Instruction ("0000");
      
      System.out.println("Hex inst " + test.getHexInstruction());
      System.out.println("Int inst " +test.getIntInstruction());
      System.out.println("Bin inst " +test.getBinInstruction());
      System.out.println("Opcode: " +test.getOpcode());
      System.out.println("Register "+test.getRegister());
      System.out.println("Operand "+test.getOperand());
     }
}
