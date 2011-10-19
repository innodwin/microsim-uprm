/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

/**
 *
 * @author Luis Rosario
 */
public class Tools {
    private final int ERROR = -1300;
    /**
     * Tests if a String value if in binary form.
     * @param input
     * @return True if the value is value is binary, else false.
     */
    public boolean isBinary(String input) {
 
         boolean check = true;
         
         for(int i=0; i < input.length(); i++){
                if(input.charAt(i) == '0' || input.charAt(i) == '1'){
                    continue;
                }
                else {
                   check = false;
                   break;
                }
         }
         return check;
}
  
  /**
   * put the leading zeros to a value to match the size especified.
   * @param size
   * @param value
   * @return the String with the correct size of bits.
   */
  public static String extendBinaryValue(int size,String value){
      int wordSize = value.length();
      String strToReturn = "";
      String sign ="0";//value.charAt(0);
      
      while(wordSize != size)
      {
          strToReturn = sign+strToReturn;
          wordSize++;
      }
      
      strToReturn += value;
      
      return strToReturn;  
  }
  
  /**
   * Change a binary value to a decimal representation.
   * @param value
   * @return an integer that contains the decimal representation of the value
   * provided.
   */
  public int binToDec(String value)
  {
      int valToReturn;
      try{
           valToReturn = Integer.parseInt(value,2);
      }catch(Exception e){
          return ERROR;
      }
      
      return valToReturn;
  }
  
  /**
   * 
   * @param binNum
   * @return
   */
  public static int signedBinToDec(String binNum){
      
      int intNum = 0;
      
      if(binNum.charAt(0)=='1'&&binNum.length()==8){
          intNum = Integer.parseInt(binNum.substring(1, 8),2)-128;
      }
      else
          intNum = Integer.parseInt(binNum,2);
      
      return intNum;
      
  }
  
  /**
   * 
   * @param binNum
   * @return
   */
  public static String byteSizedBinValue(String binNum){
      return binNum.substring(binNum.length()-8, binNum.length());
  }
  
  public static String binToHex(String value){
      int i= Integer.parseInt(value,2);
      return Integer.toHexString(i);
  }


    
}
