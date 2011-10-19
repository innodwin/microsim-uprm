package microsim;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Memory object represents the 256-byte memory bank in the microprocessor
 * Bytes 0-127 are reserved for the code segment.
 * Bytes 128-255 are for general use.
 * Words represent two contiguous bytes.
 * The LSB is on the lower, even address and the MSB is on the higher (odd) address
 * @author Damian Esteves
 */
public class Memory {
    
    private ArrayList <String> memory;  //ArrayList that will hold the 256 bytes of memory
    
    /**
     * The constructor initializes the memory ArrayList and fills it with randomly generated trash
     */
    public Memory()
    {
        Random random = new Random();
        memory = new ArrayList <String>(); //Creates the memory ArrayList
        for(int i = 0;i<256;i++) //FOR loop to fill the memory with trash
        {
            int intTrash = random.nextInt(256); //Generates the trash
            String trash = Integer.toBinaryString(intTrash); //Converts trash to binary String
            int length = trash.length(); //saves the trash String length
            if(length != 8) //checks if the generated trash String is less than 8 bits.
                for(int j = length; j < 8; j++) //FOR loop runs for the number of bits missing.
                    trash = "0" + trash; //fills leading zeros to get the Binary String to 8 bits
            memory.add(trash); //converts the trash to binary String and inserts it into memory
            //System.out.println(memory.get(i));
        }
    }
    
    /**
     * Retrieves the 8-bit binary String representing a single byte at a given memory address
     * @param address The address of the desired byte in binary String format
     * @return The 8-bit binary String
     */
    public String getByte(String address)
    {
        int intAddress = Integer.parseInt(address, 2); //parses the address into an Integer
        return memory.get(intAddress); //returns the corresponding String from the ArrayList
    }
    
    /**
     * Copies a binary String representing a single byte to a specified address
     * @param address The address where the byte will be copied to
     * @param content The 8-bit binary String to be set
     */
    public void setByte(String address, String content)
    {
        memory.set(Integer.parseInt(address, 2), content); //Copies the binary String into the corresponding ArrayList position
    }
    
    /**
     * Retrieves the 16-bit binary String representing a Word (two bytes ) at a given memory address.
     * Words always start at even addresses. If the user specifies an odd address, it retrieves the entire word that the odd byte belongs to.
     * @param address The address of the desired Word
     * @return The 16-bit binary String
     */
    public String getWord(String address){
        int intAddress = Integer.parseInt(address, 2); //Parses the address into a binary String
        int mod = intAddress%2; //Use the mod to verify if the address is even or not.
        if(mod == 0) //even address: get the specified byte and one above
            return memory.get(intAddress) + memory.get(intAddress+1); //grabs the bytes from each address and concatenates them
        else //odd address: get byte one address below specified and the one specified.
            return memory.get(intAddress-1) + memory.get(intAddress); //grabs the bytes from each address and concatenates them
    }
    
    /**
     * Copies a binary String representing a Word (two bytes) to a specified address.
     * @param address The LSB address of where the Word will be copied.
     * @param value The 16-bit binary String to be set
     */
    public void setWord(String address, String value){
        int intAddress = Integer.parseInt(address, 2);
        String byte1 = value.substring(0,8);
        String byte2 = value.substring(8, 16);
        memory.set(intAddress, byte1);
        memory.set(intAddress+1,byte2);
        
    }
    
    /**
     * Used for testing purposes. prints the memory contents to the terminal.
     */
    public void showMemory(){
        for(int i = 0; i < memory.size();i++)
        System.out.println(this.memory.get(i));
    }
    
    /**
     * Used to print the memory with it's indexes in the GUI
     * @return a string array with the memory values
     */
    public String[] getMemory(){
        String[] values = new String[256];
        
        for(int i = 0; i < 128;i++)
            values[i] =Integer.toHexString(i*2)+": " + Tools.binToHex(memory.get(i*2)) + Tools.binToHex(memory.get(i*2+1));
        
        return values;
    }
    
    /**
     * Used to convert the keyboard's ASCII code to it's String representation to print it on the GUI
     * @return the String representation of the keyboard value
     */
    public String getKeyboardMemLocationContent(){
        String vtr =""+ (char) Integer.parseInt(memory.get(250),2) + (char) Integer.parseInt(memory.get(251),2);
        return vtr;
    }
    
    /**
     * Used to convert the display's ASCII code to it's String representation to print it on the GUI
     * @return the String representation of the display value
     */
    public String getDisplayMemLocationContent(){
        String vtr = ""+ (char) Integer.parseInt(memory.get(252),2) + (char) Integer.parseInt(memory.get(253),2) + (char) Integer.parseInt(memory.get(254),2) + (char) Integer.parseInt(memory.get(255),2);
        return vtr;
    }
}
