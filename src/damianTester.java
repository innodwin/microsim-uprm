
import microsim.Tools;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author damian
 */
public class damianTester {
     public static void main(String[] args) {
         
         int whatever = -73;
         String x = Integer.toBinaryString(whatever);
         System.out.println(x);
         String y = Tools.byteSizedBinValue(x);
         System.out.println(y);
         System.out.println(Tools.signedBinToDec(y));
         
     }
}
