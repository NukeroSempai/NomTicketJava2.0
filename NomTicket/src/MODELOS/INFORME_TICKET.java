
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
    private Date rango_inicio;
    private Date rango_termino;
    private int cant_boletas;
    private int cant_tickets;
    private int total_ventas;
    private String tipo_informe;

    public INFORME_TICKET() {
    }

    public INFORME_TICKET(int correlativo_inf, Date fecha_informe, Date fecha_inicio, Date fecha_termino, int cant_boletas, int cant_tickets, int total_ventas, String tipo_informe) {
        this.correlativo_inf = correlativo_inf;
        this.fecha_informe = fecha_informe;
        this.rango_inicio = fecha_inicio;
        this.rango_termino = fecha_termino;
        this.cant_boletas = cant_boletas;
        this.cant_tickets = cant_tickets;
        this.total_ventas = total_ventas;
        this.tipo_informe = tipo_informe;
    }

    public int getCorrelativo_inf() {
        return correlativo_inf;
    }

    public void setCorrelativo_inf(int correlativo_inf) {
        this.correlativo_inf = correlativo_inf;
    }

    public Date getFecha_informe() {
        return fecha_informe;
    }

    public void setFecha_informe(Date fecha_informe) {
        this.fecha_informe = fecha_informe;
    }

    public Date getRango_inicio() {
        return rango_inicio;
    }

    public void setRango_inicio(Date fecha_inicio) {
        this.rango_inicio = fecha_inicio;
    }

    public Date getRango_termino() {
        return rango_termino;
    }

    public void setRango_termino(Date fecha_termino) {
        this.rango_termino = fecha_termino;
    }

    public int getCant_boletas() {
        return cant_boletas;
    }

    public void setCant_boletas(int cant_boletas) {
        this.cant_boletas = cant_boletas;
    }

    public int getCant_tickets() {
        return cant_tickets;
    }

    public void setCant_tickets(int cant_tickets) {
        this.cant_tickets = cant_tickets;
    }

    public int getTotal_ventas() {
        return total_ventas;
    }

    public void setTotal_ventas(int total_ventas) {
        this.total_ventas = total_ventas;
    }

    public String getTipo_informe() {
        return tipo_informe;
    }

    public void setTipo_informe(String tipo_informe) {
        this.tipo_informe = tipo_informe;
    }
}
