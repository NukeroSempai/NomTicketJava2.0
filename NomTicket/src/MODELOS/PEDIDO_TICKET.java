
package MODELOS;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class PEDIDO_TICKET {
    
    private int id;;
    private int cantidad;
    private int fk_codigo_producto;
    private int fk_num_ticket;

    public PEDIDO_TICKET() {
    }

    public PEDIDO_TICKET(int id, int cantidad, int fk_codigo_producto, int fk_num_ticket) {
        this.id = id;
        this.cantidad = cantidad;
        this.fk_codigo_producto = fk_codigo_producto;
        this.fk_num_ticket = fk_num_ticket;
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

    public int getFk_num_ticket() {
        return fk_num_ticket;
    }

    public void setFk_num_ticket(int fk_num_ticket) {
        this.fk_num_ticket = fk_num_ticket;
    }
    
}
