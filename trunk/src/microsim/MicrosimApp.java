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
        launch(MicrosimApp.class, args);
        
        Memory test = new Memory();
        test.setContent("1111", "3");
        //System.out.println(test.getContent("3"));
        //System.out.println(test.getContent("F"));
        int num = Integer.parseInt("FF", 16);
        String bin = Integer.toBinaryString(num);
        //System.out.println(bin);
    }
}
