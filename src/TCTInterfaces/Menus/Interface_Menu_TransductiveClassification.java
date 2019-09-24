//*****************************************************************************
// Author: Rafael Geraldeli Rossi
// E-mail: rgr.rossi at gmail com
// Last-Modified: January 29, 2015
// Description: 
//*****************************************************************************

package TCTInterfaces.Menus;

import TCTConfigurations.TransductiveLearning.TransductiveConfiguration;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_CoTraining;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_DocDocRelations_ID;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_DocDocAndDocTermAndTermTermRelations;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_DocTermAndDocDocRelations;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_DocTermAndTermTermRelations;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_VSM;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_Multiview;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_SelfTraining;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_TermTermRelations;
import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_TrainTest;
import TCTInterfaces.Classification.Interface_TransductiveClassification;
import TCTInterfaces.Classification.Interface_TransductiveClassification_CoTraining;
import TCTInterfaces.Classification.Interface_TransductiveClassification_DocDocRelations_ID;
import TCTInterfaces.Classification.Interface_TransductiveClassification_DocDoc_DocTerm_TermTerm;
import TCTInterfaces.Classification.Interface_TransductiveClassification_DocTerm_DocDoc;
import TCTInterfaces.Classification.Interface_TransductiveClassification_DocTerm_TermTerm;
import TCTInterfaces.Classification.Interface_TransductiveClassification_Multiview;
import TCTInterfaces.Classification.Interface_TransductiveClassification_SelfTraining;
import TCTInterfaces.Classification.Interface_TransductiveClassification_TermTerm;
import TCTInterfaces.Classification.Interface_TransductiveClassification_TrainTest;
import TCTInterfaces.Classification.Interface_TransductiveClassification_VSM;

public class Interface_Menu_TransductiveClassification extends javax.swing.JFrame {

    TransductiveConfiguration_SelfTraining configurationSelfTraining;
    TransductiveConfiguration_CoTraining configurationCoTraining;
    TransductiveConfiguration configurationTransdutivo;
    TransductiveConfiguration_TrainTest configurationTransdutivo_TrainTest;
    TransductiveConfiguration_DocTermAndTermTermRelations configurationTransdutivoDTeTT;
    TransductiveConfiguration_TermTermRelations configurationTransdutivoTT;
    TransductiveConfiguration_DocTermAndDocDocRelations configurationTransdutivoDTeDD;
    TransductiveConfiguration_DocDocAndDocTermAndTermTermRelations configurationTransdutivoDDeDTeTT;
    TransductiveConfiguration_DocDocRelations_ID configurationTransdutivoDDcomID;
    TransductiveConfiguration_VSM configurationTransdutivoMEV;
    TransductiveConfiguration_Multiview configurationTransdutivoMultiview;
    
