package SEGURIDAD;

import CONEXION_BD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import javax.swing.JOptionPane;

/**
 *
 * @author Nukero
 * @version 1.0
 *
 * version 1.0 de clase creada para verificar la conexion de los cajeros a la
 * base de datos y la encriptacion de claves usando algoritmo SHA-256
 *
 */
public class Seguridad {

    Connection con;
    Conexion cn = new Conexion();
    CallableStatement st;
    PreparedStatement ps;
    ResultSet rs;

    private String generarHash(String clave) {
        String encriptado = "";
        try {
            con = cn.Conectar();
            st = con.prepareCall("{?=callEncoder(?)}");
            st.registerOutParameter(1, Types.VARCHAR);
            st.setString(2, clave);
            st.execute();
            encriptado = st.getString(1);
            con.close();
        } catch (Exception e) {
            System.out.println("error packete SEGURIDAD.Seguridad.GenerarHash error : = " + e.getMessage());
        }
        return encriptado;
    }

    public String encriptar(String clave) {
        return generarHash(clave);
    }

    public boolean iniciarSesion(String rut, String clave) {
        boolean autorizar = false;
        String usuarioRecuperado = "";
        String ClaveRecuperada = "";
        String ClaveProcesada = generarHash(clave);
        int activo = 0;
        String sql = "select rut_cajero,clave,estado from CAJERO where rut_cajero=?";
        //buscar y recuperar usuario y contraseña
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuarioRecuperado = (rs.getString(1));
                ClaveRecuperada = (rs.getString(2));
                activo = rs.getInt("estado");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("error en Seguridad =" + e.getMessage());
        }
        //comparar si son iguales  
        if ((usuarioRecuperado.equals(rut)) && (ClaveRecuperada.equals(ClaveProcesada)) && activo == 1) {
            autorizar = true;
        }
        if (activo == 0) {
            JOptionPane.showMessageDialog(null, "Usuario Deshabilitado", "error!", JOptionPane.ERROR_MESSAGE);
        }
        return autorizar;
    }

}
