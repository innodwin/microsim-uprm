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
    
    public static String multiply(String op1, String op2)
    {
       if(op1.length()>4 || op2.length()>4){
           System.out.println("Problem multiplying: Operand bigger than 4 bits: A & B: " + op1 + " "+ op2);
           return "";
       }
       Tools t = new Tools();
       int tmp1 = t.binToDec(op1);
       int tmp2 = t.binToDec(op2);
       int result = tmp1*tmp2;
       System.out.println("op1: "+tmp1 + " op2: " + tmp2 + "result: " + result);
       String formattedResult = t.extendBinaryValue(8, Integer.toBinaryString(result));
       return formattedResult;
    }
}
