package microsim;

/**
 * InstructionParser is used as a toolset to manipulate Strings representing
 * instructions in either hexadecimal or binary formats.
 * @author Damian Esteves
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
     * Extracts the 5 Opcode bits from a binary instruction String
     * @param binaryInstruction The full 16-bit instruction in a binary String
     * @return The 5 Opcode bits in a binary String
     */
    public static String opcode(String binaryInstruction){        
        String opcode = binaryInstruction.substring(0, 5);
        return opcode;
    }
    
    /**
     * Extracts the 3 Register bits from a binary instruction String.
     * @param binaryInstruction The full 16-bit instruction in a binary String
     * @return the 3 Register bits in a binary String
     */
    public static String register(String binaryInstruction){
        String register = binaryInstruction.substring(5, 8);
        return register;
    }
    
    /**
     * Extracts the 8 Operand bits from from a binary instruction String
     * @param binaryInstruction The full 16-bit instruction in a binary String
     * @return The 8 Operand bits in a binary String
     */
    public static String operand(String binaryInstruction){
        String operand = binaryInstruction.substring(8, 16);
        return operand;
    }
}
