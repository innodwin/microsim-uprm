
package microsim;

import java.util.Scanner;

/**
 *
 * @author Luis Rosario
 */
public class IO {
    private char input = 0;
    private String inputHEX = "";
    private String inputBin ="";
    
    /**
     * Gets input from the user. This is on the console.
     */
    public void readChar(){
        Scanner in = new Scanner(System.in);
        input = in.next().charAt(0);
        int temp = (int) input;
        inputHEX = Integer.toHexString(temp);
        inputBin = Integer.toBinaryString(temp);
    }
    
    /**
     * 
     * @return the input from the user.
     */
    public char getInput(){
        return input;
    }
    
    /**
     * 
     * @return the input in hex.
     */
    public String getInputHEX(){
        return inputHEX;
    }
    
    /**
     * 
     * @return the input in binary form.
     */
    public String getInputBin(){
        return inputBin;
    }
}
