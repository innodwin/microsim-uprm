/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

/**
 *
 * @author damian
 */
public class ALU {
    
    private static final int ZERO = 0;
    private static final int CARRY = 1;
    private static final int NEGATIVE = 2;
    private static final int OVERFLOW = 3;
    
    /**
     * Logical AND operator. Gets from the instruction the register that holds the value
     * that is going to be ANDed with the value of the accumulator register
     * @param accumulator the value that holds the accumulator register
     * @param register the value that holds the register
     * @return the Logical AND result
     */
    public static String and(String accumulator, String register)
    {
        int i = 0;
	String and = "";
	
        while (i < 8){
            if(accumulator.charAt(i) == '0' || register.charAt(i) == '0')
                and = and.concat("0");
            else
                and = and.concat("1");
			
            i++;
	}
        
        ControlUnit.registers.setSR(CARRY, "0");
        ControlUnit.registers.setSR(OVERFLOW, "0");
        
        if(Integer.parseInt(and, 2)==0){
            ControlUnit.registers.setSR(ZERO, "1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
	
        if(and.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
	
        return and;
    }
    
    /**
     * Logical OR operator. Gets from the instruction the register that holds the value
     * that is going to be ORed with the value of the accumulator register
     * @param accumulator the value that holds the accumulator register
     * @param register the value that holds the register
     * @return the Logical OR result
     */
    public static String or(String accumulator, String register)
    {
        int i = 0;
        String or = "";
        
        while (i < 8){
            if(accumulator.charAt(i) == '0' && register.charAt(i) == '0')
                or = or.concat("0");
            else
                or = or.concat("1");
            
            i++;
	}
        
        ControlUnit.registers.setSR(CARRY, "0");
        ControlUnit.registers.setSR(OVERFLOW, "0");
        
        if(Integer.parseInt(or, 2)==0){
            ControlUnit.registers.setSR(ZERO, "1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
	
        if(or.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
        
        return or;
    }
    
    /**
     * ADD with Carry operator. Gets from the instruction the register that holds the value
     * that is going to be ADDED with the value of the accumulator register. In addition it
     * adds the carry flag to the end result
     * @param accumulator the value that holds the accumulator register
     * @param register the value that holds the register
     * @return the ADD with Carry result
     */
    public static String addc(String accumulator, String register)
    {
        String sum = "";
      
        int i=7;
        
        while (i >= 0){
            if(accumulator.charAt(i) == '1' && register.charAt(i) == '1' && ControlUnit.registers.getSR(CARRY).equals("1")){
                sum = "1"+sum;
                ControlUnit.registers.setSR(CARRY, "1");
            }else if(accumulator.charAt(i) == '1' && register.charAt(i) == '0'&& ControlUnit.registers.getSR(CARRY).equals("1")){
                sum = "0"+sum;
                ControlUnit.registers.setSR(CARRY, "1");
            }else if(accumulator.charAt(i) == '0' && register.charAt(i) == '1'&& ControlUnit.registers.getSR(CARRY).equals("1")){
                sum = "0"+sum;
                ControlUnit.registers.setSR(CARRY, "1");
            }else if(accumulator.charAt(i) == '0' && register.charAt(i) == '0'&& ControlUnit.registers.getSR(CARRY).equals("1")){
                sum = "1"+sum;
                ControlUnit.registers.setSR(CARRY, "0");
            }else if(accumulator.charAt(i) == '1' && register.charAt(i) == '1'&& ControlUnit.registers.getSR(CARRY).equals("0")){
                sum = "0"+sum;
                ControlUnit.registers.setSR(CARRY, "1");
            }else if(accumulator.charAt(i) == '1' && register.charAt(i) == '0'&& ControlUnit.registers.getSR(CARRY).equals("0")){
                sum = "1"+sum;
                ControlUnit.registers.setSR(CARRY, "0");
            }else if(accumulator.charAt(i) == '0' && register.charAt(i) == '1'&& ControlUnit.registers.getSR(CARRY).equals("0")){
                sum = "1"+sum;
                ControlUnit.registers.setSR(CARRY, "0");
            }else if(accumulator.charAt(i) == '0' && register.charAt(i) == '0'&& ControlUnit.registers.getSR(CARRY).equals("0")){
                sum = "0"+sum;
                ControlUnit.registers.setSR(CARRY, "0");
            }
            
            i--;
	}
        
        if(sum.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
        
        if(Integer.parseInt(sum, 2)==0){
            ControlUnit.registers.setSR(ZERO,"1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
        
        if(Integer.parseInt(sum, 2)==0 && ControlUnit.registers.getSR(CARRY).equals("1")){
            ControlUnit.registers.setSR(OVERFLOW, "1");
        }else{
            ControlUnit.registers.setSR(OVERFLOW, "0");
        }

        return sum;
    }
    
    /**
     * Subtraction operator. Gets from the instruction the register that holds the value
     * that is going to be subtracted with the value of the accumulator register
     * @param accumulator the value that holds the accumulator register
     * @param register the value that holds the register
     * @return the Subtraction result
     */
    public static String sub(String accumulator, String register)
    {
        String regNeg = ALU.neg(register);
        String sub = ALU.addc(accumulator, regNeg);
        
        if(sub.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
        
        if(Integer.parseInt(sub, 2)==0){
            ControlUnit.registers.setSR(ZERO,"1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
        
        if(Integer.parseInt(sub, 2)==0 && ControlUnit.registers.getSR(CARRY).equals("1")){
            ControlUnit.registers.setSR(OVERFLOW, "1");
        }else{
            ControlUnit.registers.setSR(OVERFLOW, "0");
        }
        
	return sub;
    }
    
    /**
     * Logical XOR operator. Gets from the instruction the register that holds the value
     * that is going to be XORed with the value of the accumulator register
     * @param accumulator the value that holds the accumulator register
     * @param register the value that holds the register
     * @return the Logical XOR result
     */
    public static String xor(String accumulator, String register)
    {
        int i = 0;
        String xor = "";
	
        while (i < 8){
            if(accumulator.charAt(i) == '1' && register.charAt(i) == '1')
                xor = xor.concat("0");
            else if(accumulator.charAt(i) == '0' && register.charAt(i) == '0')
		xor = xor.concat("0");
            else
		xor = xor.concat("1");
			
            i++;
	}
        
        ControlUnit.registers.setSR(CARRY, "0");
        ControlUnit.registers.setSR(OVERFLOW, "0");
        
        if(Integer.parseInt(xor, 2)==0){
            ControlUnit.registers.setSR(ZERO, "1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
	
        if(xor.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
	
        return xor;
    }
    
    /**
     * Logical NOT operator. Takes the value stored in the accumulator and applies
     * the NOT operation.
     * @param accumulator has the value to be modified
     * @return the modified result
     */
    public static String not(String accumulator)
    {
        int i = 0;
	String not = "";

	while(i < 8){
            if(accumulator.charAt(i) == '0')
                not = not.concat("1");
            else
                not = not.concat("0");
            i++;
	}

        ControlUnit.registers.setSR(CARRY, "0");
        ControlUnit.registers.setSR(OVERFLOW, "0");
        
        if(Integer.parseInt(not, 2)==0){
            ControlUnit.registers.setSR(ZERO, "1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
	
        if(not.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
		
	return not;
    }
    
    /**
     * Right Rotate with Carry operator. It shifts to the right the bits of the
     * value that holds the accumulator. It also puts the value of the carry flag
     * in the most significant bit and the less significant bit to the carry flag
     * @param accumulator the value that holds the accumulator register
     * @return the result after the Right Rotate with Carry
     */
    public static String neg(String accumulator)
    {
        String not = ALU.not(accumulator);
        String one = "00000001";
        ControlUnit.registers.setSR(CARRY, "0");
        String neg = ALU.addc(not, one);
        
        if(Integer.parseInt(neg, 2)==0 && ControlUnit.registers.getSR(CARRY).equals("1")){
            ControlUnit.registers.setSR(OVERFLOW, "1");
        }else{
            ControlUnit.registers.setSR(OVERFLOW, "0");
        }
        
        if(Integer.parseInt(neg, 2)==0){
            ControlUnit.registers.setSR(ZERO, "1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
        
        if(neg.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
        
	return neg;
    }
    
    /**
     * Rotate Left with Carry operator. It shifts to the left the bits of the
     * value that holds the accumulator. It also puts the value of the carry flag
     * in the less significant bit and the most significant bit to the carry flag
     * @param accumulator the value that holds the accumulator register
     * @return the result after the Rotate Left with Carry
     */
    public static String rlc(String accumulator)
    {
        String oldCarry = ControlUnit.registers.getSR(CARRY);
        String newCarry = ""+accumulator.charAt(0);
        ControlUnit.registers.setSR(CARRY, newCarry);
        accumulator = accumulator.substring(1, 8)+oldCarry;
        
        ControlUnit.registers.setSR(OVERFLOW, "0");
        
        if(Integer.parseInt(accumulator, 2)==0){
            ControlUnit.registers.setSR(ZERO, "1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
	
        if(accumulator.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
        
        return accumulator;
    }
    
    /**
     * Rotate Right with Carry operator. It shifts to the right the bits of the
     * value that holds the accumulator. It also puts the value of the carry flag
     * in the most significant bit and the less significant bit to the carry flag
     * @param accumulator the value that holds the accumulator register
     * @return the result after the Rotate Right with Carry
     */
    public static String rrc(String accumulator)
    {
        String oldCarry = ControlUnit.registers.getSR(CARRY);
        String newCarry = ""+accumulator.charAt(7);
        ControlUnit.registers.setSR(CARRY, newCarry);
        accumulator = oldCarry+accumulator.substring(0, 7);

        ControlUnit.registers.setSR(OVERFLOW, "0");
        
        if(Integer.parseInt(accumulator, 2)==0){
            ControlUnit.registers.setSR(ZERO, "1");
        }else{
            ControlUnit.registers.setSR(ZERO, "0");
        }
	
        if(accumulator.charAt(0)=='1'){
            ControlUnit.registers.setSR(NEGATIVE, "1");
        }else{
            ControlUnit.registers.setSR(NEGATIVE, "0");
        }
        
        return accumulator;
    }
    
    /**
     * Multiplication operator. Gets from the instruction the register that holds the value
     * that is going to be multiplied with the value of the accumulator register
     * @param accumulator the value that holds the accumulator register
     * @param register the value that holds the register
     * @return the result after the Multiplication
     */
    public static String mul(String accumulator, String register)
    {
       String op1 = accumulator.substring(4, 8);
       String op2 = register.substring(4,8);
       int tmp1 = Integer.parseInt(op1, 2);
       int tmp2 = Integer.parseInt(op2, 2);
       int result = tmp1*tmp2;
       String mul = Tools.extendBinaryValue(8, Integer.toBinaryString(result));
       
       ControlUnit.registers.setSR(OVERFLOW, "0");
       ControlUnit.registers.setSR(CARRY, "0");
       
       if(mul.charAt(0)=='1'){
           ControlUnit.registers.setSR(NEGATIVE, "1");
       }else{
           ControlUnit.registers.setSR(NEGATIVE, "0");
       }
       
       if(Integer.parseInt(mul, 2)==0){
           ControlUnit.registers.setSR(ZERO, "1");
       }else{
           ControlUnit.registers.setSR(ZERO, "0");
       }
       
       return mul;
    }
}