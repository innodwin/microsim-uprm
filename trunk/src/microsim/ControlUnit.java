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
 * Control Unit class handles the overall operation, execution flow, and branching of the microprocessor.
 * In addition, it also handles the load and store related instructions.
 * @author Damian Esteves
 */
public class ControlUnit {

    protected static Memory memory = new Memory(); //memory object to be used in the microprocessor
    protected static Registers registers = new Registers(); //register object to be used in the microprocessor
    private ArrayList<String> hexInstructions = new ArrayList<String>(64); //ArrayList to hold the original hexadecimal instructions
    private File instructionFile; //File object that will hold the text file with instructions
    private boolean stop = false; //Boolean for handling the STOP instruction and stopping execution.
    private IO io; //Used for handling keyboard input
    
    //Constants for accessing the SR flags
    private static final int ZERO = 0;
    private static final int CARRY = 1; 
    private static final int NEGATIVE = 2;
    private static final int OVERFLOW = 3; 
    //Constants for accessing the Registers
    private static final String R1 = "000";
    private static final String R2 = "001";
    private static final String R3 = "010";
    private static final String R4 = "011";
    private static final String R5 = "100";
    private static final String R6 = "101";
    private static final String R7 = "110";
    private static final String R8 = "111";



    /**
     * Constructor creates the Control Unit, parses the instructions from input file into memory, and initializes the microprocessor.
     * @param instructions The text file with the instructions to be loaded into memory for execution
     * @throws FileNotFoundException Exception thrown when the user points the simulator to a nonexistent file
     * @throws IOException IO Exception when reading file
     */
    public ControlUnit(File instructions) throws FileNotFoundException, IOException {        
        //Initializations for reading from file
        FileInputStream fstream = new FileInputStream(instructions);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        int lineNumber = 0; //line counter for reporting what line number an improper isntruction was found
        while ((strLine = br.readLine()) != null)   { //reads from file until finding and empty line
            lineNumber++; //increments line counter
            if(strLine.length()== 4) //Verifies that the instruction is of proper length (4)
                System.out.println("Invalid instruction at line: " +lineNumber); //If an instruction is improper, it lets the user know via the console    
            else if(hexInstructions.size() >= 64) //Verifies if the code segment in memory is full
                System.out.println("64 instructions in memory. Code segment full"); //Lets the user know of the full code segment via the console
            else
                hexInstructions.add(strLine); //Finally, adds an instruction into the ArrayList
        }
         in.close(); //Close input scanner
         
         initialize(); //Calls the method to initialize the microprocessor for operation
    }
    
    /**
     * Parses the instructions from the hexadecimal ArrayList into binary Strings, and copies them into the memory's code segment
     */
    public void instructionsToMemory(){
        int size = hexInstructions.size(); //Gets the total number of instructions to be inserted into memory
        for(int i=0;i<size;i++){
           String binInstruction = InstructionParser.parse(hexInstructions.get(i)); //Parses instruction from hexadecimal to binary
           String address = Integer.toBinaryString(i*2); //multiplies address by two to get addresses in increments of two bytes (Word)
           memory.setWord(address, binInstruction); //Sets the instruction into memory
        }
    }
    
