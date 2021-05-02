
package MODELOS;

import java.sql.Date;
/**
 *
 * @author Nukero
 * @version 1.0
 */
public class INFORME_TICKET {
    
    private int correlativo_inf;
    private Date fecha_informe;
    private Date fecha_inicio;
    private Date fecha_termino;
    private int cant_boletas;
    private int cant_tickets;
    private int total_ventas;
    private int fk_tipo_servicio;
}
