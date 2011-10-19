package microsim;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Control Unit class handles the overall operation, execution flow, and branching of the microprocessor.
 * In addition, it also handles the load and store related instructions.
 * @author Damian Esteves
 */
public class ControlUnit {

    protected static Memory memory = new Memory(); //memory object to be used in the microprocessor
    protected static Registers registers = new Registers(); //register object to be used in the microprocessor
    private ArrayList<String> hexInstructions = new ArrayList<String>(64); //ArrayList to hold the original hexadecimal instructions
    private boolean stopFlag; //Boolean for handling the STOP instruction and stopping execution.
    //Constants for accessing the SR flags
    private static final int ZERO = 0;
    private static final int CARRY = 1; 
    private static final int NEGATIVE = 2;
    private static final int OVERFLOW = 3; 
    //Constants for accessing the Register;
    private static final String R7 = "111";
    private JDialog dialog = new JDialog();
    private MicrosimView mSimView;
    private MessageBox popUp = new MessageBox();

    /**
     * Constructor creates the Control Unit, parses the instructions from input file into memory, and initializes the microprocessor.
     * @param instructions The text file with the instructions to be loaded into memory for execution
     * @throws FileNotFoundException Exception thrown when the user points the simulator to a nonexistent file
     * @throws IOException IO Exception when reading file
     */
    public ControlUnit(File instructions, MicrosimView view) throws FileNotFoundException, IOException {  
        this.mSimView = view;
        //Initializations for reading from file
        FileInputStream fstream = new FileInputStream(instructions);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        mSimView.updateGUI(registers, memory);
        int lineNumber = 0; //line counter for reporting what line number an improper isntruction was found
        dialog.setModal(true);
        if(instructions != null)
            
        while ((strLine = br.readLine()) != null)   { //reads from file until finding and empty line
            lineNumber++; //increments line counter
            if(strLine.length() != 4) //Verifies that the instruction is of proper length (4)               
            {
                popUp.setErrorMessage("Invalid instruction at line: " +lineNumber + ". Instruction omitted."); //Popup error window
                popUp.showErrorDialog();    
            }  
            else if(hexInstructions.size() >= 64) //Verifies if the code segment in memory is full
            {
                popUp.setErrorMessage("64 instructions in memory. Code segment full. Instructions after 64 omitted."); //Popup error window
                popUp.showErrorDialog();  
            } 
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
           address = Tools.extendBinaryValue(8, address); //extends address to 8 bits
           memory.setWord(address, binInstruction); //Sets the instruction into memory
        }
    }
    
