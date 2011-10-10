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
public class Memory {
    private ArrayList <String> memory;
    
    public String getContent(String address)
    {
        int dir = Integer.parseInt(address, 16);
        return memory.get(dir);
    }
    
    public void setContent(String address, String content)
    {
        memory.add(Integer.parseInt(address, 16), content);
    }
}
