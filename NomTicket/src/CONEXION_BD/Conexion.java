
package CONEXION_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
        
/**
 * @author Nukero
 * @version 1.0
 * 
 * version 1.0 de clase conexion a java cloud usando jdbc
 * 
 */
public class Conexion {
    
    public static Connection Conectar() throws ClassNotFoundException{
        Connection con = null;
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");            
            con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@168.138.146.58:1521:orcl","BDNOMTICKET","@Portafolio2021");
            if (con !=null){
                return con;
            }
        } catch (SQLException e) {
            System.out.println("error packete CONEXION_BD.Conexion.Conectar error : = "+e.getMessage());
        }
        return con;
    }
}
