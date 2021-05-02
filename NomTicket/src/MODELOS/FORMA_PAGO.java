
package MODELOS;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class FORMA_PAGO {
    
    private int id;
    private String nombre;

    public FORMA_PAGO() {
    }

    public FORMA_PAGO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
