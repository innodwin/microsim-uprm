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
      char sign = value.charAt(0);
      
      while(wordSize != size)
      {
          strToReturn += sign;
          wordSize++;
      }
      
      strToReturn += value;
      
      return strToReturn;
      
      
  }


    
}
