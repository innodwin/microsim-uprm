/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Handles the Instructions received from the file
 * @author Damian Esteves, Luis Rosario
 */
public class ControlUnit {

    private Memory memory = new Memory();
    private Registers registers = new Registers();
    private ArrayList<String> hexInstructions = new ArrayList<String>(64);    
    File instructionFile;
    /**
     * 
     */
    public static final int ZERO = 0;
    /**
     * 
     */
    public static final int CARRY = 1;
    /**
     * 
     */
    public static final int NEGATIVE = 2;
    /**
     * 
     */
    public static final int OVERFLOW = 3;
    /**
     * 
     */
    public static final String R1 = "000";
    /**
     * 
     */
    public static final String R2 = "001";
    /**
     * 
     */
    public static final String R3 = "010";
    /**
     * 
     */
    public static final String R4 = "011";
    /**
     * 
     */
    public static final String R5 = "100";
    /**
     * 
     */
    public static final String R6 = "101";
    /**
     * 
     */
    public static final String R7 = "110";
    /**
     * 
     */
    public static final String R8 = "111";
    private boolean stop = false;


    /**
     * 
     * @param instructions
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ControlUnit(File instructions) throws FileNotFoundException, IOException {        
        /*
         * Pseudocode tentativo:
         * 
         * Grab file, insert instructions in hex into a string.
         * Make ArrayList of hex instructions. ArrayList size 128.
         * check length of instructions is 4 characters before inserting. If <4 throw error.
         * 
         */
        FileInputStream fstream = new FileInputStream(instructions);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null)   {
            //System.out.println (strLine);
            hexInstructions.add(strLine);
        }
         in.close();
         
         initialize();
    }
    
    /**
     * 
     */
    public void instructionsToMemory(){
        int size = hexInstructions.size();
        int count = 0;
        for(int i=0;i<size;i++){
           String binInst = InstructionParser.parse(hexInstructions.get(i));
           //System.out.println(binInst);
           String address = Integer.toBinaryString(i*2);
           //System.out.println(address);
           memory.setWord(address, binInst);
        }
    }
    
    /**
     * 
     * @param InstructionRegister
     */
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
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.and(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                break;
            case 1: //OR
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.or(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                break;
            case 2: //XOR
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.xor(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                break;
            case 3: //ADDC
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.addc(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                break;
            case 4: //SUB
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.sub(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                break;
            case 5: //MUL
                //TODO, change multiply to parse these 2 8bit inputs into the 4 LSB.
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.mul(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                break;
            case 6: //NEG
                accumulatorValue = registers.getAccumulator();
                result = ALU.neg(accumulatorValue);
                registers.setAccumulator(result);
                break;
            case 7: //NOT
                accumulatorValue = registers.getAccumulator();
                result = ALU.not(accumulatorValue);
                registers.setAccumulator(result);
                break;
            case 8: //RLC
                accumulatorValue = registers.getAccumulator();
                result = ALU.rlc(accumulatorValue);
                registers.setAccumulator(result);
                break;
            case 9: //RRC
                accumulatorValue = registers.getAccumulator();
                result = ALU.rrc(accumulatorValue);
                registers.setAccumulator(result);
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
                brz();
                break;
            case 17: //BRC
                brc();
                break;
            case 18: //BRN
                brn();
                break;
            case 19: //BRO
                bro();
                break;
            case 31: //STOP
                stop();
                break;
            case 24: //NOP
                nop();
                break;
            default: System.out.println("Invalid Opcode");
        }
        
    }
 
    /**
     * 
     * @param register
     */
    public void ldaRegister(String register){
        String registerValue = registers.read(register);
        registers.setAccumulator(registerValue);
    }
    
    /**
     * 
     * @param register
     */
    public void staRegister(String register){
        String accumulatorValue = registers.getAccumulator();
        registers.write(register, accumulatorValue);
    }
    
    /**
     * 
     * @param address
     */
    public void ldaAddress(String address){
        String addressContent = memory.getByte(address);
        registers.setAccumulator(addressContent);
    }
    
    /**
     * 
     * @param address
     */
    public void staAddress(String address){
        String accumulatorValue = registers.getAccumulator();
        memory.setByte(address, accumulatorValue);
    }
    
    /**
     * 
     * @param immediate
     */
    public void ldi(String immediate){
        registers.setAccumulator(immediate);
    }
    
    /**
     * 
     */
    public void brz(){
        if(registers.getSR(ZERO).equals(1))
            registers.setPC(registers.read(R7));
    }
    
    /**
     * 
     */
    public void brc(){
        if(registers.getSR(CARRY).equals(1))
            registers.setPC(registers.read(R7));
    }
    
    /**
     * 
     */
    public void brn(){
        if(registers.getSR(NEGATIVE).equals(1))
            registers.setPC(registers.read(R7));
    }
    
    /**
     * 
     */
    public void bro(){
        if(registers.getSR(OVERFLOW).equals(1))
            registers.setPC(registers.read(R7));
    }
    
    /**
     * 
     */
    public void nop(){
        System.out.println("Yup. Nothing happened.");
    }
    
    /**
     * 
     */
    public void stop(){
        //TODO: Figure out how to stop execution.
        /*
         * Possibly have the the stop() set a custom flag that is checked at
         * the beginning of every nextStep(), if the flag is set, then next step
         * does not run and the simulator is set back into initialization mode.
         */
        stop = true;
        
    }
    //TODO: I'm not sure if this stopFlag shit makes sense, but fuck it
    /**
     * 
     */
    public void nextStep(){
        if(stop){
            registers.setIR(memory.getWord(registers.getPC()));
            registers.incrementPC();
            executeInstruction(registers.getIR());
        }
        else
            //Pop up window "STOP instruction received, reinitializing simulator"
            initialize();
            
    }
    
    /**
     * 
     */
    public void run(){
        //TODO: figure out the condition to put inside the while
        do
            nextStep();
        while(!stop);
        //Pop up window "STOP instruction received, reinitializing simulator"
        initialize();
        
    }
    
    public void initialize(){
        instructionsToMemory();
         memory.showMemory();
         registers.setPC("00000000");
        
    }
}
