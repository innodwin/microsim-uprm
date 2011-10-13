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
            int trash = random.nextInt(256); //Generates the trash
            memory.add(Integer.toBinaryString(trash)); //converts the trash to binary String and inserts it into memory
            //System.out.println(memory.get(i));
        }
    }
    
    public String getContent(String address)
    {
        int dir = Integer.parseInt(address, 2);
        return memory.get(dir);
    }
    
    public void setContent(String address, String content)
    {
        memory.add(Integer.parseInt(address, 2), content);        
    }
}
