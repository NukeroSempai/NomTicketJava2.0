
package MODELOS;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class TIPO_TICKET {
    
    private int id;
    private String descripcion;

    public TIPO_TICKET() {
    }

    public TIPO_TICKET(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
