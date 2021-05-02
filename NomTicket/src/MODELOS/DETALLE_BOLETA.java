
package MODELOS;

/**
 *
 * @author Nukero
 * @version 1.0
 * 
 */
public class DETALLE_BOLETA {
    
    private int id;
    private int cantidad;
    private int fk_codigo_producto;
    private int fk_num_boleta;

    public DETALLE_BOLETA() {
    }

    public DETALLE_BOLETA(int id, int cantidad, int fk_codigo_producto, int fk_num_boleta) {
        this.id = id;
        this.cantidad = cantidad;
        this.fk_codigo_producto = fk_codigo_producto;
        this.fk_num_boleta = fk_num_boleta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getFk_codigo_producto() {
        return fk_codigo_producto;
    }

    public void setFk_codigo_producto(int fk_codigo_producto) {
        this.fk_codigo_producto = fk_codigo_producto;
    }

    public int getFk_num_boleta() {
        return fk_num_boleta;
    }

    public void setFk_num_boleta(int fk_num_boleta) {
        this.fk_num_boleta = fk_num_boleta;
    }
    
}
