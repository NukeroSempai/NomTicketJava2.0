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
        String rutRecuperado = "";
        String ClaveRecuperada = "";
        String ClaveProcesada = generarHash(clave);
        String usuario ="";
        int activo = 0;
        String sql = "select rut_cajero,clave,estado,usuario from vista_credenciales where rut_cajero=?";
        Conexion.SetUsuario("CONSULTA","@Portafolio2021");
        //buscar y recuperar usuario y contraseña
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();
            while (rs.next()) {
                rutRecuperado = (rs.getString(1));
                ClaveRecuperada = (rs.getString(2));
                activo = rs.getInt("estado");
                usuario = (rs.getString(4));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("error en Seguridad =" + e.getMessage());
        }
        //comparar si son iguales  
        if ((rutRecuperado.equals(rut)) && (ClaveRecuperada.equals(ClaveProcesada)) && activo == 1) {
            autorizar = true;
            Conexion.SetUsuario(usuario, clave);
        }
        if ((rutRecuperado.equals(rut)) && (ClaveRecuperada.equals(ClaveProcesada)) && activo == 0) {
            JOptionPane.showMessageDialog(null, "Usuario Deshabilitado", "error!", JOptionPane.ERROR_MESSAGE);
        }
        return autorizar;
    }

    public boolean verificarRUT(String rut) {
        boolean resultado = false;
        char[] captura = rut.toCharArray();
        char[] procesar = new char[8];
        char[] inv_rut = new char[8];
        int largo = 8;
        //se ejecuta dentro de un try por si algun "usuario" se le ocurre poner alguna letra entre medio
        try {
            //copiar array sin incluir guion o verificador
            for (int i = 0; i < 8; i++) {
                procesar[i] = captura[i];
            }
            //invertir rut
            for (int i = 0; i < procesar.length; i++) {
                inv_rut[largo - 1] = procesar[i];
                largo -= 1;
            }
            int regla = 2;
            int suma = 0;
            //multiplicar rut invertido po (2,3,4,5,6,7,2,3)
            for (int i = 0; i < inv_rut.length; i++) {
                suma += (Integer.parseInt(String.valueOf(inv_rut[i])) * regla);                
                if (regla == 7) {
                    regla = 1;
                }
                regla += 1;
            }
            //sacar modulo de 11
            int resto = suma % 11;            
            String digito = String.valueOf(11 - resto);
            //regla de digito verificador
            if (digito.equals("11")) {
                digito = "0";
            }
            if (digito.equals("10")) {
                digito = "k";
            }
            //terminar de validar rut
            
            if (digito.charAt(0)==rut.charAt(rut.length()-1)) {
                resultado = true;
                System.out.println("rut valido");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }

        return resultado;
    }

}
