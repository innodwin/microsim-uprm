/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

import java.io.File;
import java.util.ArrayList;

/**
 * Handles the Instructions received from the file
 * @author damian
 */
public class ControlUnit {

    private Memory memory;
    private ArrayList<String> hexInstructions;    

    public ControlUnit(File instructions) {        
        /*
         * Pseudocode tentativo:
         * 
         * Grab file, insert instructions in hex into a string.
         * Make ArrayList of hex instructions. ArrayList size 128.
         * check length of instructions is 4 characters before inserting. If <4 throw error.
         * 
         */
    }
    public void instructionsToMemory(){
        int size = hexInstructions.size();
        for(int i=0;i<size;i++){
           String binInst = InstructionParser.parse(hexInstructions.get(i));
           String address = Integer.toBinaryString(i);
           memory.setContent(address, binInst);
        }
    }
    
    public void executeInstruction(String InstructionRegister){
        String opcode = InstructionParser.opcode(InstructionRegister);
        String register = InstructionParser.register(InstructionRegister);
        String operand = InstructionParser.operand(InstructionRegister);
        
        int intopcode = Integer.parseInt(opcode, 2);
        switch(intopcode){
            case 0: ALU.and();//
                    break;
                
        }
        
    }
    
}
