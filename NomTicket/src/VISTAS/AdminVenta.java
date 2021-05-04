package VISTAS;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import MODELOS.EVENTOS;
import CONTROLADOR.VentasDAO;
import MODELOS.BOLETA;
import MODELOS.CAJERO;
import MODELOS.PEDIDO_TICKET;
import MODELOS.TICKET;
import java.util.Vector;
import javax.swing.JDialog;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class AdminVenta extends javax.swing.JDialog {

    private VentasDAO dao = new VentasDAO();
    private BOLETA b = new BOLETA();
    private List<String> formaPago = dao.ListarFormaPago();
    private EVENTOS event = new EVENTOS();
    private CAJERO cajero = new CAJERO();
    private TICKET ticket = new TICKET();

    private DefaultTableModel modelo = new DefaultTableModel();

    private int fila;

    JDVenta JDVent;

    public AdminVenta(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();
        jTNumeroBoleta.setText(""+generarNumeroBoleta());
        //jTNumeroBoleta.setText("0");    <----------- comando para hacer pruebas. (borrar boleta 0 previamente antes de ejecutar)
    }

    private Integer generarNumeroBoleta() {
        Integer numero = null;
        numero = dao.GenerarIdBoleta();
        return numero;
    }

    private void inicializar() {
        cargarCombo();
        limpiarTabla();
        jTFecha.setText(dao.recuperarFecha());
    }

    public void setCajero(CAJERO cajero) {
        this.cajero = cajero;
    }

    private void cargarCombo() {
        for (int i = 0; i < formaPago.size(); i++) {
            jComboFormaPago.addItem(formaPago.get(i));
        }
    }

    private void listar() {
        List<Object[]> lista = dao.listarPedido(Integer.parseInt(jTBuscarTicket.getText()));
        modelo = (DefaultTableModel) tabla.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i)[0];
            ob[1] = lista.get(i)[1];
            ob[2] = lista.get(i)[2];
            ob[3] = lista.get(i)[3];
            modelo.addRow(ob);
        }
        tabla.setModel(modelo);
    }

    private void calcularTotal() {
        int tPagar = 0;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tPagar += Integer.parseInt(tabla.getValueAt(i, 3).toString());
        }
        jLSubTotal.setText("" + tPagar);
        jLTotal.setText("" + (Integer.parseInt(jLSubTotal.getText()) - Integer.parseInt(jLDescuento.getText())));
        if (Integer.parseInt(jLTotal.getText()) <= 0) {
            jLTotal.setText("0");
        }
    }

    private void limpiarCampos() {
        jTNumeroBoleta.setText(""+generarNumeroBoleta());
        jTBuscarTicket.setText("");
        jTBuscarTicket.setEnabled(true);
        jTFecha.setText(dao.recuperarFecha());
        jComboFormaPago.setSelectedIndex(0);
        jLDescuento.setText("0");
        jLSubTotal.setText("0");
        jLTotal.setText("0");
        limpiarTabla();
        ticket = new TICKET();

    }

    private void realizarVenta() {
        int vTotal = Integer.parseInt(jLSubTotal.getText());
        int vTicket = Integer.parseInt(jLDescuento.getText());
        int SxPagar = Integer.parseInt(jLTotal.getText());
        Integer cTicket;
        if (!jTBuscarTicket.getText().equals("")) {
            cTicket = Integer.parseInt(jTBuscarTicket.getText());
        } else {
            cTicket = null;
        }
        int fPago = jComboFormaPago.getSelectedIndex() + 1;
        String rCajero = cajero.getRut_cajero();
        Integer nBoleta = Integer.parseInt(jTNumeroBoleta.getText());
        if (vTotal == 0 && vTicket == 0 && SxPagar == 0) {
            JOptionPane.showMessageDialog(null, "Debe Agregar Productos", "error!", JOptionPane.ERROR_MESSAGE);
        } else {
            Object[] boleta = new Object[7];
            boleta[0] = nBoleta;
            boleta[1] = vTotal;
            boleta[2] = vTicket;
            boleta[3] = SxPagar;
            boleta[4] = cTicket;
            boleta[5] = fPago;
            boleta[6] = rCajero;
            if (dao.add(boleta) > 0) {
                guardarDetalle();
                JOptionPane.showMessageDialog(null, "Venta exitosa", "Exito!", JOptionPane.DEFAULT_OPTION);
                if (cTicket != null) {
                    dao.DeshabilitarTicket(cTicket);
                    int saldo = dao.RecuperarSaldo(ticket.getFk_codigo_emp());
                    System.out.println(ticket.getFk_codigo_emp());
                    System.out.println("saldo actual = "+saldo);
                    saldo = saldo - vTicket;
                    System.out.println("saldo actualizado = "+saldo);
                    
                    dao.ActualizarSaldo(saldo, ticket.getFk_codigo_emp());                    
                }
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo completar la operación", "error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarDetalle() {
        Integer nBoleta = Integer.parseInt(jTNumeroBoleta.getText());
        for (int i = 0; i < tabla.getModel().getRowCount(); i++) {
            Object[] o = new Object[3];
            o[0] = tabla.getValueAt(i, 2);
            o[1] = tabla.getValueAt(i, 0);
            o[2] = nBoleta;
            dao.addDetalle(o);
        }
    }

    private void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void cambiarModulo(JDialog dialogo) {
        this.setEnabled(false);
        this.setVisible(false);
        dialogo.setLocationRelativeTo(this);
        dialogo.setDefaultCloseOperation(0);
        dialogo.setVisible(true);
        this.setEnabled(true);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton13 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jBCancelar = new javax.swing.JButton();
        jBRealizarVenta = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jTNumeroBoleta = new javax.swing.JTextField();
        jLRut1 = new javax.swing.JLabel();
        jLRut2 = new javax.swing.JLabel();
        jTFecha = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLRut9 = new javax.swing.JLabel();
        jTBuscarTicket = new javax.swing.JTextField();
        jBCargarTicket = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jBVolver = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLRut10 = new javax.swing.JLabel();
        jBSeleccionarProducto = new javax.swing.JButton();
        jBQuitarProducto = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLRut3 = new javax.swing.JLabel();
        jLSubTotal = new javax.swing.JLabel();
        jLRut5 = new javax.swing.JLabel();
        jLDescuento = new javax.swing.JLabel();
        jLRut7 = new javax.swing.JLabel();
        jLTotal = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLRut = new javax.swing.JLabel();
        jComboFormaPago = new javax.swing.JComboBox<>();

        jButton13.setText("jButton13");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(225, 139, 34));

        jPanel3.setBackground(new java.awt.Color(233, 154, 57));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jBCancelar.setText("CANCELAR");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        jBRealizarVenta.setText("REALIZAR VENTA");
        jBRealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRealizarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBRealizarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBRealizarVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBCancelar)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(233, 154, 57));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(233, 154, 57));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ventas");

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        jTNumeroBoleta.setEditable(false);

        jLRut1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut1.setText("Boleta N°");

        jLRut2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut2.setText("Fecha");

        jTFecha.setEditable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut1)
                .addGap(18, 18, 18)
                .addComponent(jTNumeroBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLRut2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLRut1)
                    .addComponent(jTNumeroBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLRut2))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));

        jLRut9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut9.setText("Ticket N°");

        jBCargarTicket.setText("CARGAR");
        jBCargarTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarTicketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTBuscarTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jBCargarTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLRut9)
                    .addComponent(jTBuscarTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCargarTicket))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBVolver.setText("VOLVER");
        jBVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBVolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));

        jLRut10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut10.setText("Producto");

        jBSeleccionarProducto.setText("SELECCIONAR");
        jBSeleccionarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSeleccionarProductoActionPerformed(evt);
            }
        });

        jBQuitarProducto.setText("QUITAR");
        jBQuitarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBQuitarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut10)
                .addGap(18, 18, 18)
                .addComponent(jBSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jBQuitarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLRut10)
                    .addComponent(jBSeleccionarProducto)
                    .addComponent(jBQuitarProducto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(225, 139, 34));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGENES/logoSmall.png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(233, 154, 57));

        jLRut3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut3.setText("SUB - TOTAL $");

        jLSubTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLSubTotal.setForeground(new java.awt.Color(255, 255, 255));
        jLSubTotal.setText("0");

        jLRut5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut5.setText("DESCUENTO $");

        jLDescuento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLDescuento.setForeground(new java.awt.Color(255, 255, 255));
        jLDescuento.setText("0");

        jLRut7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut7.setText("TOTAL $");

        jLTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLTotal.setForeground(new java.awt.Color(255, 255, 255));
        jLTotal.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLSubTotal)
                .addGap(18, 18, 18)
                .addComponent(jLRut5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLDescuento)
                .addGap(18, 18, 18)
                .addComponent(jLRut7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLRut3)
                    .addComponent(jLSubTotal)
                    .addComponent(jLRut5)
                    .addComponent(jLDescuento)
                    .addComponent(jLRut7)
                    .addComponent(jLTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(233, 154, 57));

        jLRut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut.setText("Forma Pago");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboFormaPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLRut)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboFormaPago)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBVolverActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        limpiarCampos();
        limpiarTabla();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        fila = tabla.getSelectedRow();
    }//GEN-LAST:event_tablaMouseClicked

    private void jBRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRealizarVentaActionPerformed
        realizarVenta();
        limpiarCampos();
    }//GEN-LAST:event_jBRealizarVentaActionPerformed

    private void jBQuitarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBQuitarProductoActionPerformed
        fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila");
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "Eliminar producto?");
            if (respuesta == 0) {
                modelo.removeRow(fila);
                calcularTotal();
            }
        }
    }//GEN-LAST:event_jBQuitarProductoActionPerformed

    private void jBCargarTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarTicketActionPerformed
        if (!jTBuscarTicket.getText().equals("")) {
            ticket = dao.cargarTicket(Integer.parseInt(jTBuscarTicket.getText()));
            jLDescuento.setText("" + ticket.getValor());
            if (ticket.getEstado() != 1) {
                System.out.println("ticket no habilitado");
                JOptionPane.showMessageDialog(null, "Ticket no Valido", "error!", JOptionPane.ERROR_MESSAGE);
            } else {
                if (tabla.getRowCount() > 0) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Mantener pedido de productos?");
                    if (respuesta == 0) {
                        listar();
                        calcularTotal();
                        jTBuscarTicket.setEnabled(false);
                    } else {
                        limpiarTabla();
                        listar();
                        calcularTotal();
                        jTBuscarTicket.setEnabled(false);
                    }
                } else {
                    limpiarTabla();
                    listar();
                    calcularTotal();
                    jTBuscarTicket.setEnabled(false);
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar un codigo", "error!", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jBCargarTicketActionPerformed

    private void jBSeleccionarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSeleccionarProductoActionPerformed
        JDVent = new JDVenta(new javax.swing.JDialog(), true);
        cambiarModulo(JDVent);
    }//GEN-LAST:event_jBSeleccionarProductoActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        modelo = (DefaultTableModel) tabla.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < VentasDAO.CarroCompra.size(); i++) {
            ob[0] = VentasDAO.CarroCompra.get(i)[0];
            ob[1] = VentasDAO.CarroCompra.get(i)[1];
            ob[2] = VentasDAO.CarroCompra.get(i)[2];
            ob[3] = VentasDAO.CarroCompra.get(i)[3];
            modelo.addRow(ob);
        }
        tabla.setModel(modelo);
        VentasDAO.CarroCompra.clear();
        calcularTotal();
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(AdminVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdminVenta dialog = new AdminVenta(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton jBCargarTicket;
    private javax.swing.JButton jBQuitarProducto;
    private javax.swing.JButton jBRealizarVenta;
    private javax.swing.JButton jBSeleccionarProducto;
    private javax.swing.JButton jBVolver;
    private javax.swing.JButton jButton13;
    private javax.swing.JComboBox<String> jComboFormaPago;
    private javax.swing.JLabel jLDescuento;
    private javax.swing.JLabel jLRut;
    private javax.swing.JLabel jLRut1;
    private javax.swing.JLabel jLRut10;
    private javax.swing.JLabel jLRut2;
    private javax.swing.JLabel jLRut3;
    private javax.swing.JLabel jLRut5;
    private javax.swing.JLabel jLRut7;
    private javax.swing.JLabel jLRut9;
    private javax.swing.JLabel jLSubTotal;
    private javax.swing.JLabel jLTotal;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTBuscarTicket;
    private javax.swing.JTextField jTFecha;
    private javax.swing.JTextField jTNumeroBoleta;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