    /**
     * Goes through the Fetch-Decode-Execute process of executing an instruction
     */
    public void executeInstruction(){
        String InstructionRegister = registers.getIR(); //Grabs the instruction from the IR
        String opcode = InstructionParser.opcode(InstructionRegister); //Grabs the Opcode
        String register = InstructionParser.register(InstructionRegister); //Grabs the register
        String operand = InstructionParser.operand(InstructionRegister); //Grabs the operand
        String accumulatorValue; //String that will hold the accumulator value when needed
        String registerValue; //String that will hold the register value when needed
        String result; //String to hold results of operations
        System.out.println("Carry fCopylag: "+registers.getSR(CARRY));
        System.out.println("Zero flag: "+registers.getSR(ZERO));
        System.out.println("Negative flag: "+registers.getSR(NEGATIVE));
        System.out.println("Overflow flag: "+registers.getSR(OVERFLOW));
        int intopcode = Integer.parseInt(opcode, 2); //Parses the opcode into a digit in order to use it in the following Switch
        switch(intopcode){
            case 0: //AND
                System.out.println("Executing AND");
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.and(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 1: //OR
                System.out.println("Executing OR");
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.or(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 2: //XOR
                System.out.println("Executing XOR");
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.xor(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 3: //ADDC
                System.out.println("Executing ADDC");
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.addc(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 4: //SUB
                System.out.println("Executing SUB");
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.sub(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 5: //MUL
                System.out.println("Executing MUL");
                registerValue = registers.read(register);
                accumulatorValue = registers.getAccumulator();
                result = ALU.mul(accumulatorValue, registerValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 6: //NEG
                System.out.println("Executing NEG!!");
                accumulatorValue = registers.getAccumulator();
                result = ALU.neg(accumulatorValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 7: //NOT
                System.out.println("Executing NOT");
                accumulatorValue = registers.getAccumulator();
                result = ALU.not(accumulatorValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 8: //RLC
                System.out.println("Executing RLC");
                accumulatorValue = registers.getAccumulator();
                result = ALU.rlc(accumulatorValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 9: //RRC
                System.out.println("Executing RRC");
                accumulatorValue = registers.getAccumulator();
                result = ALU.rrc(accumulatorValue);
                registers.setAccumulator(result);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 10: //LDA rf
                System.out.println("Executing LDA RF");
                ldaRegister(register);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 11: //STA rf
                System.out.println("Executing STA");
                staRegister(register);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 12: //LDA addr
                System.out.println("Executing LDA ADDR");
                ldaAddress(operand);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 13: //STA addr
                System.out.println("Executing STA ADDR");
                staAddress(operand);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 14: //LDI Immediate
                System.out.println("Executing LDI IMMEDIATE");
                ldi(operand);
                System.out.println("Accumulator: "+registers.getAccumulator());
                break;
            case 16: //BRZ
                System.out.println("Executing BRZ");
                System.out.println("Accumulator: "+registers.getAccumulator());
                brz();
                break;
            case 17: //BRC
                System.out.println("Executing BRC");
                System.out.println("Accumulator: "+registers.getAccumulator());
                brc();
                break;
            case 18: //BRN
                System.out.println("Executing BRN");
                System.out.println("Accumulator: "+registers.getAccumulator());
                brn();
                break;
            case 19: //BRO
                System.out.println("Executing BRO");
                System.out.println("Accumulator: "+registers.getAccumulator());
                bro();
                break;
            case 31: //STOP
                System.out.println("Executing STOP");
                System.out.println("Accumulator: "+registers.getAccumulator());
                
                stopOperaton();
                break;
            case 24: //NOP
                System.out.println("Executing NOP");
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
        
        if(registerValue.charAt(0) == '1')
            registers.setSR(NEGATIVE, "1");
        else
            registers.setSR(NEGATIVE,"0");
        
        if(Integer.parseInt(registerValue, 2) == 0)
            registers.setSR(ZERO, "1");
        else
            registers.setSR(ZERO,"0");             
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
        if(intAddress == 250 || intAddress == 251) {//if the address is 250 or 251, they are keyboard inputs to be requested
            
            String var = JOptionPane.showInputDialog("enter a value");
            while (var == null)
                var = JOptionPane.showInputDialog("enter a value");
            char input = var.charAt(0);
            int temp = (int) input;
            String tmp1 = Integer.toBinaryString(temp);
            tmp1 = Tools.extendBinaryValue(8, tmp1);
            //userInput.setModalExclusionType(Dialog.ModalityType.);
            memory.setByte(address, tmp1);
        }
        //simply copy memory to accumulator
            String addressContent = memory.getByte(address); 
            registers.setAccumulator(addressContent);
            
            if(addressContent.charAt(0) == '1')
                registers.setSR(NEGATIVE, "1");
            else
                registers.setSR(NEGATIVE,"0");
        
            if(Integer.parseInt(addressContent, 2) == 0)
                registers.setSR(ZERO, "1");
            else
                registers.setSR(ZERO,"0");     
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
        if(immediate.charAt(0) == '1')
            registers.setSR(NEGATIVE, "1");
        else
            registers.setSR(NEGATIVE,"0");
        
        if(Integer.parseInt(immediate, 2) == 0)
            registers.setSR(ZERO, "1");
        else
            registers.setSR(ZERO,"0");        
        
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
    public void stopOperaton(){
        stopFlag = true;        
    }
    
    /**
     * Operates the simulator one instruction at a time.
     * Checks for previous stop instruction, sets the IR from the PC, and executes the instruction
     * If the stop instruction was executed previously, it reinitializes the simulator.
     */
    public void nextStep(){
        if(stopFlag == false){ //Checks if the previous instruction was STOP
            System.out.println("inside next step, stop is false and this is printing");
            registers.setIR(memory.getWord(registers.getPC())); //Gets the PC, uses it as an address to get the instruction Word from memory, and sets it as the next PC
            registers.incrementPC();
            executeInstruction();
            this.mSimView.updateGUI(registers, memory);
        }
        else{
            initialize();
            popUp.setErrorMessage("STOP instruction received, reinitializing simulator");
            popUp.showErrorDialog();
            System.out.println("STOP instruction received, reinitializing simulator"); //TODO:Implement this message in a popup window
        }
    }
    
    /**
     * Operates the emulator in Run mode. All the instructions in memory are run sequentially and without interruption (except for a keyboard prompt if present)
     */
    public void run(){
        do{
            nextStep();
        }      
        while(stopFlag == false);
        if(stopFlag){
            initialize();
            popUp.setErrorMessage("STOP instruction received, reinitializing simulator");
            popUp.showErrorDialog();         
        }
    }
    /**
     * Initializes the simulator: Copies instructions to memory and initializes the PC to address 00h
     */
    private void initialize(){
        instructionsToMemory();
        registers.setPC("00000000");
        stopFlag = false;
        
    }
    
}