/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mathide
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
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

        jToolBar2 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar2.setRollover(true);

        jButton4.setText("加载");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton4);

        jButton3.setText("设置");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton3);

        jButton1.setText("执行");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        getContentPane().add(jToolBar2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"数据库地址", ""},
                {"数据库用户名", null},
                {"数据库密码", null},
                {"数据库查询语句", null},
                {"发送者", null},
                {"smtp", null},
                {"smtp密码", ""},
                {"发送主题", ""},
                {"附件名", null},
                {"发送者名称", null}
            },
            new String [] {
                "设置项", "设置值"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanel1.add(jProgressBar1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            sendMail();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        int rowCount = tableModel.getRowCount();

        settings = new HashMap();
        for (int i = 0; i < rowCount; i++) {
            settings.put(tableModel.getValueAt(i, 0).toString(), tableModel.getValueAt(i, 1).toString());
        }

        writeData(settings);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Properties prop = new Properties();
        File file = new File("setting.properties");
        InputStream fis;
        try {
            fis = new FileInputStream(file);
            prop.load(fis);

            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            int rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                tableModel.setValueAt(prop.getProperty(tableModel.getValueAt(i, 0).toString()), i, 1);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    void writeData(HashMap hashMap) {
        Properties prop = new Properties();
        try {
            File file = new File("setting.properties");
            if (!file.exists()) {
                file.createNewFile();
            }
            InputStream fis = new FileInputStream(file);
            prop.load(fis);
            fis.close();//一定要在修改值之前关闭fis   
            OutputStream fos = new FileOutputStream(file);

            Set keySet = hashMap.keySet();
            for (Object key : keySet) {
                prop.setProperty(key.toString(), hashMap.get(key).toString());
            }

            prop.store(fos, "props");
            fos.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    void sendMail() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        String databaseUrl = tableModel.getValueAt(0, 1).toString();
        String databaseUser = tableModel.getValueAt(1, 1).toString();
        String databaseUserPassword = tableModel.getValueAt(2, 1).toString();
        String sqlString = tableModel.getValueAt(3, 1).toString();
        String sender = tableModel.getValueAt(4, 1).toString();
        String smtpName = tableModel.getValueAt(5, 1).toString();
        String senderPassword = tableModel.getValueAt(6, 1).toString();
        String mailSubject = tableModel.getValueAt(7, 1).toString();
        String fileName = tableModel.getValueAt(8, 1).toString();
        String senderName = tableModel.getValueAt(9, 1).toString();

        MyDataGet myDataGet = new MyDataGet();
        myDataGet.getData(databaseUrl, databaseUser, databaseUserPassword, sqlString);
        Vector<LinkedHashMap> contents = myDataGet.getContents();
        int contentsNum = contents.size();
        
        jProgressBar1.setMaximum(contentsNum);

        for (int i = 0; i < contentsNum; i++) {
            LinkedHashMap content = contents.get(i);
            String contentString = "";

            Set keySet = content.keySet();
            for (Object key : keySet) {
                contentString = contentString + "<tr>" + "<td>" + key.toString() + "</td>" + "<td>" + content.get(key).toString() + "</td>" + "</tr>";
            }

            contentString = "<table border=\"1\">" + contentString + "</table>";
            String mail_to = (String) content.get("协同邮箱");

            Authenticator auth = new MyAutherticator(sender, senderPassword); //1--进行邮件服务用户认证  
            Properties pros = MyProperties.getPro(smtpName);                 //2--属性配置  
            Session session = Session.getDefaultInstance(pros, auth);//3--设置session,和邮件服务器进行通讯  
            session.setDebug(true);

            MyMail mail = new MyMail();
            mail.setMail_subject(mailSubject);
            mail.setMail_head_name(mailSubject);
            mail.setMail_head_value(mailSubject);
            mail.setMail_body(contentString);
            mail.setMail_from(sender);
            mail.setMail_to(mail_to);
            mail.setPersonalName(senderName);
            mail.setMail_file(fileName);
            MimeMessage message;
            try {
                message = mail.getMail(session); //4--设置一个邮件
                Transport.send(message);//5--发送  
            } catch (SendFailedException e) {
                Address[] invalidAddress= e.getInvalidAddresses();
                for (Address address : invalidAddress) {
                    System.out.println(address.toString());//无用的地址
                }
                continue;
            } catch (Exception e) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
            }

        }

    }

    private HashMap settings;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
}