    /**
     * Executes the instruction currently in the Instruction Register
     */
    public void executeInstruction(){
        String InstructionRegister = registers.getIR(); //Grabs the instruction from the IR
        String opcode = InstructionParser.opcode(InstructionRegister); //Grabs the Opcode
        String register = InstructionParser.register(InstructionRegister); //Grabs the register
        String operand = InstructionParser.operand(InstructionRegister); //Grabs the operand
        String accumulatorValue; //String that will hold the accumulator value when needed
        String registerValue; //String that will hold the register value when needed
        String result; //String to hold results of operations
        
        int intopcode = Integer.parseInt(opcode, 2); //Parses the opcode into a digit in order to use it in the following Switch
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
            default: System.out.println("Invalid Opcode"); //If the Opcode does not match any of the above, it is incorrect and the user is informed via the console
        }
        
    }
 
    /**
     * Native Instruction: LDA rf - Copies the specified register into the Accumulator.
     * @param register The register to be copied
     */
    public void ldaRegister(String register){
        String registerValue = registers.read(register);
        registers.setAccumulator(registerValue);
    }
    
    /**
     * Native Instruction: STA rf - Copy accumulator to specified register.
     * @param register The register to be copied to
     */
    public void staRegister(String register){
        String accumulatorValue = registers.getAccumulator();
        registers.write(register, accumulatorValue);
    }
    
    /**
     * Native Instruction: LDA addr - Load accumulator from memory location
     * @param address The address of the content to be loaded
     */
    public void ldaAddress(String address){
        int intAddress = Integer.parseInt(address, 2); //parses address to integer
        if(intAddress == 250 || intAddress == 251) //if the address is 250 or 251, they are keyboard inputs to be requested
            //TODO: Read from keyboard and save to accumulator and address
            io.readChar(); 
        else{//Else, simply copy memory to accumulator
            String addressContent = memory.getByte(address); 
            registers.setAccumulator(addressContent);
        }
    }
    
    /**
     * Native Instruction: STA addr - Store accumulator to memory location
     * @param address The address to which the accumulator will be copied
     */
    public void staAddress(String address){
        String accumulatorValue = registers.getAccumulator();
        memory.setByte(address, accumulatorValue);
    }
    
    /**
     * Native Instruction: LDI - Load immediate value from operand into accumulator
     * @param immediate The explicit value to be loaded
     */
    public void ldi(String immediate){
        registers.setAccumulator(immediate);
    }
    
    /**
     * Native Instruction: BRZ - Branches if Zero Flag is set by copying contents of R7 to PC
     */
    public void brz(){
        if(registers.getSR(ZERO).equals("1"))
            registers.setPC(registers.read(R7));
    }
    
    /**
     * Native Instruction: BRC - Branches if Carry Flag is set by copying contents of R7 to PC
     */
    public void brc(){
        if(registers.getSR(CARRY).equals("1"))
            registers.setPC(registers.read(R7));
    }
    
    /**
     * Native Instruction: BRN - Branches if Negative Flag is set by copying contents of R7 to PC
     */
    public void brn(){
        if(registers.getSR(NEGATIVE).equals("1"))
            registers.setPC(registers.read(R7));
    }
    
    /**
     * Native Instruction: BRO - Branches if Overflow Flag is set by copying contents of R7 to PC
     */
    public void bro(){
        if(registers.getSR(OVERFLOW).equals("1"))
            registers.setPC(registers.read(R7));
    }
    
    /**
     * Native Instruction: NOP - Does nothing.
     */
    public void nop(){
        System.out.println("Yup. Nothing happened.");
    }
    
    /**
     * Native Instruction: STOP - Stops execution
     */
    public void stop(){
        stop = true;
        
    }
    
    /**
     * Operates the simulator one instruction at a time.
     * Checks for previous stop instruction, sets the IR from the PC, and executes the instruction
     * If the stop instruction was executed previously, it reinitializes the simulator.
     */
    public void nextStep(){
        if(!stop){ //Checks if the previous instruction was STOP
            registers.setIR(memory.getWord(registers.getPC())); //Gets the PC, uses it as an address to get the instruction Word from memory, and sets it as the next PC
            registers.incrementPC();
            executeInstruction();
        }
        else{
            initialize();
            System.out.println("Simulator has been reinitialized"); //TODO:Implement this message in a popup window
        }
    }
    
    /**
     * Operates the emulator in Run mode. All the instructions in memory are run sequentially and without interruption (except for a keyboard prompt if present)
     */
    public void run(){
        do
            nextStep();
        while(!stop);
        System.out.println("STOP instruction received, reinitializing simulator"); //TODO: Implement this message in a popup window
        initialize();
        
    }
    /**
     * Initializes the simulator: Copies instructions to memory and initializes the PC to address 00h
     */
    private void initialize(){
        instructionsToMemory();
        registers.setPC("00000000");
        
    }
    /**
     * Implemented so the GUI will have access to the Registers for display purposes.
     * @return The Registers object used in the Control Unit
     */
    public Registers getRegisters(){
        return this.registers;
    }
    
    /**
     * Implemented so the GUI will have access to the Memory for display purposes.
     * @return The Memory object used in the Control Unit
     */
    public Memory getMemory(){
        return this.memory;
    }
    
}
