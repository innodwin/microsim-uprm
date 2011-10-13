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
public class Registers {
    private int INVALIDREGISTER = -1200;
    private int INVALIDVALUE = -1201;
    private ArrayList <String> registers;
    private Tools t = new Tools();
    public Registers()
    {
        registers = new ArrayList <String>();
        for(int i = 0;i<8;i++)
            registers.add("00000000");
    }
    
    public int write(int addr,String value ){
        
        if(addr > 7)
            return INVALIDREGISTER;
        if(!t.isBinary(value))
            return this.INVALIDVALUE;
        if(value.length() > 8) 
        return 1;
        
        return 1;
        
    }
}
