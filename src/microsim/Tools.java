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

    
}
