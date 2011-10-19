/*
 * MicrosimView.java
 */

package microsim;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class MicrosimView extends FrameView {
    public File file = null;
    ControlUnit cu = null;
    private static final String R0 = "000";
    private static final String R1 = "001";
    private static final String R2 = "010";
    private static final String R3 = "011";
    private static final String R4 = "100";
    private static final String R5 = "101";
    private static final String R6 = "110";
    private static final String R7 = "111";
    public MessageBox mBox = new MessageBox();
    public MicrosimView(SingleFrameApplication app) {
        super(app);

        initComponents();
     
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = MicrosimApp.getApplication().getMainFrame();
            aboutBox = new MicrosimAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        MicrosimApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        IRField = new javax.swing.JTextField();
        PCField = new javax.swing.JTextField();
        AField = new javax.swing.JTextField();
        SRField = new javax.swing.JTextField();
        R0Field = new javax.swing.JTextField();
        R1Field = new javax.swing.JTextField();
        R2Field = new javax.swing.JTextField();
        R3Field = new javax.swing.JTextField();
        R4Field = new javax.swing.JTextField();
        R5Field = new javax.swing.JTextField();
        R6Field = new javax.swing.JTextField();
        R7Field = new javax.swing.JTextField();
        KeyboardField = new javax.swing.JTextField();
        DisplayField = new javax.swing.JTextField();
        IRLabel = new javax.swing.JLabel();
        PCLabel = new javax.swing.JLabel();
        ALabel = new javax.swing.JLabel();
        SRLabel = new javax.swing.JLabel();
        KeyboardLabel = new javax.swing.JLabel();
        DisplayLabel = new javax.swing.JLabel();
        MemoryLabel = new javax.swing.JLabel();
        R0Label = new javax.swing.JLabel();
        R1Label = new javax.swing.JLabel();
        R2Label = new javax.swing.JLabel();
        R3Label = new javax.swing.JLabel();
        R4Label = new javax.swing.JLabel();
        R5Label = new javax.swing.JLabel();
        R6Label = new javax.swing.JLabel();
        R7Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MemoryList = new javax.swing.JList();
        stepButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(325, 338));

        IRField.setEditable(false);
        IRField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IRField.setMaximumSize(new java.awt.Dimension(16, 1));
        IRField.setMinimumSize(new java.awt.Dimension(16, 1));
        IRField.setName("IRField"); // NOI18N
        IRField.setPreferredSize(new java.awt.Dimension(16, 1));

        PCField.setEditable(false);
        PCField.setMaximumSize(new java.awt.Dimension(8, 1));
        PCField.setMinimumSize(new java.awt.Dimension(8, 1));
        PCField.setName("PCField"); // NOI18N
        PCField.setPreferredSize(new java.awt.Dimension(8, 1));

        AField.setEditable(false);
        AField.setMaximumSize(new java.awt.Dimension(8, 1));
        AField.setMinimumSize(new java.awt.Dimension(8, 1));
        AField.setName("AField"); // NOI18N
        AField.setPreferredSize(new java.awt.Dimension(8, 1));

        SRField.setEditable(false);
        SRField.setMaximumSize(new java.awt.Dimension(4, 1));
        SRField.setMinimumSize(new java.awt.Dimension(4, 1));
        SRField.setName("SRField"); // NOI18N
        SRField.setPreferredSize(new java.awt.Dimension(4, 1));

        R0Field.setEditable(false);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(microsim.MicrosimApp.class).getContext().getResourceMap(MicrosimView.class);
        R0Field.setFont(resourceMap.getFont("R0Field.font")); // NOI18N
        R0Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        R0Field.setMaximumSize(new java.awt.Dimension(8, 1));
        R0Field.setMinimumSize(new java.awt.Dimension(8, 1));
        R0Field.setName("R0Field"); // NOI18N
        R0Field.setPreferredSize(new java.awt.Dimension(8, 1));

        R1Field.setEditable(false);
        R1Field.setFont(resourceMap.getFont("R1Field.font")); // NOI18N
        R1Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        R1Field.setMaximumSize(new java.awt.Dimension(8, 1));
        R1Field.setMinimumSize(new java.awt.Dimension(8, 1));
        R1Field.setName("R1Field"); // NOI18N
        R1Field.setPreferredSize(new java.awt.Dimension(8, 1));

        R2Field.setEditable(false);
        R2Field.setFont(resourceMap.getFont("R2Field.font")); // NOI18N
        R2Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        R2Field.setMaximumSize(new java.awt.Dimension(8, 1));
        R2Field.setMinimumSize(new java.awt.Dimension(8, 1));
        R2Field.setName("R2Field"); // NOI18N
        R2Field.setPreferredSize(new java.awt.Dimension(8, 1));

        R3Field.setEditable(false);
        R3Field.setFont(resourceMap.getFont("R3Field.font")); // NOI18N
        R3Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        R3Field.setMaximumSize(new java.awt.Dimension(8, 1));
        R3Field.setMinimumSize(new java.awt.Dimension(8, 1));
        R3Field.setName("R3Field"); // NOI18N
        R3Field.setPreferredSize(new java.awt.Dimension(8, 1));

        R4Field.setEditable(false);
        R4Field.setFont(resourceMap.getFont("R4Field.font")); // NOI18N
        R4Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        R4Field.setMaximumSize(new java.awt.Dimension(8, 1));
        R4Field.setMinimumSize(new java.awt.Dimension(8, 1));
        R4Field.setName("R4Field"); // NOI18N
        R4Field.setPreferredSize(new java.awt.Dimension(8, 1));

        R5Field.setEditable(false);
        R5Field.setFont(resourceMap.getFont("R5Field.font")); // NOI18N
        R5Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        R5Field.setMaximumSize(new java.awt.Dimension(8, 1));
        R5Field.setMinimumSize(new java.awt.Dimension(8, 1));
        R5Field.setName("R5Field"); // NOI18N
        R5Field.setPreferredSize(new java.awt.Dimension(8, 1));

        R6Field.setEditable(false);
        R6Field.setFont(resourceMap.getFont("R6Field.font")); // NOI18N
        R6Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        R6Field.setMaximumSize(new java.awt.Dimension(8, 1));
        R6Field.setMinimumSize(new java.awt.Dimension(8, 1));
        R6Field.setName("R6Field"); // NOI18N
        R6Field.setPreferredSize(new java.awt.Dimension(8, 1));

        R7Field.setEditable(false);
        R7Field.setFont(resourceMap.getFont("R7Field.font")); // NOI18N
        R7Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        R7Field.setMaximumSize(new java.awt.Dimension(8, 1));
        R7Field.setMinimumSize(new java.awt.Dimension(8, 1));
        R7Field.setName("R7Field"); // NOI18N
        R7Field.setPreferredSize(new java.awt.Dimension(8, 1));

        KeyboardField.setEditable(false);
        KeyboardField.setMaximumSize(new java.awt.Dimension(1, 1));
        KeyboardField.setMinimumSize(new java.awt.Dimension(1, 1));
        KeyboardField.setName("KeyboardField"); // NOI18N
        KeyboardField.setPreferredSize(new java.awt.Dimension(1, 1));

        DisplayField.setEditable(false);
        DisplayField.setMaximumSize(new java.awt.Dimension(4, 1));
        DisplayField.setMinimumSize(new java.awt.Dimension(4, 1));
        DisplayField.setName("DisplayField"); // NOI18N
        DisplayField.setPreferredSize(new java.awt.Dimension(4, 1));

        IRLabel.setText(resourceMap.getString("IRLabel.text")); // NOI18N
        IRLabel.setName("IRLabel"); // NOI18N

        PCLabel.setText(resourceMap.getString("PCLabel.text")); // NOI18N
        PCLabel.setName("PCLabel"); // NOI18N

        ALabel.setText(resourceMap.getString("ALabel.text")); // NOI18N
        ALabel.setName("ALabel"); // NOI18N

        SRLabel.setText(resourceMap.getString("SRLabel.text")); // NOI18N
        SRLabel.setName("SRLabel"); // NOI18N

        KeyboardLabel.setText(resourceMap.getString("KeyboardLabel.text")); // NOI18N
        KeyboardLabel.setName("KeyboardLabel"); // NOI18N

        DisplayLabel.setText(resourceMap.getString("DisplayLabel.text")); // NOI18N
        DisplayLabel.setName("DisplayLabel"); // NOI18N

        MemoryLabel.setText(resourceMap.getString("MemoryLabel.text")); // NOI18N
        MemoryLabel.setName("MemoryLabel"); // NOI18N

        R0Label.setText(resourceMap.getString("R0Label.text")); // NOI18N
        R0Label.setName("R0Label"); // NOI18N

        R1Label.setText(resourceMap.getString("R1Label.text")); // NOI18N
        R1Label.setName("R1Label"); // NOI18N

        R2Label.setText(resourceMap.getString("R2Label.text")); // NOI18N
        R2Label.setName("R2Label"); // NOI18N

        R3Label.setText(resourceMap.getString("R3Label.text")); // NOI18N
        R3Label.setName("R3Label"); // NOI18N

        R4Label.setText(resourceMap.getString("R4Label.text")); // NOI18N
        R4Label.setName("R4Label"); // NOI18N

        R5Label.setText(resourceMap.getString("R5Label.text")); // NOI18N
        R5Label.setName("R5Label"); // NOI18N

        R6Label.setText(resourceMap.getString("R6Label.text")); // NOI18N
        R6Label.setName("R6Label"); // NOI18N

        R7Label.setText(resourceMap.getString("R7Label.text")); // NOI18N
        R7Label.setName("R7Label"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        MemoryList.setName("MemoryList"); // NOI18N
        jScrollPane1.setViewportView(MemoryList);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(microsim.MicrosimApp.class).getContext().getActionMap(MicrosimView.class, this);
        stepButton.setAction(actionMap.get("step")); // NOI18N
        stepButton.setText(resourceMap.getString("stepButton.text")); // NOI18N
        stepButton.setName("stepButton"); // NOI18N

        runButton.setAction(actionMap.get("run")); // NOI18N
        runButton.setText(resourceMap.getString("runButton.text")); // NOI18N
        runButton.setName("runButton"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(IRLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IRField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PCLabel)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(PCField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addComponent(ALabel)
                                    .addGap(16, 16, 16)
                                    .addComponent(AField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(R7Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(R7Field, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(R6Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(R6Field, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(R5Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(R5Field, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(R4Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(R4Field, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(R3Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(R3Field, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(R2Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(R2Field, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(R1Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(R1Field, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(R0Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(R0Field, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(DisplayLabel)
                                    .addComponent(MemoryLabel)
                                    .addComponent(SRLabel)))
                            .addComponent(KeyboardLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(DisplayField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(KeyboardField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SRField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(stepButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(runButton)))
                .addGap(398, 398, 398))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IRLabel)
                            .addComponent(IRField, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PCLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ALabel))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SRLabel)
                            .addComponent(SRField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PCField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KeyboardLabel)
                            .addComponent(KeyboardField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DisplayLabel)
                            .addComponent(DisplayField, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MemoryLabel)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(R0Label)
                                    .addComponent(R0Field, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(R1Label)
                                    .addComponent(R1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(R2Label)
                            .addComponent(R2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(R3Label)
                            .addComponent(R3Field, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(R4Label)
                            .addComponent(R4Field, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(R5Label)
                            .addComponent(R5Field, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(R6Label)
                            .addComponent(R6Field, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(R7Label)
                            .addComponent(R7Field, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepButton)
                    .addComponent(runButton))
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        openMenuItem.setAction(actionMap.get("openFile")); // NOI18N
        openMenuItem.setText(resourceMap.getString("openMenuItem.text")); // NOI18N
        openMenuItem.setName("openMenuItem"); // NOI18N
        fileMenu.add(openMenuItem);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(chooser);
        this.file = chooser.getSelectedFile();
         if (file == null){
            mBox.setErrorMessage("Please Select a File first.");
            mBox.showErrorDialog();
         }
            else{
            cu = null;
            try {
                 cu = new ControlUnit(file,this);
            } catch (Exception ex) {
                mBox.setErrorMessage("Error Reading File. Select a new\n file and try again");
                return;
            }
        }
    }

    @Action
    public void run() {
        if (file == null){
            mBox.setErrorMessage("Please Select a File first.");
            mBox.showErrorDialog();
         }
        else{
            cu.run();
        }
    }
    @Action
    public void step() {
        if (file == null){
            mBox.setErrorMessage("Please Select a File first.");
            mBox.showErrorDialog();
         }
        else{
            cu.nextStep();
        }
            
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AField;
    private javax.swing.JLabel ALabel;
    private javax.swing.JTextField DisplayField;
    private javax.swing.JLabel DisplayLabel;
    private javax.swing.JTextField IRField;
    private javax.swing.JLabel IRLabel;
    private javax.swing.JTextField KeyboardField;
    private javax.swing.JLabel KeyboardLabel;
    private javax.swing.JLabel MemoryLabel;
    private javax.swing.JList MemoryList;
    private javax.swing.JTextField PCField;
    private javax.swing.JLabel PCLabel;
    private javax.swing.JTextField R0Field;
    private javax.swing.JLabel R0Label;
    private javax.swing.JTextField R1Field;
    private javax.swing.JLabel R1Label;
    private javax.swing.JTextField R2Field;
    private javax.swing.JLabel R2Label;
    private javax.swing.JTextField R3Field;
    private javax.swing.JLabel R3Label;
    private javax.swing.JTextField R4Field;
    private javax.swing.JLabel R4Label;
    private javax.swing.JTextField R5Field;
    private javax.swing.JLabel R5Label;
    private javax.swing.JTextField R6Field;
    private javax.swing.JLabel R6Label;
    private javax.swing.JTextField R7Field;
    private javax.swing.JLabel R7Label;
    private javax.swing.JTextField SRField;
    private javax.swing.JLabel SRLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JButton runButton;
    private javax.swing.JButton stepButton;
    // End of variables declaration//GEN-END:variables

   
    private JDialog aboutBox;
    
    public void updateGUI(Registers r, Memory m){
        this.R0Field.setText(r.read(R0));
        this.R1Field.setText(r.read(R1));
        this.R2Field.setText(r.read(R2));
        this.R3Field.setText(r.read(R3));
        this.R4Field.setText(r.read(R4));
        this.R5Field.setText(r.read(R5));
        this.R6Field.setText(r.read(R6));
        this.R7Field.setText(r.read(R7));
        this.getFrame().repaint();
    }
}


