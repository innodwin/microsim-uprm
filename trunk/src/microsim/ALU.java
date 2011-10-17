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
    
    private static Registers registers = new Registers();
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
                and.concat("0");
            else
                and.concat("1");
			
            i++;
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
            if(accumulator.charAt(i) == '1' || register.charAt(i) == '1')
                or.concat("1");
            else
                or.concat("0");
            
            i++;
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
        String carry = registers.getSR(CARRY);
        
        int acc = Integer.parseInt(accumulator, 2);
	int reg = Integer.parseInt(register,2);
        int result = 0;
        String sum = "";
        
        if(Integer.signum(acc)==-1&&Integer.signum(reg)==-1){
            result = acc + reg;
            if(result==-256){
                registers.setSR(OVERFLOW, "1");
                if(registers.getSR(CARRY).equals("1"))
                    result++;
            }
            sum = Tools.byteSizedBinValue(Integer.toBinaryString(result));
        }else if(acc<reg){
            
        }else{
            result = acc + reg;
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
        int acc = Tools.signedBinToDec(accumulator);
	int reg = Tools.signedBinToDec(register);
        int result=0;
        String sub = "";
	
        if(Integer.signum(acc)==-1&&Integer.signum(reg)==1){
            result = acc - reg;
            if(result==-256){
                registers.setSR(OVERFLOW, "1");
            }
            sub = Tools.byteSizedBinValue(Integer.toBinaryString(result));
        }else if(acc<reg){
            registers.setSR(NEGATIVE, "1");
            
        }else{
            acc = acc - reg;
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
                xor.concat("0");
            else if(accumulator.charAt(i) == '0' && register.charAt(i) == '0')
		xor.concat("0");
            else
		xor.concat("1");
			
            i++;
	}
	
        return xor;
    }
    
    /**
     * 
     * @param accumulator
     * @return
     */
    public static String not(String accumulator)
    {
        int i = 0;
	String not = "";
		
	while(i > 8){
            if(accumulator.charAt(i) == '0')
                not.concat("1");
            else
                not.concat("0");
            i++;
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
        int i = 0;
	String neg = "";
		
	while(i > 8){
            if(accumulator.charAt(i) == '0')
                neg.concat("1");
            else
                neg.concat("0");
            i++;
	}
        
        Tools t = new Tools();
        int intNeg = Integer.parseInt(neg, 2);
        
        if(intNeg==256){
            intNeg=0;
            neg = Integer.toBinaryString(intNeg);
            neg = t.extendBinaryValue(8, neg);
            registers.setSR(OVERFLOW, "1");
        }else{
            intNeg++;
            neg = Integer.toBinaryString(intNeg);
            neg = t.extendBinaryValue(8, neg);
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
        String oldCarry = registers.getSR(CARRY);
        String newCarry = ""+accumulator.charAt(0);
        registers.setSR(CARRY, newCarry);
        accumulator = accumulator.substring(1, 8)+oldCarry;
        
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
        String oldCarry = registers.getSR(CARRY);
        String newCarry = ""+accumulator.charAt(7);
        registers.setSR(CARRY, newCarry);
        accumulator = oldCarry+accumulator.substring(0, 7);
        
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
       Tools t = new Tools();
       int tmp1 = t.binToDec(op1);
       int tmp2 = t.binToDec(op2);
       int result = tmp1*tmp2;
       System.out.println("op1: "+tmp1 + " op2: " + tmp2 + "result: " + result);
       String formattedResult = t.extendBinaryValue(8, Integer.toBinaryString(result));
       return formattedResult;
    }
}