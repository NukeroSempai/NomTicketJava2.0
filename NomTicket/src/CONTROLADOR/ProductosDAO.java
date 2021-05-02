package CONTROLADOR;

import CONEXION_BD.Conexion;
import MODELOS.PRODUCTO;
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
public class ProductosDAO implements CRUD {

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

    //reparar para que traiga el objeto
    public List listarTipo() {
        List<String> lista = new ArrayList<>();
        String sql = "select * from TIPO_PRODUCTO order by id_tipo_producto";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String p = new String();
                p = (rs.getString("nom_tipo_producto"));
                lista.add(p);
            }
            con.close();

        } catch (Exception e) {
            RegistrarError("CONTROLADOR.ProductosDAO.ListarTipo()", e.getMessage());            
        }
        return lista;
    }

    public PRODUCTO listarCodigo(int codigo) {
        PRODUCTO p = new PRODUCTO();
        String sql = "select * from PRODUCTO where codigo_producto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setCodigo_producto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setDescripcion(rs.getString(3));
                p.setPrecio(rs.getInt(4));
                p.setFk_tipo_producto(rs.getInt(5));
            }
            con.close();
        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.ProductosDAO.ListarCodigo()", e.getMessage());

        }
        return p;
    }

    //metodos de interfaz
    @Override
    public List listar() {
        List<PRODUCTO> lista = new ArrayList<>();
        String sql = "select * from PRODUCTO order by codigo_producto desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PRODUCTO p = new PRODUCTO();
                p.setCodigo_producto(rs.getInt("codigo_producto"));
                p.setNombre(rs.getString("nom_producto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFk_tipo_producto(rs.getInt("fk_tipo_producto_id"));
                p.setPrecio(rs.getInt("precio"));
                lista.add(p);
            }
            con.close();

        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.ProductosDAO.Listar()", e.getMessage());
        }
        return lista;
    }
    //sobrecarga de metodo
    public List listar(int categoria) {
        List<PRODUCTO> lista = new ArrayList<>();
        String sql = "select * from PRODUCTO where fk_tipo_producto_id = ? order by codigo_producto desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, categoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                PRODUCTO p = new PRODUCTO();
                p.setCodigo_producto(rs.getInt("codigo_producto"));
                p.setNombre(rs.getString("nom_producto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFk_tipo_producto(rs.getInt("fk_tipo_producto_id"));
                p.setPrecio(rs.getInt("precio"));
                lista.add(p);
            }
            con.close();

        } catch (Exception e) {            
            RegistrarError("CONTROLADOR.ProductosDAO.Listar(int categoria)", e.getMessage());
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
