/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;


/**
 *
 * @author damian
 */
public class Instruction {
    
    protected String hexInstruction;  
    protected int intInstruction;
    protected String binInstruction;
    
    public Instruction(String instruction) {
        hexInstruction = instruction;
        intInstruction = Integer.parseInt(hexInstruction, 16); //Converts instruction to int
        binInstruction = Integer.toBinaryString(intInstruction);
            int length = binInstruction.length();
            if(length != 16)
                for(int i = length; i < 16; i++)
                    binInstruction = "0" + binInstruction;
    }
    
    /**
     * Get the value of hexInstruction
     *
     * @return the value of hexInstruction
     */
    public String getHexInstruction() {
        return hexInstruction;
    }
    
    public int getIntInstruction(){
        return intInstruction;
    }
    
    public String getBinInstruction() {
        return binInstruction;
    }
    
    public String getOpcode() {
        String opcode = binInstruction.substring(0, 5);
        return opcode;
    }
    
    public String getRegister() {
        String register = binInstruction.substring(5, 8);
        return register;
    }
    
    public String getOperand(){
        String operand = binInstruction.substring(8, 16);
        return operand;
    }

}
