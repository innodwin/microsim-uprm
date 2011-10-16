/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

/**
 *
 * @author damian
 */
public class InstructionParser {

    
    /**
     * Empty constructor. Class is only used for its methods
     */
    public InstructionParser() {   
    }
    
    
    /**
     * Converts hexadecimal instruction String into a binary String
     * @param instruction The instruction to convert
     * @return The instruction in a binary String
     */
    public static String parse(String instruction){
        //TODO: Check if instruction length = 4
        int decimal = Integer.parseInt(instruction, 16); //Converts instruction to int
        String binary = Integer.toBinaryString(decimal); //Converts int instruction to binary String
        int length = binary.length(); //grabs the length of the binary instruction
            if(length != 16) //checks  if the instruction is 16 bits long
                for(int i = length; i < 16; i++) //for every missing bit
                    binary = "0" + binary; //add a leading zero to the String
        return binary;
    }
    
    /**
     * Extracts the OpCode from a String that is already in binary format
     * @param binaryInstruction
     * @return The 5 opcode bits in a binary String
     */
    public static String opcode(String binaryInstruction){        
        String opcode = binaryInstruction.substring(0, 5);
        return opcode;
    }
    
    /**
     * 
     * @param binaryInstruction
     * @return
     */
    public static String register(String binaryInstruction){
        String register = binaryInstruction.substring(5, 8);
        return register;
    }
    
    /**
     * 
     * @param binaryInstruction
     * @return
     */
    public static String operand(String binaryInstruction){
        String operand = binaryInstruction.substring(8, 16);
        return operand;
    }
}
