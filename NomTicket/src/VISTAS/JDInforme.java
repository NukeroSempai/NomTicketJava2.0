package VISTAS;

import CONTROLADOR.InformesDAO;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import MODELOS.EVENTOS;
import MODELOS.PRODUCTO;
import CONTROLADOR.ProductosDAO;
import MODELOS.INFORME_TICKET;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Nukero
 * @version 1.0
 *
 * Formulario creado para guardar y actualizar datos
 *
 */
public class JDInforme extends javax.swing.JDialog {

    private int MODALIDAD;

    private InformesDAO dao = new InformesDAO();
    private INFORME_TICKET p = new INFORME_TICKET();    
    private EVENTOS event = new EVENTOS(); 
    private DefaultTableModel modelo = new DefaultTableModel();

    public JDInforme(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jTCantidadBoletas.setEnabled(false);
        jTCantidadTickets.setEnabled(false);
        jTTotalVentas.setEnabled(false);
    }

    public void setModalidad(int modalidad) {
        this.MODALIDAD = modalidad;
        inicializar();
    }

    public void setProducto(INFORME_TICKET informe) {
        this.p = informe;        
    }

    private void inicializar() {
        if(MODALIDAD==0){//modo agregar informe            
            jLabelTituloInforme1.setText("Generar Informe");
            jBConfirmar.setText("Guardar Informe");
        }else{//modo abrir informe
            //configuracion de formulario
            jLabelTituloInforme1.setText("Informe N° "+p.getCorrelativo_inf());
            jPanelBTNConfirmar.setBackground(new Color(225,139,34));
            jBConfirmar.setVisible(false);
            jBConfirmar.setEnabled(false);
            //carga de datos
            jTCantidadBoletas.setText(""+p.getCant_boletas());
            jTCantidadTickets.setText(""+p.getCant_tickets());
            jTTotalVentas.setText(""+p.getTotal_ventas());
            jDRangoInicio.setDate(new java.util.Date(p.getRango_inicio().toString().replace("-", "/")));
            jDRangoTermino.setDate(new java.util.Date(p.getRango_termino().toString().replace("-", "/")));
            //se desactiva la ediciobn
            abrirInforme();
            cargarTabla(p.getRango_inicio(),p.getRango_termino());     
            
        }     

    }
    private void abrirInforme(){
        jBCargarDatos.setVisible(false);        
        jDRangoInicio.setEnabled(false);
        jDRangoTermino.setEnabled(false);
    }
    
    private void GraficarTabla(){
        DefaultCategoryDataset datosGrafico = new DefaultCategoryDataset();
        for (int i = 0; i < Tabla.getRowCount(); i++) {                        
            datosGrafico.setValue(Integer.parseInt(Tabla.getValueAt(i, 1).toString()),Tabla.getValueAt(i, 0).toString(), "Servicios");
        }
        JFreeChart grafico = ChartFactory.createBarChart3D("Informe", "Venta por Tipo Servicio", "Recuento", datosGrafico, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panel = new ChartPanel(grafico);
        panel.setPreferredSize(JPanelGrafico.getSize());
        
        JPanelGrafico.removeAll();
        JPanelGrafico.setLayout(new BorderLayout());
        
        JPanelGrafico.add(panel,BorderLayout.CENTER);
        JPanelGrafico.validate();
        
        pack();
        JPanelGrafico.repaint();
    }
    
    private void limpiarTabla(DefaultTableModel modelo){
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    private void cargarTabla(java.sql.Date rangoInicio,java.sql.Date rangoTermino){
        limpiarTabla(modelo);
        List<Object[]> lista = dao.ContarServicios(rangoInicio, rangoTermino);
        modelo = (DefaultTableModel) Tabla.getModel();
        Object[] ob = new Object[2];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i)[0];
            ob[1] = lista.get(i)[1];
            modelo.addRow(ob);
        }
        Tabla.setModel(modelo);
        GraficarTabla();
        
    }


    private void Agregar() {
        /*
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
        */
    }

    private void Actualizar() {
        /*
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
        */
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLRut4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLRut6 = new javax.swing.JLabel();
        jDRangoInicio = new com.toedter.calendar.JDateChooser();
        jLRut7 = new javax.swing.JLabel();
        jDRangoTermino = new com.toedter.calendar.JDateChooser();
        jLRut8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLRut9 = new javax.swing.JLabel();
        jLRut10 = new javax.swing.JLabel();
        jLRut11 = new javax.swing.JLabel();
        jTCantidadBoletas = new javax.swing.JTextField();
        jTCantidadTickets = new javax.swing.JTextField();
        jTTotalVentas = new javax.swing.JTextField();
        jBCargarDatos = new javax.swing.JButton();
        jPanelBTNConfirmar = new javax.swing.JPanel();
        jBConfirmar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLRut5 = new javax.swing.JLabel();
        JPanelGrafico = new javax.swing.JPanel();
        jLabelTituloInforme1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jBCancelar1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(225, 139, 34));

