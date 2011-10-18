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
        System.out.println("Executing AND");
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
        System.out.println("Executing OR");
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
        System.out.println("Executing ADDC");
        int acc = Tools.signedBinToDec(accumulator);
        int reg = Tools.signedBinToDec(register);
        int result = 0;
        String sum = "";
        result = acc + reg;
        
        if(result==-255||result==255){
            
              if(ControlUnit.registers.getSR(CARRY).equals("1")){
                        result++;
                        ControlUnit.registers.setSR(OVERFLOW, "1");
                        ControlUnit.registers.setSR(ZERO, "1");
              }
            
              if(Integer.signum(result)==-1){
                    ControlUnit.registers.setSR(NEGATIVE,"1");
              }
            
              sum = Tools.byteSizedBinValue(Integer.toBinaryString(result));
            
        }else if(result==-256||result==256){
            
              ControlUnit.registers.setSR(OVERFLOW, "1");

              if(ControlUnit.registers.getSR(CARRY).equals("1")){
                        result++;
              }else{
                        ControlUnit.registers.setSR(CARRY,"1");
                        ControlUnit.registers.setSR(ZERO, "1");
              }
            
              if(Integer.signum(result)==-1){
                    ControlUnit.registers.setSR(NEGATIVE,"1");
              }

              sum = Tools.byteSizedBinValue(Integer.toBinaryString(result));

        }else{
              
              if(ControlUnit.registers.getSR(CARRY).equals("1")){
                      result++;
              }
              
              if(result==0){
                      ControlUnit.registers.setSR(ZERO,"1");
              }
              
              if(Integer.signum(result)==-1){
                      ControlUnit.registers.setSR(NEGATIVE,"1");
              }
              
              sum = Tools.extendBinaryValue(8,Integer.toBinaryString(result));
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
        System.out.println("Executing SUB");
        int acc = Tools.signedBinToDec(accumulator);
        int reg = Tools.signedBinToDec(register);
        int result=0;
        String sub = "";
        result = acc - reg;
        if(result==-256){
            
            ControlUnit.registers.setSR(OVERFLOW, "1");
            ControlUnit.registers.setSR(CARRY, "1");
            ControlUnit.registers.setSR(ZERO, "1");
            ControlUnit.registers.setSR(NEGATIVE, "1");
            sub = Tools.byteSizedBinValue(Integer.toBinaryString(result));
            
        }else{
            
            if(Integer.signum(result)==-1){
                  ControlUnit.registers.setSR(NEGATIVE, "1");
            }else if(result==0){
                  ControlUnit.registers.setSR(ZERO, "1");
            }
            
            sub = Tools.extendBinaryValue(8,Integer.toBinaryString(result));
        
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
        System.out.println("Executing XOR");
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
     * Logical NOT operator. Takes the value stored in the accumulator and applies
     * the NOT operation.
     * @param accumulator has the value to be modified
     * @return the modified result
     */
    public static String not(String accumulator)
    {
        System.out.println("Executing NOT");
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
        System.out.println(accumulator+"!!!!!!!!!!!!!");
        int i = 0;
        String neg = "";
		
        while(i < 8){
            if(accumulator.charAt(i) == '0')
                neg+="1";
            else
                neg+="0";
            i++;
        }
        
        int intNeg = Tools.signedBinToDec(neg);
        
        if(intNeg==255){
            
            intNeg++;
            ControlUnit.registers.setSR(OVERFLOW, "1");
            ControlUnit.registers.setSR(CARRY, "1");
            ControlUnit.registers.setSR(ZERO, "1");
            ControlUnit.registers.setSR(NEGATIVE, "1");
            neg = Tools.byteSizedBinValue(Integer.toBinaryString(intNeg));
            
        }else{
            
            intNeg++;
            if(Integer.signum(intNeg)==-1){
                  neg = Tools.byteSizedBinValue(Integer.toBinaryString(intNeg));
                  ControlUnit.registers.setSR(NEGATIVE, "1");
            }else
                  neg = Tools.extendBinaryValue(8,Integer.toBinaryString(intNeg));
                  
        }
	System.out.println("This is neg: " + neg);	
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
        System.out.println("Executing RLC");
        String oldCarry = ControlUnit.registers.getSR(CARRY);
        String newCarry = ""+accumulator.charAt(0);
        ControlUnit.registers.setSR(CARRY, newCarry);
        accumulator = accumulator.substring(1, 8)+oldCarry;
        int intNum = Tools.signedBinToDec(accumulator);
        if(Integer.signum(intNum)==-1)
              ControlUnit.registers.setSR(NEGATIVE,"1");
        else if(intNum==0)
              ControlUnit.registers.setSR(ZERO,"1");
        
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
        System.out.println("Executing RRC");
        String oldCarry = ControlUnit.registers.getSR(CARRY);
        String newCarry = ""+accumulator.charAt(7);
        ControlUnit.registers.setSR(CARRY, newCarry);
        accumulator = oldCarry+accumulator.substring(0, 7);
        int intNum = Tools.signedBinToDec(accumulator);

        if(Integer.signum(intNum)==-1)
              ControlUnit.registers.setSR(NEGATIVE,"1");
        else if(intNum==0)
              ControlUnit.registers.setSR(ZERO,"1");
        
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
       System.out.println("Executing MUL");
       String op1 = accumulator.substring(4, 8);
       String op2 = register.substring(4,8);
       Tools t = new Tools();
       int tmp1 = t.binToDec(op1);
       int tmp2 = t.binToDec(op2);
       int result = tmp1*tmp2;
       if(result==0)
             ControlUnit.registers.setSR(ZERO,"1");
       String formattedResult = t.extendBinaryValue(8, Integer.toBinaryString(result));
       return formattedResult;
    }
}