/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OpenFileDialog.java
 *
 * Created on Oct 15, 2011, 12:25:52 PM
 */
package microsim;

import java.io.File;
import javax.swing.JFileChooser;
import org.jdesktop.application.Action;

/**
 *
 * @author luillo
 */
public class OpenFileDialog extends javax.swing.JFrame {

    private File file = null;
    /** Creates new form OpenFileDialog */
    public OpenFileDialog() {
        initComponents();
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(microsim.MicrosimApp.class).getContext().getResourceMap(OpenFileDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBounds(new java.awt.Rectangle(500, 300, 0, 0));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(406, 200));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(microsim.MicrosimApp.class).getContext().getActionMap(OpenFileDialog.class, this);
        jButton1.setAction(actionMap.get("ok")); // NOI18N
        jButton1.setName("okButton"); // NOI18N

        jButton2.setAction(actionMap.get("Cancel")); // NOI18N
        jButton2.setName("cancelButton"); // NOI18N

        jButton3.setAction(actionMap.get("select")); // NOI18N
        jButton3.setText(resourceMap.getString("selectLocation.text")); // NOI18N
        jButton3.setName("selectLocation"); // NOI18N

        jTextField1.setText(resourceMap.getString("fileLocation.text")); // NOI18N
        jTextField1.setName("fileLocation"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(33, 33, 33)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(101, 101, 101)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(86, 86, 86)))
                .add(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(27, 27, 27)
                .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(48, 48, 48))
        );

        jButton3.getAccessibleContext().setAccessibleName(resourceMap.getString("selectLocation.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /**
     * 
     */
    @Action
    public void ok() {
        this.setVisible(false);
    }

    /**
     * Sets the file value to null and hides the dialog box.
     */
    @Action
    public void Cancel() {
        this.setVisible(false);
        this.file = null;
    }

    /**
     * When the user presses the select button, It shows
     * a file chooser window and the user selects the file.
     * It also sets the file text file to the file selected.
     */
    @Action
    public void select() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        this.file = chooser.getSelectedFile();
        this.jTextField1.setText(file.getName());
    }
    
    /**
     * 
     * @return the file selected by the user.
     */
    public File getFile(){
        return file;
    }
    
    /**
     * Delay to wait for the input of the user.
     */
    public void waitForInput(){
        while(file == null){}
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
