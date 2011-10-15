/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;


/**
 *This class represents an Instruction object.
 * Instructions should be created when reading the list of instructions from the input text file.
 * Each instruction string is passed (in hexadecimal) to the Instruction constructor.
 * This Instruction object can then be queried to provide the relevant parts of the instruction such as opcode.
 * @author damian
 */
public class Instruction {
    
    /**
     * Property holding the original instruction in a Hexadecimal String
     */
    protected String hexInstruction;
    /**
     * Property holding the instruction in a native integer (decimal)
     */
    protected int intInstruction;
    /**
     * Property holding the instruction in a Binary String
     */
    protected String binInstruction; 
    
    /**
     * Creates the Instruction object for use.
     * @param instruction The original instruction in a Hexadecimal String.
     */
    public Instruction(String instruction) {
        //TODO: Check if instruction is 4 characters long
        hexInstruction = instruction;
        intInstruction = Integer.parseInt(hexInstruction, 16); //Converts instruction to int
        binInstruction = Integer.toBinaryString(intInstruction); //Converts instruction to binary
            int length = binInstruction.length(); //grabs the length of the binary instruction
            if(length != 16) //checks  if the instruction is 16 bits long
                for(int i = length; i < 16; i++) //for every missing bit
                    binInstruction = "0" + binInstruction; //add a leading zero to the String
    }
    
    /**
     * Get the value of hexInstruction
     * @return the value of hexInstruction
     */
    public String getHexInstruction() {
        return hexInstruction;
    }
    
    /**
     * 
     * @return
     */
    public int getIntInstruction(){
        return intInstruction;
    }
    
    /**
     * A Binary Instruction is composed of 16 bits. 5 bits for OpCode, 3 bits for Register, and 3 bits for the Operand
     * @return
     */
    public String getBinInstruction() {
        return binInstruction;
    }
    
    /**
     * 
     * @return
     */
    public String getOpcode() {
        String opcode = binInstruction.substring(0, 5);
        return opcode;
    }
    
    /**
     * 
     * @return
     */
    public String getRegister() {
        String register = binInstruction.substring(5, 8);
        return register;
    }
    
    /**
     * 
     * @return
     */
    public String getOperand(){
        String operand = binInstruction.substring(8, 16);
        return operand;
    }

}
