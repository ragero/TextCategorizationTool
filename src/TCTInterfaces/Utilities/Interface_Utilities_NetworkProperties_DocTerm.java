/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TCTInterfaces.Utilities;

import TCTIO.SearchIO;
import TCTNetworkProperties.AverageNumberRelations;
import java.io.File;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author ragero
 */
public class Interface_Utilities_NetworkProperties_DocTerm extends javax.swing.JInternalFrame {

    /**
     * Creates new form Interface_Utilities_NetworkProperties_DocTerm
     */
    public Interface_Utilities_NetworkProperties_DocTerm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lArff = new javax.swing.JLabel();
        tArff = new javax.swing.JTextField();
        bProcurarArff = new javax.swing.JButton();
        lDist = new javax.swing.JLabel();
        tDist = new javax.swing.JTextField();
        bProcurarDist = new javax.swing.JButton();
        lProb = new javax.swing.JLabel();
        tProb = new javax.swing.JTextField();
        bProcurarProb = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tResults = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setTitle("Network Properties - Document-Term Relations");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Paths"));

        lArff.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lArff.setText("<html> Document-term matrix (<b>arff with ID</b>): <html>");

        tArff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tArffActionPerformed(evt);
            }
        });

        bProcurarArff.setText("Seach...");
        bProcurarArff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcurarArffActionPerformed(evt);
            }
        });

        lDist.setText("<html>\nProximity file (<b>doc-doc relations</b>):\n<html>");

        bProcurarDist.setText("Search...");
        bProcurarDist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcurarDistActionPerformed(evt);
            }
        });

        lProb.setText("<html>\nProbability file (<b>term-term relations</b>):\n</html>");

        bProcurarProb.setText("Search...");
        bProcurarProb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProcurarProbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lArff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lProb, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tDist, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(tArff))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bProcurarDist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bProcurarArff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(tProb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bProcurarProb)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tArff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bProcurarArff))
                    .addComponent(lArff, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bProcurarDist)
                    .addComponent(tDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lProb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tProb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProcurarProb)))
        );

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton1.setText("Analyse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Results"));

        tResults.setColumns(20);
        tResults.setRows(5);
        jScrollPane2.setViewportView(tResults);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tArffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tArffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tArffActionPerformed

    private void bProcurarArffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcurarArffActionPerformed
        tArff.setText(SearchIO.AbreArq());
    }//GEN-LAST:event_bProcurarArffActionPerformed

    private void bProcurarDistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcurarDistActionPerformed
        tDist.setText(SearchIO.AbreArq());
    }//GEN-LAST:event_bProcurarDistActionPerformed

    private void bProcurarProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProcurarProbActionPerformed
        tProb.setText(SearchIO.AbreArq());
    }//GEN-LAST:event_bProcurarProbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File fileArff = new File(tArff.getText());
        File fileDist = new File(tDist.getText());
        File fileProb = new File(tProb.getText());

        int numTerms = 0;

        if(!fileArff.getAbsolutePath().endsWith(".arff")){
            tResults.setText("Invalid ARF file");
            return;
        }

        tResults.setText("Loading ARFF file...\n\n");

        Instances dataOriginal = null;
        try{
            ConverterUtils.DataSource trainSource = new ConverterUtils.DataSource(fileArff.getAbsolutePath().toString()); //Carregando arquivo de Dados
            dataOriginal = trainSource.getDataSet();
        }catch(Exception e){
            System.err.println("Error when reading document-term matrix.");
            e.printStackTrace();
            System.exit(0);
        }

        double avgNumber = 0;

        tResults.append("Number of documents: " + dataOriginal.numInstances() + "\n");
        tResults.append("Number of terms: " + (dataOriginal.numAttributes() - 2) + "\n");
        avgNumber = AverageNumberRelations.BipartiteNetwork(dataOriginal);

        tResults.append("Average number of relations: " + avgNumber);
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bProcurarArff;
    private javax.swing.JButton bProcurarDist;
    private javax.swing.JButton bProcurarProb;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lArff;
    private javax.swing.JLabel lDist;
    private javax.swing.JLabel lProb;
    private javax.swing.JTextField tArff;
    private javax.swing.JTextField tDist;
    private javax.swing.JTextField tProb;
    private javax.swing.JTextArea tResults;
    // End of variables declaration//GEN-END:variables
}
