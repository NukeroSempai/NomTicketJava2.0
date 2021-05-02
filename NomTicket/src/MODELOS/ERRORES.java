
package MODELOS;

import java.sql.Date;
/**
 *
 * @author Nukero
 * @version 1.0
 * 
 */
public class ERRORES {
    
    private int CORRELATIVO;
    private Date fecha_error;
    private String nombre_modulo;
    private String descripcion_error;

    public ERRORES() {
    }

    public ERRORES(int CORRELATIVO, Date fecha_error, String nombre_modulo, String descripcion_error) {
        this.CORRELATIVO = CORRELATIVO;
        this.fecha_error = fecha_error;
        this.nombre_modulo = nombre_modulo;
        this.descripcion_error = descripcion_error;
    }

    public int getCORRELATIVO() {
        return CORRELATIVO;
    }

    public void setCORRELATIVO(int CORRELATIVO) {
        this.CORRELATIVO = CORRELATIVO;
    }

    public Date getFecha_error() {
        return fecha_error;
    }

    public void setFecha_error(Date fecha_error) {
        this.fecha_error = fecha_error;
    }

    public String getNombre_modulo() {
        return nombre_modulo;
    }

    public void setNombre_modulo(String nombre_modulo) {
        this.nombre_modulo = nombre_modulo;
    }

    public String getDescripcion_error() {
        return descripcion_error;
    }

    public void setDescripcion_error(String descripcion_error) {
        this.descripcion_error = descripcion_error;
    }
            
}
