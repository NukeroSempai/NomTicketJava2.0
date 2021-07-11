
package CONEXION_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
        
/**
 * @author Nukero
 * @version 1.1
 * 
 * version 1.0 de clase conexion a java cloud usando jdbc
 * version 1.1 implementacion de parametros para conexion
 */
public class Conexion {
    
    private static String usuario;
    private static String clave;
    
    
    static{
        usuario = "BDNOMTICKET";
        clave ="@Portafolio2021";
    }
    
    
    public static void SetUsuario(String usu,String pass){
        usuario = usu;
        clave = pass;
    }    
    
    public static Connection Conectar() throws ClassNotFoundException{
        Connection con = null;
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");            
            con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@168.138.146.58:1521:orcl",usuario,clave);
            if (con !=null){
                return con;
            }
        } catch (SQLException e) {
            System.out.println("error packete CONEXION_BD.Conexion.Conectar error : = "+e.getMessage());
        }
        return con;
    }
}
