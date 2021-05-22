package CONTROLADOR;

import CONEXION_BD.Conexion;
import MODELOS.INFORME_TICKET;
import MODELOS.PRODUCTO;
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
public class InformesDAO implements CRUD {

    //objetos SQL
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    //Objetos controladores
    private ErroresDAO error = new ErroresDAO();
    //FUNCIONES DE CONTROLADOR
    public static final int AGREGAR = 0;
    public static final int ABRIR = 1;

    //metodos personalizados
    private void RegistrarError(String modulo,String mensaje) {
        System.out.println("error packete "+ modulo +" error : =  " + mensaje);
        Object[] O = new Object[4];
        O[2] = modulo;
        O[3] = mensaje;
        error.add(O);
    }

    //reparar para que traiga el objeto
    

    public INFORME_TICKET listarCodigo(int codigo) {
        INFORME_TICKET inf = new INFORME_TICKET();
        String sql = "select * from INFORME_TICKET where correlativo_inf=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                inf.setCorrelativo_inf(rs.getInt("correlativo_inf"));
                inf.setFecha_informe(rs.getDate("fecha_informe"));
                inf.setRango_inicio(rs.getDate("rango_inicio"));
                inf.setRango_termino(rs.getDate("rango_termino"));
                inf.setCant_boletas(rs.getInt("cant_boletas"));
                inf.setCant_tickets(rs.getInt("cant_tickets"));
                inf.setTotal_ventas(rs.getInt("total_ventas"));
                inf.setTipo_informe(rs.getString("tipo_informe"));
            }
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.InformesDAO.ListarCodigo()", e.getMessage());

        }
        return inf;
    }

    //metodos de interfaz
    @Override
    public List listar() {
        List<INFORME_TICKET> lista = new ArrayList<>();
        String sql = "select * from INFORME_TICKET order by CORRELATIVO_INF desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                INFORME_TICKET inf = new INFORME_TICKET();
                inf.setCorrelativo_inf(rs.getInt("correlativo_inf"));
                inf.setFecha_informe(rs.getDate("fecha_informe"));
                inf.setRango_inicio(rs.getDate("rango_inicio"));
                inf.setRango_termino(rs.getDate("rango_termino"));
                inf.setCant_boletas(rs.getInt("cant_boletas"));
                inf.setCant_tickets(rs.getInt("cant_tickets"));
                inf.setTotal_ventas(rs.getInt("total_ventas"));
                inf.setTipo_informe(rs.getString("tipo_informe"));
                lista.add(inf);
            }
            con.close();

        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.InformesDAO.Listar()", e.getMessage());
        }
        return lista;
    }
    //sobrecarga de metodo
    public List listar(Date fecha) {
        List<INFORME_TICKET> lista = new ArrayList<>();
        String sql = "select * from INFORME_TICKET where trunc(fecha_informe)=trunc(?) order by CORRELATIVO_INF desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, fecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                INFORME_TICKET inf = new INFORME_TICKET();
                inf.setCorrelativo_inf(rs.getInt("correlativo_inf"));
                inf.setFecha_informe(rs.getDate("fecha_informe"));
                inf.setRango_inicio(rs.getDate("rango_inicio"));
                inf.setRango_termino(rs.getDate("rango_termino"));
                inf.setCant_boletas(rs.getInt("cant_boletas"));
                inf.setCant_tickets(rs.getInt("cant_tickets"));
                inf.setTotal_ventas(rs.getInt("total_ventas"));
                inf.setTipo_informe(rs.getString("tipo_informe"));
                lista.add(inf);
            }
            con.close();

        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.InformesDAO.Listar(Date Fecha)", e.getMessage());
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into producto(codigo_producto,nom_producto,descripcion,fk_tipo_producto_id,precio)values(ISEQ$$_79699.nextval,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            //el codigo del producto es generado directamente en la base de datos por una secuencia. este dato sera omitido
            ps.setObject(1, o[0]);//nombre
            ps.setObject(2, o[1]);//descripcion
            ps.setObject(3, o[2]);//tipo producto
            ps.setObject(4, o[3]);//precio
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.ProductosDAO.add()", e.getMessage().toString());
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update producto set codigo_producto=?, nom_producto=?,descripcion=?,fk_tipo_producto_id=?, precio=? where codigo_producto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.ProductosDAO.actualizar()", e.getMessage());
        }
        return r;
    }

    @Override
    public int eliminar(String id) {
        int r = 0;
        String sql = "delete from producto where codigo_producto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.ProductosDAO.eliminar()", e.getMessage());
        }
        return r;
    }
}
