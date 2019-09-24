//*****************************************************************************
// Author: Rafael Geraldeli Rossi
// E-mail: rgr.rossi at gmail com
// Last-Modified: January 29, 2015
// Description: 
//*****************************************************************************

package TCTInterfaces.Classification;

import TCTInterfaces.Parameters.Interface_Parameters_Rocchio;
import TCTInterfaces.Parameters.Interface_Parameters_MLP;
import TCTInterfaces.Parameters.Interface_Parameters_SMO;
import TCTInterfaces.Parameters.Interface_Parameters_LogisticRegression;
import TCTInterfaces.Parameters.Interface_Parameters_RidgeRegression;
import TCTInterfaces.Parameters.Interface_Parameters_KAG;
import TCTInterfaces.Parameters.Interface_Parameters_TGM;
import TCTInterfaces.Parameters.Interface_Parameters_IMBHN;
import TCTInterfaces.Parameters.Interface_Parameters_J48;
import TCTInterfaces.Parameters.Interface_Parameters_RIPPER;
import TCTInterfaces.Parameters.Interface_Parameters_KNN;
import TCTInterfaces.Menus.Interface_Menu2;
import TCT.SupervisedInductiveClassification2;
import TCTConfigurations.Email;
import TCTConfigurations.SupervisedLearning.SupervisedInductiveConfiguration;
import TCTIO.SearchIO;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interface_SupervisedInductiveClassification2 extends javax.swing.JInternalFrame {
    
    private SupervisedInductiveConfiguration configuration;
    
    /** Creates new form ClassificacaoProposicional */
    public Interface_SupervisedInductiveClassification2(SupervisedInductiveConfiguration configuration) {
        this.configuration = configuration;
        initComponents();
        this.setVisible(true);
    }
    
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tDirIn = new javax.swing.JTextField();
        tDirOut = new javax.swing.JTextField();
        bProcurarDirIn = new javax.swing.JButton();
        bProcurarDirOut = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cNB = new javax.swing.JCheckBox();
        cMNB = new javax.swing.JCheckBox();
        cJ48 = new javax.swing.JCheckBox();
        cSMO = new javax.swing.JCheckBox();
        cKNN = new javax.swing.JCheckBox();
        bOpKnn = new javax.swing.JButton();
        bOpSmo = new javax.swing.JButton();
        cMLP = new javax.swing.JCheckBox();
        bOpMlp = new javax.swing.JButton();
        cIMBHN = new javax.swing.JCheckBox();
        bRHLMS = new javax.swing.JButton();
        cIMBHN2 = new javax.swing.JCheckBox();
        bIMBHN2 = new javax.swing.JButton();
        cTGM = new javax.swing.JCheckBox();
        bTGM = new javax.swing.JButton();
        cRocchio = new javax.swing.JCheckBox();
        bRocchio = new javax.swing.JButton();
        cRIPPER = new javax.swing.JCheckBox();
        bRIPPER = new javax.swing.JButton();
        cBLR = new javax.swing.JCheckBox();
        bBLR = new javax.swing.JButton();
        bJ48 = new javax.swing.JButton();
        cKAOG = new javax.swing.JCheckBox();
        bKAG = new javax.swing.JButton();
        cLLSF = new javax.swing.JCheckBox();
        bLLSF = new javax.swing.JButton();
        lLegend = new javax.swing.JLabel();
        cIRR = new javax.swing.JCheckBox();
        bFechar = new javax.swing.JButton();
        bExecutar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tFolds = new javax.swing.JTextField();
        tRep = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tNumThreads = new javax.swing.JTextField();
        bSalvar = new javax.swing.JButton();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setTitle("TCT - Supervised Inductive Classification");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Directories"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("<html>Arffs (<b>without ID</b>)</html>");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Results:");

        bProcurarDirIn.setText("Search...");
        bProcurarDirIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcurarDirInActionPerformed(evt);
            }
        });

        bProcurarDirOut.setText("Search...");
        bProcurarDirOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcurarDirOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tDirOut)
                    .addComponent(tDirIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bProcurarDirOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bProcurarDirIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tDirIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProcurarDirIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tDirOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProcurarDirOut))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Classification Algorithms"));

        cNB.setText("NB");

        cMNB.setText("MNB");

        cJ48.setText("J48");

        cSMO.setText("SMO");

        cKNN.setText("KNN");

        bOpKnn.setText("...");
        bOpKnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOpKnnActionPerformed(evt);
            }
        });

        bOpSmo.setText("...");
        bOpSmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOpSmoActionPerformed(evt);
            }
        });

        cMLP.setText("MLP");

        bOpMlp.setText("...");
        bOpMlp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOpMlpActionPerformed(evt);
            }
        });

        cIMBHN.setText("<html>\nIMBHN<sup>C</sup>\n</html>");

        bRHLMS.setText("...");
        bRHLMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRHLMSActionPerformed(evt);
            }
        });

        cIMBHN2.setText("<html>\nIMBHN<sup>R</sup>\n</html>");
        cIMBHN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cIMBHN2ActionPerformed(evt);
            }
        });

        bIMBHN2.setText("...");
        bIMBHN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bIMBHN2ActionPerformed(evt);
            }
        });

        cTGM.setText("TGM");

        bTGM.setText("...");
        bTGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTGMActionPerformed(evt);
            }
        });

        cRocchio.setText("Rocchio");

        bRocchio.setText("...");
        bRocchio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRocchioActionPerformed(evt);
            }
        });

        cRIPPER.setText("JRip");

        bRIPPER.setText("...");
        bRIPPER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRIPPERActionPerformed(evt);
            }
        });

        cBLR.setText("BLR");

        bBLR.setText("...");
        bBLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBLRActionPerformed(evt);
            }
        });

        bJ48.setText("...");
        bJ48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bJ48ActionPerformed(evt);
            }
        });

        cKAOG.setText("KAC");

        bKAG.setText("...");
        bKAG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bKAGActionPerformed(evt);
            }
        });

        cLLSF.setText("LLSF");

        bLLSF.setText("...");
        bLLSF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLLSFActionPerformed(evt);
            }
        });

        lLegend.setForeground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.selectionBackground"));
        lLegend.setText("<html>\n<u>Legend</u>\n<html>");
        lLegend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lLegend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lLegendMouseClicked(evt);
            }
        });

        cIRR.setText("IRR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cMNB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cRocchio)
                                .addGap(18, 18, 18)
                                .addComponent(bRocchio))
                            .addComponent(cNB)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cJ48)
                                    .addComponent(cBLR)
                                    .addComponent(cSMO))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bJ48)
                                    .addComponent(bBLR)
                                    .addComponent(bOpSmo))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(cRIPPER)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cMLP)
                                        .addComponent(cKNN))
                                    .addGap(46, 46, 46)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cIMBHN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cLLSF)
                                    .addComponent(cIMBHN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bRHLMS, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bLLSF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bRIPPER, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bOpMlp, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bOpKnn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bIMBHN2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cTGM)
                                .addGap(40, 40, 40)
                                .addComponent(bTGM))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cKAOG)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bKAG)))
                        .addComponent(lLegend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cIRR))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cTGM)
                        .addComponent(bTGM))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bOpKnn)
                        .addComponent(cKNN)
                        .addComponent(cNB)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cKAOG)
                        .addComponent(bKAG))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bOpMlp)
                        .addComponent(cMLP)
                        .addComponent(cMNB)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bRIPPER)
                            .addComponent(cRIPPER)
                            .addComponent(cJ48)
                            .addComponent(bJ48)
                            .addComponent(cIRR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bLLSF)
                            .addComponent(cLLSF)
                            .addComponent(cBLR)
                            .addComponent(bBLR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bRHLMS)
                            .addComponent(cIMBHN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cSMO)
                            .addComponent(bOpSmo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bIMBHN2)
                            .addComponent(cIMBHN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cRocchio)
                            .addComponent(bRocchio)))
                    .addComponent(lLegend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bFechar.setText("Close");
        bFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFecharActionPerformed(evt);
            }
        });

        bExecutar.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        bExecutar.setText("Run");
        bExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExecutarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Evaluation"));

        jLabel3.setText("Nº of Folds:");

        jLabel4.setText("Nº of Repetitions:");

        tFolds.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tFolds.setText("10");

        tRep.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tRep.setText("1");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Multithreading"));

        jLabel5.setText("# of Threads:");

        tNumThreads.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tNumThreads.setText("10");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNumThreads, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tNumThreads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tRep)
                            .addComponent(tFolds, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tFolds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bSalvar.setText("Save Configuration");
        bSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bFechar)
                    .addComponent(bSalvar)
                    .addComponent(bExecutar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bProcurarDirInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcurarDirInActionPerformed
        tDirIn.setText(SearchIO.AbreDir());
}//GEN-LAST:event_bProcurarDirInActionPerformed

    private void bProcurarDirOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcurarDirOutActionPerformed
        tDirOut.setText(SearchIO.AbreDir());
}//GEN-LAST:event_bProcurarDirOutActionPerformed

    private void bFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_bFecharActionPerformed

    private void defineConfiguration(){
        Email.emailInterface(configuration);
        
        configuration.setNumFolds(Integer.parseInt(tFolds.getText()));
        configuration.setNumReps(Integer.parseInt(tRep.getText()));
        configuration.setNumThreads(Integer.parseInt(tNumThreads.getText()));
        
        if(cNB.isSelected()){
            configuration.setNB(true);
        }else{
            configuration.setNB(false);
        }
        if(cMNB.isSelected()){
            configuration.setMNB(true);
        }else{
            configuration.setMNB(false);
        }
        if(cJ48.isSelected()){
            configuration.setJ48(true);
        }else{
            configuration.setJ48(false);
        }
        if(cSMO.isSelected()){
            configuration.setSMO(true);
        }else{
            configuration.setSMO(false);
        }
        if(cKNN.isSelected()){
            configuration.setKNN(true);
        }else{
            configuration.setKNN(false);
        }
        if(cMLP.isSelected()){
            configuration.setMLP(true);
        }else{
            configuration.setMLP(false);
        }
        if(cIMBHN.isSelected()){
            configuration.setIMBHN(true);
        }else{
            configuration.setIMBHN(false);
        }
        if(cIMBHN2.isSelected()){
            configuration.setIMBHN2(true);
        }else{
            configuration.setIMBHN2(false);
        }
        if(cTGM.isSelected()){
            configuration.setTGM(true);
        }else{
            configuration.setTGM(false);
        }
        if(cRocchio.isSelected()){
            configuration.setRocchio(true);
        }else{
            configuration.setRocchio(false);
        }
        if(cRIPPER.isSelected()){
            configuration.setRIPPER(true);
        }else{
            configuration.setRIPPER(false);
        }
        if(cBLR.isSelected()){
            configuration.setBLR(true);
        }else{
            configuration.setBLR(false);
        }
        if(cKAOG.isSelected()){
            configuration.setKAOG(true);
        }else{
            configuration.setKAOG(false);
        }
        if(cLLSF.isSelected()){
            configuration.setLLSF(true);
        }else{
            configuration.setLLSF(false);
        }
        if(cIRR.isSelected()){
            configuration.setIRR(true);
        }else{
            configuration.setIRR(false);
        }
        
        configuration.setDirEntrada(tDirIn.getText());
        configuration.setDirSaida(tDirOut.getText());
    }
    
    private void bExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExecutarActionPerformed
        defineConfiguration();
        SupervisedInductiveClassification2.learning(configuration);
    }//GEN-LAST:event_bExecutarActionPerformed

    private void bOpSmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOpSmoActionPerformed
        JInternalFrame frame = new Interface_Parameters_SMO(configuration.getParametersSMO());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bOpSmoActionPerformed

    private void bOpKnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOpKnnActionPerformed
        JInternalFrame frame = new Interface_Parameters_KNN(configuration.getParametersKNN());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bOpKnnActionPerformed

    private void bOpMlpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOpMlpActionPerformed
        JInternalFrame frame = new Interface_Parameters_MLP(configuration.getParametersMLP());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bOpMlpActionPerformed

    private void bRHLMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRHLMSActionPerformed
        JInternalFrame frame = new Interface_Parameters_IMBHN(configuration.getParametersIMBHN());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bRHLMSActionPerformed

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        
        defineConfiguration();
        JFileChooser save = new JFileChooser();
        save.setFileSelectionMode(save.FILES_ONLY);
        save.setDialogTitle("Save");
        save.setDialogType(save.SAVE_DIALOG);
        save.setFileFilter(new FileNameExtensionFilter("Text Categorization Tool (*.tct)", "tct"));
        save.showSaveDialog(null);

        File config = save.getSelectedFile();
        if(config == null){
            return;
        }
        String fileName = config.toString();
        
        FileOutputStream file;
        ObjectOutputStream obj;
        try{
            if(!fileName.endsWith(".tct")){
                fileName = fileName + ".tct";
            }
            file = new FileOutputStream(fileName);
            obj = new ObjectOutputStream(file);
            obj.writeObject(configuration);
            obj.close();
        }catch(Exception e){
            System.err.println("Error when saving configuration object.");
            e.printStackTrace();
            System.exit(0);
        }
    }//GEN-LAST:event_bSalvarActionPerformed

    private void bIMBHN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIMBHN2ActionPerformed
        JInternalFrame frame = new Interface_Parameters_IMBHN(configuration.getParametersIMBHN2());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bIMBHN2ActionPerformed

    private void bTGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTGMActionPerformed
        JInternalFrame frame = new Interface_Parameters_TGM(configuration);
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bTGMActionPerformed

    private void bRocchioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRocchioActionPerformed
        JInternalFrame frame = new Interface_Parameters_Rocchio(configuration.getParametersRocchio());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bRocchioActionPerformed

    private void bRIPPERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRIPPERActionPerformed
        JInternalFrame frame = new Interface_Parameters_RIPPER(configuration.getParametersRIPPER());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bRIPPERActionPerformed

    private void bBLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBLRActionPerformed
        JInternalFrame frame = new Interface_Parameters_LogisticRegression(configuration.getParametersBLR());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bBLRActionPerformed

    private void bJ48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bJ48ActionPerformed
        JInternalFrame frame = new Interface_Parameters_J48(configuration.getParametersJ48());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bJ48ActionPerformed

    private void bKAGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bKAGActionPerformed
        JInternalFrame frame = new Interface_Parameters_KAG(configuration.getParametersKAG());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bKAGActionPerformed

    private void bLLSFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLLSFActionPerformed
        JInternalFrame frame = new Interface_Parameters_RidgeRegression(configuration.getParametersRidgeRegression());
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bLLSFActionPerformed

    private void lLegendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lLegendMouseClicked
        String text = "<html>\n" +
        "<b>NB</b> - Naive Bayes <br>\n" +
        "<b>MNB</b> - Multinomial Naive Bayes <br>\n" +
        "<b>J48</b> - Weka's implementation of C4.5\n" +
        "<b>BLR</b> - Bayesian Logistic Regression<br>\n" +
        "<b>SMO</b> - Sequential Minimal Optimization (Weka's implementation of Support Vector Machines)<br>\n" +
        "<b>KNN</b> - <i>K</i> Nearest Neighbors<br>\n" +
        "<b>MLP</b> - Multi-Layer Perceptron<br>\n" +
        "<b>JRip</b> - Weka's implementation of RIPPER<br>\n" +
        "<b>LLSF</b> - Linear Least Squares Fit<br>\n" +
        "<b>IMBHN<sup>C</sup></b> - Inductive Model Generation based on Bipartite Heterogeneous Network (with classification function)<br>\n" +
        "<b>IMBHN<sup>R</sup></b> - Inductive Model Generation based on Bipartite Heterogeneous Network (with regression function)<br>\n" +
        "<b>TGM</b> - Term Graph Model<br>\n" +
        "<b>KAG</b> - <i>K</i> Associated Graphs<br>\n" +
        "</html>";
        JInternalFrame frame = new Interface_Legend(text);
        Interface_Menu2.getDesktop().add(frame);
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Interface_Menu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lLegendMouseClicked

    private void cIMBHN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cIMBHN2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cIMBHN2ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBLR;
    private javax.swing.JButton bExecutar;
    private javax.swing.JButton bFechar;
    private javax.swing.JButton bIMBHN2;
    private javax.swing.JButton bJ48;
    private javax.swing.JButton bKAG;
    private javax.swing.JButton bLLSF;
    private javax.swing.JButton bOpKnn;
    private javax.swing.JButton bOpMlp;
    private javax.swing.JButton bOpSmo;
    private javax.swing.JButton bProcurarDirIn;
    private javax.swing.JButton bProcurarDirOut;
    private javax.swing.JButton bRHLMS;
    private javax.swing.JButton bRIPPER;
    private javax.swing.JButton bRocchio;
    private javax.swing.JButton bSalvar;
    private javax.swing.JButton bTGM;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cBLR;
    private javax.swing.JCheckBox cIMBHN;
    private javax.swing.JCheckBox cIMBHN2;
    private javax.swing.JCheckBox cIRR;
    private javax.swing.JCheckBox cJ48;
    private javax.swing.JCheckBox cKAOG;
    private javax.swing.JCheckBox cKNN;
    private javax.swing.JCheckBox cLLSF;
    private javax.swing.JCheckBox cMLP;
    private javax.swing.JCheckBox cMNB;
    private javax.swing.JCheckBox cNB;
    private javax.swing.JCheckBox cRIPPER;
    private javax.swing.JCheckBox cRocchio;
    private javax.swing.JCheckBox cSMO;
    private javax.swing.JCheckBox cTGM;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lLegend;
    private javax.swing.JTextField tDirIn;
    private javax.swing.JTextField tDirOut;
    private javax.swing.JTextField tFolds;
    private javax.swing.JTextField tNumThreads;
    private javax.swing.JTextField tRep;
    // End of variables declaration//GEN-END:variables

}
