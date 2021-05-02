package VISTAS;

import CONTROLADOR.CajerosDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import MODELOS.EVENTOS;
import MODELOS.PRODUCTO;
import CONTROLADOR.ProductosDAO;
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
public class JDCajero extends javax.swing.JDialog {

    private int MODALIDAD;

    private Seguridad seg = new Seguridad();
    private CajerosDAO dao = new CajerosDAO();
    private CAJERO c = new CAJERO();
    private List<String> sucursales = dao.listarSucursales();
    private EVENTOS event = new EVENTOS();

    public JDCajero(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void setModalidad(int modalidad) {
        this.MODALIDAD = modalidad;
        inicializar();
    }

    public void setCajero(CAJERO cajero) {
        this.c = cajero;
        cargarCajero();
    }

    private void cargarCajero() {
        jTextRut.setText(c.getRut_cajero());
        jTextNombre.setText(c.getNombre());
        jComboSucursal.setSelectedIndex(c.getFk_sucursal());
        jComboEstado.setSelectedIndex(c.getEstado_login());
        jComboAdministrador.setSelectedIndex(c.getAdministrador());
    }

    private void inicializar() {
        jTextClave.setEnabled(false);
        cargarCombo();
        if (MODALIDAD == 0) {
            jLabelModalidadEntrada.setText("Agregar Producto");
            jBConfirmar.setText("AGREGAR");
        } else {
            jLabelModalidadEntrada.setText("Modificar Producto");
            jBConfirmar.setText("GUARDAR CAMBIOS");
        }
    }

    private void cargarCombo() {
        for (int i = 0; i < sucursales.size(); i++) {
            jComboSucursal.addItem(sucursales.get(i));
        }
    }

    private void Agregar() {
        jTextClave.setEnabled(true);
        String rut_caj = jTextRut.getText();
        String nombre_caj = jTextNombre.getText();
        int sucursal = jComboSucursal.getSelectedIndex() + 1;
        String clave = "";
        for (int i = 0; i < jTextClave.getPassword().length; i++) {
            clave += jTextClave.getPassword()[i];
        }
        clave = seg.encriptar(clave);
        int administrador = jComboAdministrador.getSelectedIndex();
        int estado = jComboEstado.getSelectedIndex();

        Object[] ob = new Object[6];
        ob[0] = rut_caj;
        ob[1] = nombre_caj;
        ob[2] = clave;
        ob[3] = sucursal;
        ob[4] = administrador;
        ob[5] = estado;
        if (dao.add(ob) > 0) {
            JOptionPane.showMessageDialog(null, "Cajero Agregado correctamente", "Exito!", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "error al agregar Cajero", "error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Actualizar() {
        jTextClave.setEnabled(false);
        String rut_caj = jTextRut.getText();
        String nombre_caj = jTextNombre.getText();
        int sucursal = jComboSucursal.getSelectedIndex() + 1;
        int administrador = jComboAdministrador.getSelectedIndex();
        int estado = jComboEstado.getSelectedIndex();
        Object[] obj = new Object[6];
        obj[0] = rut_caj;
        obj[1] = nombre_caj;
        obj[2] = sucursal;
        obj[3] = administrador;
        obj[4] = estado;
        obj[5] = c.getRut_cajero();
        if (dao.actualizar(obj) > 0) {
            JOptionPane.showMessageDialog(null, "Cajero Actualizado correctamente", "Exito!", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "error al Actualizar Cajero", "error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void Actualizar(String clave) {
        jTextClave.setEnabled(false);
        String rut_caj = jTextRut.getText();
        String nombre_caj = jTextNombre.getText();
        int sucursal = jComboSucursal.getSelectedIndex() + 1;
        clave = seg.encriptar(clave);
        int administrador = jComboAdministrador.getSelectedIndex();
        int estado = jComboEstado.getSelectedIndex();
        Object[] obj = new Object[6];
        obj[0] = rut_caj;
        obj[1] = nombre_caj;
        obj[2] = sucursal;
        obj[3] = administrador;
        obj[4] = estado;
        obj[5] = c.getRut_cajero();
        System.out.println("rut ="+obj[0]);
        System.out.println("nombre ="+obj[1]);
        System.out.println("clave ="+clave);
        System.out.println("sucursal ="+obj[2]);
        System.out.println("administrador ="+obj[3]);
        System.out.println("estado ="+obj[4]);
        System.out.println("rut de where ="+obj[5]);
        /*
        if (dao.actualizar(obj, clave) > 0) {
            JOptionPane.showMessageDialog(null, "Cajero Actualizado correctamente", "Exito!", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "error al Actualizar Cajero", "error!", JOptionPane.ERROR_MESSAGE);
        }
        */

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
        jLRut3 = new javax.swing.JLabel();
        jLRut4 = new javax.swing.JLabel();
        jComboSucursal = new javax.swing.JComboBox<>();
        jCheckClave = new javax.swing.JCheckBox();
        jTextClave = new javax.swing.JPasswordField();
        jLRut5 = new javax.swing.JLabel();
        jLRut6 = new javax.swing.JLabel();
        jComboEstado = new javax.swing.JComboBox<>();
        jComboAdministrador = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jBConfirmar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabelModalidadEntrada.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelModalidadEntrada.setText("<Modo Entrada>");

        jLRut1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut1.setText("Rut");

        jLRut2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut2.setText("Nombre");

        jLRut3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut3.setText("Sucursal");

        jLRut4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut4.setText("Clave");

        jCheckClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckClaveActionPerformed(evt);
            }
        });

        jLRut5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut5.setText("Estado");

        jLRut6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut6.setText("Administrador");

        jComboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Deshabilitar", "Habilitar" }));

        jComboAdministrador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Si" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLRut5)
                                .addComponent(jLRut1)
                                .addComponent(jTextRut)
                                .addComponent(jLRut2)
                                .addComponent(jTextNombre)
                                .addComponent(jLRut3)
                                .addComponent(jComboSucursal, 0, 350, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLRut4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jCheckClave))
                                .addComponent(jTextClave))
                            .addComponent(jLRut6)))
                    .addComponent(jComboAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jLRut3)
                .addGap(18, 18, 18)
                .addComponent(jComboSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLRut5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLRut6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLRut4)
                    .addComponent(jCheckClave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBConfirmar.setText("<Modo Entrada>");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        this.dispose();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConfirmarActionPerformed
        if (MODALIDAD == 0) {
            Agregar();
            this.dispose();
        }
        if (MODALIDAD == 1) {
            if (jTextClave.getPassword().length != 0) {
                String clave = "";
                for (int i = 0; i < jTextClave.getPassword().length; i++) {
                    clave += jTextClave.getPassword()[i];
                }
                Actualizar(clave);
            } else {
                Actualizar();
            }
            this.dispose();
        }
    }//GEN-LAST:event_jBConfirmarActionPerformed

    private void jCheckClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckClaveActionPerformed
        if(jCheckClave.isSelected()){
            jTextClave.setEnabled(true);
            jTextClave.setText("");
        }else{
            jTextClave.setEnabled(false);
            jTextClave.setText("");
        }
    }//GEN-LAST:event_jCheckClaveActionPerformed

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
            java.util.logging.Logger.getLogger(JDCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDCajero dialog = new JDCajero(new javax.swing.JDialog(), true);
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
    private javax.swing.JComboBox<String> jComboAdministrador;
    private javax.swing.JComboBox<String> jComboEstado;
    private javax.swing.JComboBox<String> jComboSucursal;
    private javax.swing.JLabel jLRut1;
    private javax.swing.JLabel jLRut2;
    private javax.swing.JLabel jLRut3;
    private javax.swing.JLabel jLRut4;
    private javax.swing.JLabel jLRut5;
    private javax.swing.JLabel jLRut6;
    private javax.swing.JLabel jLabelModalidadEntrada;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jTextClave;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextRut;
    // End of variables declaration//GEN-END:variables
}