    public Interface_Menu_TransductiveClassification() {
        configurationSelfTraining = new TransductiveConfiguration_SelfTraining();
        configurationCoTraining = new TransductiveConfiguration_CoTraining(); 
        configurationTransdutivo = new TransductiveConfiguration();
        configurationTransdutivo_TrainTest= new TransductiveConfiguration_TrainTest();
        configurationTransdutivoDTeTT = new TransductiveConfiguration_DocTermAndTermTermRelations();
        configurationTransdutivoTT = new TransductiveConfiguration_TermTermRelations();
        configurationTransdutivoDTeDD = new TransductiveConfiguration_DocTermAndDocDocRelations();
        configurationTransdutivoDDeDTeTT = new TransductiveConfiguration_DocDocAndDocTermAndTermTermRelations();
        configurationTransdutivoDDcomID = new TransductiveConfiguration_DocDocRelations_ID();
        configurationTransdutivoMEV = new TransductiveConfiguration_VSM();
        configurationTransdutivoMultiview = new TransductiveConfiguration_Multiview();
        
        initComponents();
        
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        bTransdutivoTrainTest = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bTransdutivoDDcomID = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        bTransdutivoDT1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        bSelfTraining = new javax.swing.JButton();
        bCoTraining = new javax.swing.JButton();
        bMultiView = new javax.swing.JButton();
        bOutros = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TCT - Transductive Classification");
        setResizable(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Networks"));

        bTransdutivoTrainTest.setText("<html><center>Doc-Doc or Doc-Term Relations<br> <font size = \"2\">(Train-Test Validation)</font></center><html>");
        bTransdutivoTrainTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTransdutivoTrainTestActionPerformed(evt);
            }
        });

        jButton3.setText("Term-Term Relations");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("<html> <center> Doc-Term and Term-Term Relations </center> </html>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bTransdutivoDDcomID.setText("<html> Doc-Doc Relations <br> <font size = \"2\">(Representations with ID)</font> </html>");
        bTransdutivoDDcomID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTransdutivoDDcomIDActionPerformed(evt);
            }
        });

        jButton4.setText("<html>\nDoc-Doc and Doc-Term\nRelations\n</html>");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("<html>\n<center>\nDoc-Doc, Doc-Term and <br>Term-Term Relations\n</center>\n</html>");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        bTransdutivoDT1.setText("Doc-Doc or Doc-Term Relations");
        bTransdutivoDT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTransdutivoDT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bTransdutivoTrainTest)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
            .addComponent(jButton4)
            .addComponent(jButton5)
            .addComponent(bTransdutivoDDcomID)
            .addComponent(bTransdutivoDT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bTransdutivoDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bTransdutivoTrainTest, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bTransdutivoDDcomID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Vector Space Model"));

        bSelfTraining.setText("Self-Training");
        bSelfTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSelfTrainingActionPerformed(evt);
            }
        });

        bCoTraining.setText("Co-Training");
        bCoTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCoTrainingActionPerformed(evt);
            }
        });

        bMultiView.setText("MultiView Learning");
        bMultiView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMultiViewActionPerformed(evt);
            }
        });

        bOutros.setText("<html>\n<center>\nExpectation Maximization and <br> \nTransductive Support Vector Machines\n</center>\n</html>");
        bOutros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOutrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bSelfTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bCoTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bMultiView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bOutros)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(bSelfTraining, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bCoTraining, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bMultiView, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bOutros, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bTransdutivoTrainTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTransdutivoTrainTestActionPerformed
        new Interface_TransductiveClassification_TrainTest(configurationTransdutivo_TrainTest);
    }//GEN-LAST:event_bTransdutivoTrainTestActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Interface_TransductiveClassification_TermTerm(configurationTransdutivoTT);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Interface_TransductiveClassification_DocTerm_TermTerm(configurationTransdutivoDTeTT);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bTransdutivoDDcomIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTransdutivoDDcomIDActionPerformed
        new Interface_TransductiveClassification_DocDocRelations_ID(configurationTransdutivoDDcomID);
    }//GEN-LAST:event_bTransdutivoDDcomIDActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new Interface_TransductiveClassification_DocTerm_DocDoc(configurationTransdutivoDTeDD);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bSelfTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSelfTrainingActionPerformed
        new Interface_TransductiveClassification_SelfTraining(configurationSelfTraining);
    }//GEN-LAST:event_bSelfTrainingActionPerformed

    private void bCoTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCoTrainingActionPerformed
        new Interface_TransductiveClassification_CoTraining(configurationCoTraining);
    }//GEN-LAST:event_bCoTrainingActionPerformed

    private void bMultiViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMultiViewActionPerformed
        new Interface_TransductiveClassification_Multiview(configurationTransdutivoMultiview);
    }//GEN-LAST:event_bMultiViewActionPerformed

    private void bOutrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOutrosActionPerformed
        new Interface_TransductiveClassification_VSM(configurationTransdutivoMEV);
    }//GEN-LAST:event_bOutrosActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new Interface_TransductiveClassification_DocDoc_DocTerm_TermTerm(configurationTransdutivoDDeDTeTT);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void bTransdutivoDT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTransdutivoDT1ActionPerformed
        new Interface_TransductiveClassification(configurationTransdutivo);
    }//GEN-LAST:event_bTransdutivoDT1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCoTraining;
    private javax.swing.JButton bMultiView;
    private javax.swing.JButton bOutros;
    private javax.swing.JButton bSelfTraining;
    private javax.swing.JButton bTransdutivoDDcomID;
    private javax.swing.JButton bTransdutivoDT1;
    private javax.swing.JButton bTransdutivoTrainTest;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}