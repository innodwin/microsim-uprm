/*
 * MicrosimApp.java
 */

package microsim;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class MicrosimApp extends SingleFrameApplication {
    public static final int ZERO = 0;
    public static final int CARRY = 1;
    public static final int NEGATIVE = 2;
    public static final int OVERFLOW = 3;
    public static final String R1 = "000";
    public static final String R2 = "001";
    public static final String R3 = "010";
    public static final String R4 = "011";
    public static final String R5 = "100";
    public static final String R6 = "101";
    public static final String R7 = "110";
    public static final String R8 = "111";



    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new MicrosimView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of MicrosimApp
     */
    public static MicrosimApp getApplication() {
        return Application.getInstance(MicrosimApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        //launch(MicrosimApp.class, args);
        Registers r = new Registers();
        IO io = new IO();
        ALU alu = new ALU();
        Memory test = new Memory();
        test.setContent("1111", "3");
        //System.out.println(test.getContent("3"));
        //System.out.println(test.getContent("F"));
        int num = Integer.parseInt("FF", 16);
        String bin = Integer.toBinaryString(num);
        //System.out.println(bin);
    }
}
