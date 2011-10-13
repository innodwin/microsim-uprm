/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

import java.util.ArrayList;

/**
 *
 * @author damian
 */

/* First 8 positions in the array are reserved for the 
 * general purpose registers. the 9th postion(index 8) is the 
 * program counter (PC), the 10th is the accumulator. all these are
 * 8 bits long. the 11th position is the Instruction register, which
 * is 16 bits long.
 * the 12th position is the status register, which
is 4 bits in size.*/
public class Registers {
    private int INVALIDREGISTER = -1200;
    private int INVALIDVALUE = -1201;
    private int INVALIDSIZE = -1202;
    private int INVALIDFORMAT = -1203;
    private ArrayList <String> registers;
    private Tools t = new Tools();
    public Registers()
    {
        registers = new ArrayList <String>();
        for(int i = 0;i<10;i++)
            registers.add("00000000");
        registers.add("0000000000000000");
        registers.add("0000");

    }
    
    
    /**
     * Writes a binary value to one of the special purpose registers
     * @param addr
     * @param value
     * @return An error code in the negative range numbers. If successful returns 1.
     */
    public int write(String address,String value ){
        int addr = 0;
        int val = 0;
        try{
            addr = Integer.parseInt(address, 2);
           }catch(Exception e)
           { 
               return INVALIDFORMAT;
           }
        
        if(addr > 7)
            return INVALIDREGISTER;
        if(!t.isBinary(value))
            return this.INVALIDVALUE;
        if(value.length() > 8) 
            return INVALIDSIZE;
        
        System.out.println(t.extendBinaryValue(8, value));
        registers.add(addr, value);
        
        return 1;
        
    }
    
    /**
     * 
     * @param addr
     * @return 
     */
    public String read(int addr) {
        
        if(addr > 7)
            return null;
        return "DD";
        
    }
}
