package KTS3G1;


public class ControlUI extends javax.swing.JFrame {

    DataStore ds;
    ControlUI cui;
    //Transmitter tm;
    
    /**
     * Creates new form ControlUI
     */
    public ControlUI(DataStore ds) {
        // initComponents();  // The GUI code, generated by NetBeans is not called

        this.ds = ds;
      //  this.tm=tm;
        
        myinitComponents();
        setTitle("Grupp 1");
    }

    public void showStatus(){
        System.out.println("Nodes: "+ds.nodes);
        System.out.println("Arcs: "+ds.arcs);
         statusuppdTextArea.append("Nodes: "+ds.nodes+"\n");
         statusuppdTextArea.append("Arcs: "+ds.arcs+"\n");
                
    } 
        public void appendStatus(String s)
    {
        statusuppdTextArea.append(s+"\n");
        statusuppdTextArea.setCaretPosition(statusuppdTextArea.getDocument().getLength());
    }
 
       public void myinitComponents()
       {

        jPanelMap = new MapPanel(ds);
        jScrollPane1 = new javax.swing.JScrollPane();
        statusuppdTextArea = new javax.swing.JTextArea();
        startPosition = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        uppdTextArea = new javax.swing.JTextArea();
        startStopp = new javax.swing.JToggleButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 350));

        jPanelMap.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelMap.setToolTipText("");
        jPanelMap.setPreferredSize(new java.awt.Dimension(700, 350));

        javax.swing.GroupLayout jPanelMapLayout = new javax.swing.GroupLayout(jPanelMap);
        jPanelMap.setLayout(jPanelMapLayout);
        jPanelMapLayout.setHorizontalGroup(
            jPanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );
        jPanelMapLayout.setVerticalGroup(
            jPanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        statusuppdTextArea.setEditable(false);
        statusuppdTextArea.setColumns(20);
        statusuppdTextArea.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        statusuppdTextArea.setRows(5);
        statusuppdTextArea.setText("Statusuppdateringar:"+"\n");
        jScrollPane1.setViewportView(statusuppdTextArea);

        startPosition.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        startPosition.setText("Återvänd till startposition");
        startPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        startPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPositionActionPerformed(evt);
            }
        });

        uppdTextArea.setEditable(false);
        uppdTextArea.setColumns(20);
        uppdTextArea.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        uppdTextArea.setRows(5);
        uppdTextArea.setText("Uppdragslista i realtid:");
        jScrollPane2.setViewportView(uppdTextArea);

        startStopp.setForeground(java.awt.Color.green);
        startStopp.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        startStopp.setText("Start");
        startStopp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStoppActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 0, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jTextField3.setEditable(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel1.setText("Status hos andra AGVer:");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Status:");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Kontakt med AGV:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(startStopp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startPosition, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelMap, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(startStopp, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(startPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jPanelMap, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))
        );
       
        pack();
       }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMap = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusuppdTextArea = new javax.swing.JTextArea();
        startPosition = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        uppdTextArea = new javax.swing.JTextArea();
        startStopp = new javax.swing.JToggleButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 350));

        jPanelMap.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelMap.setToolTipText("");
        jPanelMap.setPreferredSize(new java.awt.Dimension(700, 350));

        javax.swing.GroupLayout jPanelMapLayout = new javax.swing.GroupLayout(jPanelMap);
        jPanelMap.setLayout(jPanelMapLayout);
        jPanelMapLayout.setHorizontalGroup(
            jPanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );
        jPanelMapLayout.setVerticalGroup(
            jPanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        statusuppdTextArea.setEditable(false);
        statusuppdTextArea.setColumns(20);
        statusuppdTextArea.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        statusuppdTextArea.setRows(5);
        statusuppdTextArea.setText("Statusuppdateringar:");
        jScrollPane1.setViewportView(statusuppdTextArea);

        startPosition.setFont(new java.awt.Font("Lucida Grande", 1, 8)); // NOI18N
        startPosition.setText("Återvänd till startposition");
        startPosition.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        startPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPositionActionPerformed(evt);
            }
        });

        uppdTextArea.setEditable(false);
        uppdTextArea.setColumns(20);
        uppdTextArea.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        uppdTextArea.setRows(5);
        uppdTextArea.setText("Uppdragslista i realtid:");
        jScrollPane2.setViewportView(uppdTextArea);

        startStopp.setBackground(java.awt.Color.green);
        startStopp.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        startStopp.setText("Start");
        startStopp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStoppActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 0, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jTextField3.setEditable(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 10)); // NOI18N
        jLabel1.setText("Status hos andra AGVer:");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Status:");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Kontakt med AGV:");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("Map:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startStopp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startPosition, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(jPanelMap, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(startStopp, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jLabel4)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jPanelMap, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startPositionActionPerformed
        // TODO add your handling code here:
        
        cui = new ControlUI(ds);
        cui.setVisible(true);
        cui.showStatus();
        RobotRead p1 = new RobotRead(ds,cui);
        Thread t1 = new Thread(p1);
        GuiUpdate p2 = new GuiUpdate(ds,cui);
        Thread t2 = new Thread(p2);
        System.out.println("Startar 1 trådar...");
        t1.start();t2.start(); 
        
        System.out.println("Trådar startade, main avslutas");
         OptPlan op = new OptPlan(ds);
         op.createPlan();
    }//GEN-LAST:event_startPositionActionPerformed

    private void startStoppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStoppActionPerformed
        
       if (startStopp.getText().equals("Start")) {
          startStopp.setText("Stop");
          startStopp.setForeground(new java.awt.Color(255, 0, 0));
          GuiUpdate r1 = new GuiUpdate(ds, cui);
          r1.run();
          
       } else if (startStopp.getText().equals("Stop")) {
          startStopp.setText("Start");
          startStopp.setForeground(new java.awt.Color(0, 255, 0));
       }
    }//GEN-LAST:event_startStoppActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
   
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelMap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton startPosition;
    private javax.swing.JToggleButton startStopp;
    private javax.swing.JTextArea statusuppdTextArea;
    private javax.swing.JTextArea uppdTextArea;
    // End of variables declaration//GEN-END:variables

    private MapPanel map;
}
