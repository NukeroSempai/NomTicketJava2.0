/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import CONEXION_BD.Conexion;
import MODELOS.PEDIDO_TICKET;
import MODELOS.PRODUCTO;
import MODELOS.TICKET;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nukero
 * @version 1.0
 *
 */
public class VentasDAO implements CRUD {

    //objetos SQL
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    //Objetos controladores
    private ErroresDAO error = new ErroresDAO();
    //FUNCIONES DE CONTROLADOR
    public static final int AGREGAR = 0;
    public static final int MODIFICAR = 1;

    //metodos personalizados
    private void RegistrarError(String modulo, String mensaje) {
        System.out.println("error packete " + modulo + " error : =  " + mensaje);
        Object[] O = new Object[4];
        O[2] = modulo;
        O[3] = mensaje;
        error.add(O);
    }

    //reparar para que traiga el objeto
    public List ListarFormaPago() {
        List<String> lista = new ArrayList<>();
        String sql = "select * from FORMAS_PAGO order by id_forma_pago";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String p = new String();
                p = (rs.getString("nombre_forma_pago"));
                lista.add(p);
            }
            con.close();

        } catch (Exception e) {
            RegistrarError("CONTROLADOR.VentasDAO.ListarFormaPago()", e.getMessage());
        }
        return lista;
    }
    
    public String recuperarFecha(){
        String fecha ="";
        String sql ="select to_char(sysdate-4/24,'dd-mm-yyyy') from dual";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                fecha = (rs.getString(1));                
            }            
            con.close();                                    
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.VentasDAO.RecuperarFecha()", e.getMessage());
        }
        return fecha;
    }

    //metodos de interfaz    
    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into BOLETA (num_boleta,fecha_boleta,hora_venta,valor_total,valor_ticket,saldo_por_pagar,fk_codigo_ticket_id,fk_forma_pago_id,fk_rut_cajero_id) VALUES (?,sysdate-4/24,to_char(sysdate-4/24,'hh24:mi'),?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            //las fechas se asignan automaticamente
            ps.setObject(1, o[0]);//numeroBoleta
            ps.setObject(2, o[1]);//valor_total
            ps.setObject(3, o[2]);//valor_ticket
            ps.setObject(4, o[3]);//salgo_por_pagar
            ps.setObject(5, o[4]);//fk_codigo_ticket //puede ser nulo
            ps.setObject(6, o[5]);//fk_forma_pago
            ps.setObject(7, o[6]);//fk_rut_cajero
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.VentasDAO.add()", e.getMessage().toString());
        }
        return r;
    }
    
    public int addDetalle(Object[] o) {
        int r = 0;
        String sql = "insert into DETALLE_BOLETA (id,cantidad,fk_codigo_producto_id,fk_num_boleta_id) values (ISEQ$$_79710.nextval,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            //las fechas se asignan automaticamente
            ps.setObject(1, o[0]);//cantidad
            ps.setObject(2, o[1]);//fk_producto
            ps.setObject(3, o[2]);//fk_boleta            
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.VentasDAO.addDetalle()", e.getMessage().toString());
        }
        return r;
    }
    
    public TICKET cargarTicket(int codigo){
        TICKET t = new TICKET();
        String sql ="select codigo_ticket,fecha_imp,hora_vig_inicio,hora_vig_termino,estado,valor,fk_tipo_ticket_id from ticket where codigo_ticket = ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {                
                t.setCodigo_ticket(rs.getInt("codigo_ticket"));
                t.setFecha_impresion(rs.getDate("fecha_imp"));
                t.setHora_vig_inicio(rs.getString("hora_vig_inicio"));
                t.setHora_vig_termino(rs.getString("hora_vig_termino"));
                t.setEstado(rs.getInt("estado"));
                t.setValor(rs.getInt("valor"));
                t.setFk_tipo_ticket(rs.getInt("fk_tipo_ticket_id"));                
            }
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.VentasDAO.cargarTicket(int codigo)", e.getMessage());
        }
        return t;
    }
    
    public int DeshabilitarTicket(int codigo){
        int r=0;        
        String sql = "update ticket set estado = 0 where codigo_ticket = ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);            
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.ProductosDAO.actualizar()", e.getMessage());
        }
        return r;        
    }
    
    public List listarPedido(int codigo){
        List<Object[]> lista = new ArrayList<>();
        String sql = "select p.codigo_producto,p.nom_producto,pt.cantidad,pt.cantidad * p.precio from pedido_ticket pt join producto p on pt.fk_codigo_producto_id = p.codigo_producto where fk_num_ticket_id = ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] o = new Object[4];
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getInt(3);
                o[3] = rs.getInt(4);                               
                lista.add(o);                
            }
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.VentasDAO.cargarTicket(int codigo)", e.getMessage());
        }        
        return lista;
    }
    
    public List listarPedidoDato(int codigo){
        List <PEDIDO_TICKET> lista = new ArrayList<>();
        String sql = "select cantidad,fk_codigo_producto_id,fk_num_ticket_id from pedido_ticket where fk_num_ticket_id = ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                PEDIDO_TICKET pt = new PEDIDO_TICKET();
                pt.setCantidad(rs.getInt("codigo_ticket"));
                pt.setFk_codigo_producto(rs.getInt("fk_codigo_producto_id"));
                pt.setFk_num_ticket(rs.getInt("fk_num_ticket_id"));                
                lista.add(pt);                
            }
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.VentasDAO.cargarTicket(int codigo)", e.getMessage());
        }        
        return lista;
    }
    
    public int GenerarIdBoleta(){
        Integer codigoBoleta=null;
        String sql ="select ISEQ$$_79660.nextval from dual";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                codigoBoleta = (rs.getInt(1));                
            }            
            con.close();                                    
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.VentasDAO.GenerarIdBoleta", e.getMessage());
        }
        return codigoBoleta;
    }

    @Override
    public List listar() {
        //listar venta no es necesario por el momento. no se implementara el metodo momentaneamente
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Object[] o) {
        //actualizar venta no es necesario por el momento. no se implementara el metodo momentaneamente
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(String id) {
        //eliminar venta no es necesario por el momento. no se implementara el metodo momentaneamente
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
