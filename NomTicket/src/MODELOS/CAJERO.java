
package MODELOS;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class CAJERO {
    
    private String rut_cajero;
    private String nombre;
    private String clave;
    private int estado_login;
    private int administrador;
    private int fk_sucursal;

    public CAJERO() {
    }

    public CAJERO(String rut_cajero, String nombre, String clave, int estado_login, int administrador, int fk_sucursal) {
        this.rut_cajero = rut_cajero;
        this.nombre = nombre;
        this.clave = clave;
        this.estado_login = estado_login;
        this.administrador = administrador;
        this.fk_sucursal = fk_sucursal;
    }

    public String getRut_cajero() {
        return rut_cajero;
    }

    public void setRut_cajero(String rut_cajero) {
        this.rut_cajero = rut_cajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado_login() {
        return estado_login;
    }

    public void setEstado_login(int estado_login) {
        this.estado_login = estado_login;
    }

    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public int getFk_sucursal() {
        return fk_sucursal;
    }

    public void setFk_sucursal(int fk_sucursal) {
        this.fk_sucursal = fk_sucursal;
    }
}
