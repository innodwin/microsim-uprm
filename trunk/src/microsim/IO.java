/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

import java.util.Scanner;

/**
 *
 * @author luillo
 */
public class IO {
    private char input = 0;
    private String inputHEX = "";
    private String inputBin ="";
    public void readChar(){
        Scanner in = new Scanner(System.in);
        input = in.next().charAt(0);
        int temp = (int) input;
        inputHEX = Integer.toHexString(temp);
        inputBin = Integer.toBinaryString(temp);
    }
    
    public char getInput(){
        return input;
    }
    
    public String getInputHEX(){
        return inputHEX;
    }
    
    public String getInputBin(){
        return inputBin;
    }
}
