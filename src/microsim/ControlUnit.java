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

    private Memory memory = new Memory();
    private Registers registers = new Registers();
    private ArrayList<String> hexInstructions = new ArrayList<String>(128);    

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
        String accumulatorValue;
        String registerValue;
        String result;
        
        int intopcode = Integer.parseInt(opcode, 2);
        switch(intopcode){
            case 0: //AND
                //ALU.and();
                break;
            case 1: //OR
                //ALU.or();
                break;
            case 2: //XOR
                //ALU.xor();
                break;
            case 3: //ADDC
                //ALU.addc();
                break;
            case 4: //SUB
                //ALU.sub();
                break;
            case 5: //MUL
                //TODO, change multiply to parse these 2 8bit inputs into the 4 LSB.
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.multiply(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                break;
            case 6: //NEG
                //ALU.neg();
                break;
            case 7: //NOT
                //ALU.not();
                break;
            case 8: //RLC
                //ALU.rlc();
                break;
            case 9: //RRC
                //ALU.rrc();
                break;
            case 10: //LDA rf
                ldaRegister(register);
                break;
            case 11: //STA rf
                staRegister(register);
                break;
            case 12: //LDA addr
                ldaAddress(operand);
                break;
            case 13: //STA addr
                staAddress(operand);
                break;
            case 14: //LDI Immediate
                ldi(operand);
                break;
            case 16: //BRZ
                //brz();
                break;
            case 17: //BRC
                //brc();
                break;
            case 18: //BRN
                //brn();
                break;
            case 19: //BRO
                //bro();
                break;
            case 31: //STOP
                //stop();
                break;
            case 24: //NOP
                //nop();
                break;
            default: System.out.println("Invalid Opcode");
        }
        
    }
 
    public void ldaRegister(String register){
        String registerValue = registers.read(register);
        registers.setAccumulator(registerValue);
    }
    
    public void staRegister(String register){
        String accumulatorValue = registers.getAccumulator();
        registers.write(register, accumulatorValue);
    }
    
    public void ldaAddress(String address){
        String addressContent = registers.read(address);
        registers.setAccumulator(addressContent);
    }
    
    public void staAddress(String address){
        String accumulatorValue = registers.getAccumulator();
        registers.write(address, accumulatorValue);
    }
    
    public void ldi(String immediate){
        registers.setAccumulator(immediate);
    }
    
    public void brz(){
        //if(registers.getSR(index) == 1)
            registers.setPC(registers.read("111"));
    }
    
    public void brc(){
        //if(registers.getSR(index) == 1)
            registers.setPC(registers.read("111"));
    }
    
    public void brn(){
        //if(registers.getSR(index) == 1)
            registers.setPC(registers.read("111"));
    }
    
    public void bro(){
        //if(registers.getSR(index) == 1)
            registers.setPC(registers.read("111"));
    }
    
}
