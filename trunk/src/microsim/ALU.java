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
    private static final int CARRY = 1;
    
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
    
    public static String addc(String accumulator, String register)
    {
        String carry = registers.getSR(CARRY);
        
        int acc = Long.valueOf(accumulator, 2).intValue();
	int reg = Long.valueOf(register, 2).intValue();
        
        if(carry.equals("1"))
            reg++;
		
        acc += reg;
	acc %= 255;
		
	Tools t = new Tools();
        
	return t.extendBinaryValue(8, Integer.toBinaryString(acc));
    }
    
    public static String sub(String accumulator, String register)
    {
        int acc = Long.valueOf(accumulator, 2).intValue();
	int reg = Long.valueOf(register, 2).intValue();
		
        acc -= reg;
	acc %= 255;
	
        Tools t = new Tools();
        
	return t.extendBinaryValue(8, Integer.toBinaryString(acc));
    }
    
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
    
    public static String neg(String accumulator)
    {
        String neg = "";
		
	if(accumulator.charAt(0) == '1')
            neg.concat("0");
        else
            neg.concat("1");
		
	neg.concat(accumulator.substring(1, accumulator.length()));
		
	return neg;
    }
    
    public static String rlc(String accumulator)
    {
        String oldCarry = registers.getSR(CARRY);
        String newCarry = ""+accumulator.charAt(0);
        registers.setSR(CARRY, newCarry);
        accumulator = accumulator.substring(1, 8)+oldCarry;
        
        return accumulator;
    }
    
    public static String rrc(String accumulator)
    {
        String oldCarry = registers.getSR(CARRY);
        String newCarry = ""+accumulator.charAt(7);
        registers.setSR(CARRY, newCarry);
        accumulator = oldCarry+accumulator.substring(0, 7);
        
        return accumulator;
    }
    
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