        jLRut4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut4.setText("Fechas");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLRut6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLRut6.setText("RANGO DE INICIO");

        jLRut7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLRut7.setText("RANGO DE TERMINO");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDRangoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLRut6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDRangoTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLRut7))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLRut6)
                    .addComponent(jLRut7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDRangoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDRangoTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLRut8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut8.setText("Resumen");

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jLRut9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLRut9.setText("CANTIDAD DE BOLETAS");

        jLRut10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLRut10.setText("CANTIDAD DE TICKETS");

        jLRut11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLRut11.setText("TOTAL DE VENTAS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTCantidadBoletas)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLRut9)
                            .addComponent(jLRut10)
                            .addComponent(jLRut11))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTCantidadTickets, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTTotalVentas))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTCantidadBoletas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLRut10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTCantidadTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLRut11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBCargarDatos.setText("Generar Informe");
        jBCargarDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLRut4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLRut8)
                                .addGap(18, 18, 18)
                                .addComponent(jBCargarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(7, 7, 7))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLRut8)
                    .addComponent(jBCargarDatos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBConfirmar.setText("<MODALIDAD>");
        jBConfirmar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBTNConfirmarLayout = new javax.swing.GroupLayout(jPanelBTNConfirmar);
        jPanelBTNConfirmar.setLayout(jPanelBTNConfirmarLayout);
        jPanelBTNConfirmarLayout.setHorizontalGroup(
            jPanelBTNConfirmarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBTNConfirmarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBTNConfirmarLayout.setVerticalGroup(
            jPanelBTNConfirmarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBTNConfirmarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBConfirmar)
                .addContainerGap())
        );

        jLRut5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLRut5.setText("Grafico");

        JPanelGrafico.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout JPanelGraficoLayout = new javax.swing.GroupLayout(JPanelGrafico);
        JPanelGrafico.setLayout(JPanelGraficoLayout);
        JPanelGraficoLayout.setHorizontalGroup(
            JPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JPanelGraficoLayout.setVerticalGroup(
            JPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLRut5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLRut5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabelTituloInforme1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTituloInforme1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloInforme1.setText("<MODALIDAD ENTRADA>");

        jBCancelar1.setText("VOLVER");
        jBCancelar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBCancelar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBCancelar1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Servicio", "Conteo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelTituloInforme1)
                                .addGap(0, 154, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelBTNConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabelTituloInforme1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBTNConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void jBConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConfirmarActionPerformed
        limpiarTabla(modelo);        
        
        
        /*
        if (MODALIDAD == 0) {
            Agregar();
            this.dispose();
        }
        if (MODALIDAD == 1) {
            Actualizar();
            this.dispose();
        }
        */
    }//GEN-LAST:event_jBConfirmarActionPerformed

    private void jBCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelar1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBCancelar1ActionPerformed

    private void jBCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarDatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBCargarDatosActionPerformed

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
            java.util.logging.Logger.getLogger(JDInforme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDInforme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDInforme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDInforme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDInforme dialog = new JDInforme(new javax.swing.JDialog(), true);
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
    private javax.swing.JPanel JPanelGrafico;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton jBCancelar1;
    private javax.swing.JButton jBCargarDatos;
    private javax.swing.JButton jBConfirmar;
    private com.toedter.calendar.JDateChooser jDRangoInicio;
    private com.toedter.calendar.JDateChooser jDRangoTermino;
    private javax.swing.JLabel jLRut10;
    private javax.swing.JLabel jLRut11;
    private javax.swing.JLabel jLRut4;
    private javax.swing.JLabel jLRut5;
    private javax.swing.JLabel jLRut6;
    private javax.swing.JLabel jLRut7;
    private javax.swing.JLabel jLRut8;
    private javax.swing.JLabel jLRut9;
    private javax.swing.JLabel jLabelTituloInforme1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBTNConfirmar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTCantidadBoletas;
    private javax.swing.JTextField jTCantidadTickets;
    private javax.swing.JTextField jTTotalVentas;
    // End of variables declaration//GEN-END:variables
}
