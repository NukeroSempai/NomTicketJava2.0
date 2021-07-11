/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import CONEXION_BD.Conexion;
import MODELOS.CAMBIO_PRECIO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class CambioPrecioDAO implements CRUD{

    
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
    private void RegistrarError(String modulo,String mensaje) {
        System.out.println("error packete "+ modulo +" error : =  " + mensaje);
        Object[] O = new Object[4];
        O[2] = modulo;
        O[3] = mensaje;
        error.add(O);
    }
    
    
    @Override
    public List listar() {
        List<CAMBIO_PRECIO> lista = new ArrayList<>();
        String sql = "select rut,codigo_producto,nombre_producto,precio_anterior,precio_nuevo,fecha,hora from historial_cambio_precio order by fecha desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CAMBIO_PRECIO p = new CAMBIO_PRECIO();
                p.setRut(rs.getString("rut"));
                p.setCodigo(rs.getInt("codigo_producto"));
                p.setNombre_producto(rs.getString("nombre_producto"));
                p.setPrecio_anterior(rs.getInt("precio_anterior"));
                p.setPrecio_nuevo(rs.getInt("precio_nuevo"));
                p.setFecha(rs.getDate("fecha"));
                p.setHora(rs.getString("hora"));
                
                lista.add(p);
            }
            con.close();

        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CambioPrecioDAO.Listar()", e.getMessage());
        }
        return lista;
    }
    
    public List listar(Date fecha) {
        List<CAMBIO_PRECIO> lista = new ArrayList<>();
        String sql = "select rut,codigo_producto,nombre_producto,precio_anterior,precio_nuevo,fecha,hora from historial_cambio_precio where trunc(fecha)=trunc(?) order by fecha desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, fecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                CAMBIO_PRECIO p = new CAMBIO_PRECIO();
                p.setRut(rs.getString("rut"));
                p.setCodigo(rs.getInt("codigo_producto"));
                p.setNombre_producto(rs.getString("nombre_producto"));
                p.setPrecio_anterior(rs.getInt("precio_anterior"));
                p.setPrecio_nuevo(rs.getInt("precio_nuevo"));
                p.setFecha(rs.getDate("fecha"));
                p.setHora(rs.getString("hora"));
                
                lista.add(p);
            }
            con.close();

        } catch (Exception e) {
            RegistrarError("CONTROLADOR.CambioPrecioDAO.Listar(Date Fecha)", e.getMessage());
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        //no es necesario de programar, pues lo ejecuta la base de datos de forma automatica
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Object[] o) {
        //no es necesario de programar, pues lo ejecuta la base de datos de forma automatica
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(String id) {
        //no es necesario de programar, pues lo ejecuta la base de datos de forma automatica
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
