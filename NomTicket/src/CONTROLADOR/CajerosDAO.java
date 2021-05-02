package CONTROLADOR;

import CONEXION_BD.Conexion;
import MODELOS.CAJERO;
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
public class CajerosDAO implements CRUD {

    //objetos SQL
    private Connection con;
    private Conexion cn = new Conexion();
    private PreparedStatement ps;
    private ResultSet rs;
    //Objetos controladores
    private ErroresDAO error = new ErroresDAO();

    //metodos personalizados
    private void RegistrarError(String modulo,String mensaje) {
        System.out.println("error packete "+ modulo +" error : =  " + mensaje);
        Object[] O = new Object[4];
        O[2] = modulo;
        O[3] = mensaje;
        error.add(O);
    }
    
    public boolean VerificarSuperUsuario(String rut) {
        boolean admin = false;
        String sql = "select administrador from CAJERO where rut_cajero =?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();
            while (rs.next()) {
                CAJERO c = new CAJERO();
                c.setAdministrador(rs.getInt("administrador"));
                if (c.getAdministrador() == 1) {
                    admin = true;
                }
            }
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CajerosDAO.VerificarSuperUsuario()", e.getMessage());
        }
        return admin;
    }

    public List listarSucursales() {
        List<String> lista = new ArrayList<>();
        String sql = "select * from SUCURSAL order by id_sucursal";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String p = new String();
                p = (rs.getString("nombre_sucursal"));
                lista.add(p);
            }
            con.close();

        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CajerosDAO.ListarSucursales()", e.getMessage());
        }
        return lista;
    }

    public CAJERO BuscarCajero(String rut) {
        CAJERO cajero = new CAJERO();
        String sql = "select rut_cajero,nombre,estado,administrador,fk_sucursal_id from CAJERO where rut_cajero = ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();
            while (rs.next()) {
                cajero.setRut_cajero(rs.getString("rut_cajero"));
                cajero.setNombre(rs.getString("nombre"));
                cajero.setEstado_login(rs.getInt("estado"));
                cajero.setAdministrador(rs.getInt("administrador"));
                cajero.setFk_sucursal(rs.getInt("fk_sucursal_id"));                
            }
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CajerosDAO.BuscarCajero()", e.getMessage());
        }
        return cajero;
    }

    //metodos de interfaz
    @Override
    public List listar() {
        List<CAJERO> lista = new ArrayList<>();
        String sql = "select rut_cajero,nombre,fk_sucursal_id,administrador,estado from CAJERO order by fk_sucursal_id";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CAJERO c = new CAJERO();
                c.setRut_cajero(rs.getString("rut_cajero"));
                c.setNombre(rs.getString("nombre"));
                c.setFk_sucursal(rs.getInt("fk_sucursal_id"));
                c.setAdministrador(rs.getInt("administrador"));
                c.setEstado_login(rs.getInt("estado"));
                lista.add(c);
            }
            con.close();

        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CajerosDAO.listar()", e.getMessage());
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into CAJERO(rut_cajero,nombre,clave,fk_sucursal_id,administrador,estado)values(?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);//rut
            ps.setObject(2, o[1]);//nombre
            ps.setObject(3, o[2]);//clave
            ps.setObject(4, o[3]);//sucursal
            ps.setObject(5, o[4]);//administrador
            ps.setObject(6, o[5]);//estado            
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CajerosDAO.add()", e.getMessage());
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update CAJERO set rut_cajero=?,nombre=?,fk_sucursal_id=?,administrador=?,estado=?where rut_cajero=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);//rut
            ps.setObject(2, o[1]);//nombre            
            ps.setObject(3, o[2]);//sucursal
            ps.setObject(4, o[3]);//administrador
            ps.setObject(5, o[4]);//estado 
            ps.setObject(6, o[5]);// rut cajero a actualizar
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CajerosDAO.actualizar()", e.getMessage());
        }
        return r;
    }

    //sobrecarga de metodo actualizar. esta diseñado para actualizar los datos del cajero y su contraseña. muy neceasrio para la recuperacion de cuentas 
    public int actualizar(Object[] o, String clave) {
        int r = 0;
        String sql = "update CAJERO set rut_cajero=?,nombre=?,clave=?,fk_sucursal_id=?,administrador=?,estado=?where rut_cajero=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);//rut
            ps.setObject(2, o[1]);//nombre
            ps.setObject(3, clave);//clave
            ps.setObject(4, o[2]);//sucursal
            ps.setObject(5, o[3]);//administrador
            ps.setObject(6, o[4]);//estado 
            ps.setObject(7, o[5]);// rut cajero a actualizar
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CajerosDAO.actualizar(Object,String clave)", e.getMessage());
        }
        return r;
    }

    @Override
    public int eliminar(String id) {
        int r = 0;
        String sql = "delete from CAJERO where rut_cajero=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.CajerosDAO.eliminar()", e.getMessage());
        }
        return r;
    }

}
