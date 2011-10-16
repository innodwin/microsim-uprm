/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

/**
 *
 * @author damian
 */
public class ALU {
    
    public static void and()
    {
        
    }
    
    public static void or()
    {
        
    }
    
    public static String multiply(String accumulator, String register)
    {
       String op1 = accumulator.substring(4, 8);
       String op2 = register.substring(4,8);
       Tools t = new Tools();
       int tmp1 = t.binToDec(op1);
       int tmp2 = t.binToDec(op2);
       int result = tmp1*tmp2;
       System.out.println("op1: "+tmp1 + " op2: " + tmp2 + "result: " + result);
       String formattedResult = t.extendBinaryValue(8, Integer.toBinaryString(result));
       return formattedResult;
    }
}
