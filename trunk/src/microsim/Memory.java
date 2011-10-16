/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author damian
 */
public class Memory {
    
    private ArrayList <String> memory;  //ArrayList that will hold the 256 bytes of memory
    
    public Memory()
    {
        Random random = new Random();
        memory = new ArrayList <String>(); //Creates the memory ArrayList
        for(int i = 0;i<256;i++) //FOR loop to fill the memory with trash
        {
            int basura = random.nextInt(256); //Generates the trash
            String trash = Integer.toBinaryString(basura); //Converts trash to binary
            int length = trash.length(); //saves the trash String length
            if(length != 16)
                for(int j = length; j < 16; j++)
                    trash = "0" + trash; //fills leading zeros to get the Binary String to 16-bits
            memory.add(trash); //converts the trash to binary String and inserts it into memory
            //System.out.println(memory.get(i));
        }
    }
    
    public String getByte(String address)
    {
        int intAddress = Integer.parseInt(address, 2);
        return memory.get(intAddress);
    }
    
    public void setByte(String address, String content)
    {
        memory.add(Integer.parseInt(address, 2), content);        
    }
    
    public String getWord(String address){
        int intAddress = Integer.parseInt(address, 2);
        int mod = intAddress%2;
        String word;
        if(mod == 0)
            word = memory.get(intAddress) + memory.get(intAddress+1);
        else
            word = memory.get(intAddress-1) + memory.get(intAddress);
        return word;
    }
    
    public void setWord(String address, String value){
        int intAddress = Integer.parseInt(address, 2);
        String byte1 = value.substring(0,8);
        String byte2 = value.substring(8, 16);
        memory.set(intAddress, byte1);
        memory.set(intAddress+1,byte2);
        
    }
}
