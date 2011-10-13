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
    private final int INVALIDREGISTER = -1200;
    private final int INVALIDVALUE = -1201;
    private final int INVALIDSIZE = -1202;
    private final int INVALIDFORMAT = -1203;
    private final int PCINDEX = 8;
    private final int ACCUMULATORINDEX = 9;
    private final int IRINDEX = 10;
    private final int SRINDEX = 11;
    private ArrayList <String> registers;
    private Tools t = new Tools();
    
 //**************************************************************************************
 //Default Constructor
 //**************************************************************************************
    public Registers()
    {
        registers = new ArrayList <String>();
        for(int i = 0;i<9;i++)
            registers.add("00000000");
        registers.add("0000000000000000");
        registers.add("0000");

    }
    
//**************************************************************************************    
    /**
     * Writes a binary value to one of the special purpose registers
     * @param addr
     * @param value
     * @return An error code in the negative range numbers. If successful returns 1.
     */
//**************************************************************************************
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
        
        
        //If the value is not in an 8-bit format, this formats it.
        registers.add(addr, t.extendBinaryValue(8, value));
        
        for(int i = 0; i < registers.size();i++)
            System.out.println(registers.get(i));
        return 1;
        
    }

//**************************************************************************************
    /**
     * 
     * @param addr
     * @return 
     */
//**************************************************************************************
    public String read(String address) {
        int addr = 0;
        try{
            addr = Integer.parseInt(address, 2);
           }catch(Exception e)
           { 
               System.err.println("INVALID FORMAT ERROR Reading from register.. Value: " + address );
               return "";
           }
        
        if(addr > 7){
            System.err.println("Register size is 7... Value: " + addr );
            return "";
        }
        
         return registers.get(addr);
    }
    
    
//**************************************************************************************
//Sets the value of the Program Counter
//**************************************************************************************
    public int setPC(String value)
    {
        if(!t.isBinary(value))
            return INVALIDFORMAT;
        if(value.length()>8)
            return INVALIDSIZE;
        
        registers.add(PCINDEX, value);
        return 1;
    }

//**************************************************************************************
//Gets the value of the Program Counter
//**************************************************************************************
    public String getPC()
    {
        return registers.get(PCINDEX);
    }

//**************************************************************************************
//Sets the value of the Accumulator
//**************************************************************************************
    public int setAccumulator(String value)
    {
        if(!t.isBinary(value))
            return INVALIDFORMAT;
        if(value.length()>8)
            return INVALIDSIZE;
        
        registers.add(ACCUMULATORINDEX, value);
        return 1;
    }

//**************************************************************************************
//Gets the value of the Accumulator
//************************************************************************************** 
    public String getAccumulator()
    {
        return registers.get(ACCUMULATORINDEX);
    }

//**************************************************************************************
//Sets the value of the Instruction Register
//************************************************************************************** 
    public int setIR(String value)
    {
        if(!t.isBinary(value))
            return INVALIDFORMAT;
        if(value.length()>16)
            return INVALIDSIZE;
        
        registers.add(IRINDEX, value);
        return 1;
    }

//**************************************************************************************
//Gets the value of the Instruction Register
//************************************************************************************** 
    public String getIR()
    {
        return registers.get(IRINDEX);
    }

//**************************************************************************************
//Sets the value of the Status Register
//************************************************************************************** 
     public int setSR(int flagType,String value)
    {
        if(!t.isBinary(value))
            return INVALIDFORMAT;
        if(value.length()>1)
            return INVALIDSIZE;
        
        String tmp = registers.get(SRINDEX);
        ArrayList<String> tmpArr = new ArrayList<String>();
        
        for(int i = 0; i < tmp.length();i++)
            tmpArr.add(Character.toString(tmp.charAt(i)));
        
        tmpArr.add(flagType, value);
        tmp = "";
        
        for(int j = 0;j < tmpArr.size();j++)
            tmp += tmpArr.get(j);
                
        registers.add(SRINDEX, tmp);
        return 1;
    }
//**************************************************************************************
//Sets the value of the Program Counter
//**************************************************************************************    
    public String getSR(int index)
    {
        if(index > 3)
            return "";
        return String.valueOf(registers.get(SRINDEX).charAt(index));
    }
}
