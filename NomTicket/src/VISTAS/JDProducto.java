package VISTAS;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import MODELOS.EVENTOS;
import MODELOS.PRODUCTO;
import CONTROLADOR.ProductosDAO;

/**
 *
 * @author Nukero
 * @version 1.0
 *
 * Formulario creado para guardar y actualizar datos
 *
 */
public class JDProducto extends javax.swing.JDialog {

    private int MODALIDAD;

    private ProductosDAO dao = new ProductosDAO();
    private PRODUCTO p = new PRODUCTO();
    private List<String> tipoProd = dao.listarTipo();
    private EVENTOS event = new EVENTOS();    

    public JDProducto(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("IMAGENES/icon2.png"));
        return retValue;
    }

    public void setModalidad(int modalidad) {
        this.MODALIDAD = modalidad;
        inicializar();
    }

    public void setProducto(PRODUCTO producto) {
        this.p = producto;
        cargarProducto();
    }
    private void cargarProducto(){
        jTextNombre.setText(p.getNombre());
        jTextDescripcion.setText(p.getDescripcion());
        jTextPrecio.setText(""+p.getPrecio());
        jComboCategoria.setSelectedIndex(p.getFk_tipo_producto());
    }

    private void inicializar() {
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
        for (int i = 0; i < tipoProd.size(); i++) {
            jComboCategoria.addItem(tipoProd.get(i));
        }
    }

    private void Agregar() {
        String nom_prod = jTextNombre.getText();
        String desc_prod = jTextDescripcion.getText();
        int tipo = jComboCategoria.getSelectedIndex() + 1;
        String precio_prod = jTextPrecio.getText();
        Object[] ob = new Object[4];
        ob[0] = nom_prod;
        ob[1] = desc_prod;
        ob[2] = tipo;
        ob[3] = precio_prod;
        System.out.println(ob[0]);
        System.out.println(ob[1]);
        System.out.println(ob[2]);
        System.out.println(ob[3]);        
        if (dao.add(ob) > 0) {
            JOptionPane.showMessageDialog(null, "Producto Agregado correctamente", "Exito!", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "error al agregar producto", "error!", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void Actualizar() {
        String nom_prod = jTextNombre.getText();
        String desc_prod = jTextDescripcion.getText();
        int tipo = jComboCategoria.getSelectedIndex() + 1;
        int precio_prod = Integer.parseInt(jTextPrecio.getText());
        Object[] obj = new Object[6];
        obj[0] = p.getCodigo_producto();
        obj[1] = nom_prod;
        obj[2] = desc_prod;
        obj[3] = tipo;
        obj[4] = precio_prod;
        obj[5] = p.getCodigo_producto();
        if (dao.actualizar(obj) > 0) {
            JOptionPane.showMessageDialog(null, "Producto Actualizado correctamente", "Exito!", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "error al Actualizar producto", "error!", JOptionPane.ERROR_MESSAGE);
        }

    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelModalidadEntrada = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLRut1 = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jLRut2 = new javax.swing.JLabel();
        jTextDescripcion = new javax.swing.JTextField();
        jLRut3 = new javax.swing.JLabel();
        jTextPrecio = new javax.swing.JTextField();
        jLRut4 = new javax.swing.JLabel();
        jComboCategoria = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jBConfirmar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(225, 139, 34));

        jLabelModalidadEntrada.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelModalidadEntrada.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModalidadEntrada.setText("<Modo Entrada>");

        jLRut1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut1.setText("Nombre Producto");

        jTextNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextNombreKeyTyped(evt);
            }
        });

        jLRut2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut2.setText("Descripción");

        jTextDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextDescripcionKeyTyped(evt);
            }
        });

        jLRut3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut3.setText("Categoria");

        jTextPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPrecioKeyTyped(evt);
            }
        });

        jLRut4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut4.setText("Precio");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLRut1)
                        .addComponent(jTextNombre)
                        .addComponent(jLRut2)
                        .addComponent(jTextDescripcion)
                        .addComponent(jLRut3)
                        .addComponent(jTextPrecio)
                        .addComponent(jComboCategoria, 0, 350, Short.MAX_VALUE))
                    .addComponent(jLRut4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLRut2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLRut3)
                .addGap(18, 18, 18)
                .addComponent(jComboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLRut4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            Actualizar();
            this.dispose();
        }
    }//GEN-LAST:event_jBConfirmarActionPerformed

    private void jTextNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNombreKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_jTextNombreKeyTyped

    private void jTextDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDescripcionKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_jTextDescripcionKeyTyped

    private void jTextPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPrecioKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_jTextPrecioKeyTyped

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
            java.util.logging.Logger.getLogger(JDProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDProducto dialog = new JDProducto(new javax.swing.JDialog(), true);
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
    private javax.swing.JComboBox<String> jComboCategoria;
    private javax.swing.JLabel jLRut1;
    private javax.swing.JLabel jLRut2;
    private javax.swing.JLabel jLRut3;
    private javax.swing.JLabel jLRut4;
    private javax.swing.JLabel jLabelModalidadEntrada;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextDescripcion;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextPrecio;
    // End of variables declaration//GEN-END:variables
}
