package CONTROLADOR;

import CONEXION_BD.Conexion;
import MODELOS.ERRORES;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class ErroresDAO implements CRUD {

    private Connection con;
    private Conexion cn = new Conexion();
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List listar() {
        List<ERRORES> lista = new ArrayList<>();
        String sql = "select correlativo_error,fecha_error,nombre_modulo,descripcion_error from ERRORES order by fecha_error desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ERRORES e = new ERRORES();
                e.setCORRELATIVO(rs.getInt("correlativo_error"));
                e.setFecha_error(rs.getDate("fecha_error"));
                e.setNombre_modulo(rs.getString("nombre_modulo"));
                e.setDescripcion_error(rs.getString("descripcion"));                
                lista.add(e);
            }
            con.close();

        } catch (Exception e) {
            System.out.println("error packete CONTROLADOR.ErroresDAO.listar() : = " + e.getMessage());
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        //variable r hace referencia a la respuesta de la consulta sql de ser 1 significa que la sentencia se ejecuto correctamente
        int r = 0;
        String sql ="insert into ERRORES(correlativo_error,fecha_error,nombre_modulo,descripcion_error)values(ISEQ$$_79669.nextval,sysdate-4/24,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            //el correlativo se maneja con una secuencia en la base de datos. este parametro sera omitido
            //la fecha se maneja directamente desde la base de datos. este parametro sera omitido
            ps.setObject(1, o[2]); //modulo
            ps.setObject(2, o[3]); //descripcion
            
        } catch (Exception e) {
            System.out.println("error packete CONTROLADOR.ErroresDAO.add() error : = "+e.getMessage());
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        //funcion no necesaria
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(String id) {
        //funcion no necesaria 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
