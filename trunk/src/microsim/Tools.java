/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package microsim;

/**
 *
 * @author luillo
 */
public class Tools {
    private final int ERROR = -1300;
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
  
  public String extendBinaryValue(int size,String value){
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
  
  public static int signedBinToDec(String binNum){
      
      int intNum = 0;
      
      if(binNum.charAt(0)=='1')
          intNum = Integer.parseInt(binNum.substring(1, 8),2)-128;
      else
          intNum = Integer.parseInt(binNum,2);
      
      return intNum;
      
  }
  
  public static String byteSizedBinValue(String binNum){
      return binNum.substring(binNum.length()-8, binNum.length());
  }


    
}
