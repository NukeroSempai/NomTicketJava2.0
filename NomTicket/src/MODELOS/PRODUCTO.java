
package MODELOS;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class PRODUCTO {
    
    private int codigo_producto;
    private String nombre;
    private String descripcion;
    private int precio;
    private int fk_tipo_producto;

    public PRODUCTO() {
    }

    public PRODUCTO(int codigo_producto, String nombre, String descripcion, int precio, int fk_tipo_producto) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fk_tipo_producto = fk_tipo_producto;
    }

    public int getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(int codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getFk_tipo_producto() {
        return fk_tipo_producto;
    }

    public void setFk_tipo_producto(int fk_tipo_producto) {
        this.fk_tipo_producto = fk_tipo_producto;
    }
    
}
