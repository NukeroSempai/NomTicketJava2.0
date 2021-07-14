package VISTAS;

import CONTROLADOR.CajerosDAO;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import MODELOS.EVENTOS;
import MODELOS.CAJERO;
import SEGURIDAD.Seguridad;

/**
 *
 * @author Nukero
 * @version 1.0
 *
 * Formulario creado para guardar y actualizar datos
 *
 */
public class JDPerfil extends javax.swing.JDialog {
    
    public static boolean estado;
    private Seguridad seg = new Seguridad();
    private CajerosDAO dao = new CajerosDAO();
    private CAJERO c = new CAJERO();
    private EVENTOS event = new EVENTOS();

    public JDPerfil(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTextClave1.setEnabled(false);
        jTextClave2.setEnabled(false);
        estado = true;
    }
    
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("IMAGENES/icon2.png"));
        return retValue;
    }

    public void setCajero(CAJERO cajero) {
        this.c = cajero;
        cargarCajero();
    }

    private void cargarCajero() {
        jTextRut.setText(c.getRut_cajero());
        jTextNombre.setText(c.getNombre());
    }

    private void Actualizar() {
        jTextClave1.setEnabled(false);
        String rut_caj = jTextRut.getText();
        String nombre_caj = jTextNombre.getText();
        Object[] obj = new Object[3];
        obj[0] = rut_caj;
        obj[1] = nombre_caj;
        obj[2] = c.getRut_cajero();
        if (dao.actualizarPerfil(obj) > 0) {
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Éxito!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void Actualizar(String clave) {
        jTextClave1.setEnabled(false);
        String rut_caj = jTextRut.getText();
        String nombre_caj = jTextNombre.getText();
        String claveR = clave;
        clave = seg.encriptar(clave);
        Object[] obj = new Object[3];
        obj[0] = rut_caj;
        obj[1] = nombre_caj;
        obj[2] = c.getRut_cajero();
        if (dao.actualizarPerfil(obj, clave) > 0) {
            dao.CambiarClaveUsuario(rut_caj, claveR);
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Éxito!", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Por Favor volver a ingresar", "Aviso!", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelModalidadEntrada = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLRut1 = new javax.swing.JLabel();
        jTextRut = new javax.swing.JTextField();
        jLRut2 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jLRut4 = new javax.swing.JLabel();
        jCheckClave = new javax.swing.JCheckBox();
        jTextClave1 = new javax.swing.JPasswordField();
        jTextClave2 = new javax.swing.JPasswordField();
        jLRut5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jBConfirmar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(225, 139, 34));

        jLabelModalidadEntrada.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelModalidadEntrada.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModalidadEntrada.setText("Información Personal");

        jLRut1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut1.setText("Rut");

        jLRut2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut2.setText("Nombre");

        jTextNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNombreKeyTyped(evt);
            }
        });

        jLRut4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut4.setText("Clave");

        jCheckClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckClaveActionPerformed(evt);
            }
        });

        jLRut5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut5.setText("Repetir Clave");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextClave1)
                    .addComponent(jTextClave2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLRut1)
                                .addComponent(jTextRut, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addComponent(jLRut2)
                                .addComponent(jTextNombre))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLRut4)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckClave))
                            .addComponent(jLRut5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLRut2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLRut4)
                    .addComponent(jCheckClave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextClave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLRut5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextClave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBConfirmar.setText("GUARDAR");
        jBConfirmar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConfirmarActionPerformed(evt);
            }
        });

        jBCancelar.setText("CANCELAR");
        jBCancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBConfirmar)
                    .addComponent(jBCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelModalidadEntrada)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelModalidadEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        estado = false;
        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConfirmarActionPerformed
        if(jTextRut.getText().length()!=10){
            JOptionPane.showMessageDialog(null, "ERROR RUT NO VÁLIDO!", "Error!", JOptionPane.ERROR_MESSAGE);
        }else{
            if (seg.verificarRUT(jTextRut.getText()) == false) {
                JOptionPane.showMessageDialog(null, "ERROR RUT NO VÁLIDO!", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                if (jTextClave1.getPassword().length != 0 && jTextClave2.getPassword().length != 0) {
                    String clave1 = "";
                    for (int i = 0; i < jTextClave1.getPassword().length; i++) {
                        clave1 += jTextClave1.getPassword()[i];
                    }
                    String clave2 = "";
                    for (int i = 0; i < jTextClave2.getPassword().length; i++) {
                        clave2 += jTextClave2.getPassword()[i];
                    }
                    if (clave1.equals(clave2)) {
                        Actualizar(clave1);                        
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ambas claves deben ser iguales", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Actualizar();
                    this.dispose();
                }
            }
        }   
    }//GEN-LAST:event_jBConfirmarActionPerformed

    private void jCheckClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckClaveActionPerformed
        if (jCheckClave.isSelected()) {
            jTextClave1.setEnabled(true);
            jTextClave1.setText("");
            jTextClave2.setEnabled(true);
            jTextClave2.setText("");
        } else {
            jTextClave1.setEnabled(false);
            jTextClave1.setText("");
            jTextClave2.setEnabled(false);
            jTextClave2.setText("");
        }
    }//GEN-LAST:event_jCheckClaveActionPerformed

    private void jTextNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNombreKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_jTextNombreKeyTyped

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
            java.util.logging.Logger.getLogger(JDPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDPerfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDPerfil dialog = new JDPerfil(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBConfirmar;
    private javax.swing.JCheckBox jCheckClave;
    private javax.swing.JLabel jLRut1;
    private javax.swing.JLabel jLRut2;
    private javax.swing.JLabel jLRut4;
    private javax.swing.JLabel jLRut5;
    private javax.swing.JLabel jLabelModalidadEntrada;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jTextClave1;
    private javax.swing.JPasswordField jTextClave2;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextRut;
    // End of variables declaration//GEN-END:variables
}
