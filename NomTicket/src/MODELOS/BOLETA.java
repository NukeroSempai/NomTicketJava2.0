
package MODELOS;

import java.sql.Date;

/**
 *
 * @author Nukero
 * @version 1.0
 * tipos de datos sacados de la base de datos
 */
public class BOLETA {
    
    private int num_boleta;
    private Date fecha;
    private String hora;
    private int valor_total;
    private int valor_ticket;
    private int saldo_por_pagar;
    private int fk_codigo_ticket;
    private int fk_forma_pago;
    private String fk_rut_cajero;

    public BOLETA() {
    }

    public BOLETA(int num_boleta, Date fecha, String hora, int valor_total, int valor_ticket, int saldo_por_pagar, int fk_codigo_ticket, int fk_forma_pago, String fk_rut_cajero) {
        this.num_boleta = num_boleta;
        this.fecha = fecha;
        this.hora = hora;
        this.valor_total = valor_total;
        this.valor_ticket = valor_ticket;
        this.saldo_por_pagar = saldo_por_pagar;
        this.fk_codigo_ticket = fk_codigo_ticket;
        this.fk_forma_pago = fk_forma_pago;
        this.fk_rut_cajero = fk_rut_cajero;
    }

    public int getNum_boleta() {
        return num_boleta;
    }

    public void setNum_boleta(int num_boleta) {
        this.num_boleta = num_boleta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }

    public int getValor_ticket() {
        return valor_ticket;
    }

    public void setValor_ticket(int valor_ticket) {
        this.valor_ticket = valor_ticket;
    }

    public int getSaldo_por_pagar() {
        return saldo_por_pagar;
    }

    public void setSaldo_por_pagar(int saldo_por_pagar) {
        this.saldo_por_pagar = saldo_por_pagar;
    }

    public int getFk_codigo_ticket() {
        return fk_codigo_ticket;
    }

    public void setFk_codigo_ticket(int fk_codigo_ticket) {
        this.fk_codigo_ticket = fk_codigo_ticket;
    }

    public int getFk_forma_pago() {
        return fk_forma_pago;
    }

    public void setFk_forma_pago(int fk_forma_pago) {
        this.fk_forma_pago = fk_forma_pago;
    }

    public String getFk_rut_cajero() {
        return fk_rut_cajero;
    }

    public void setFk_rut_cajero(String fk_rut_cajero) {
        this.fk_rut_cajero = fk_rut_cajero;
    }
}
