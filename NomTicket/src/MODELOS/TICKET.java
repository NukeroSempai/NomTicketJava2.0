
package MODELOS;

import java.sql.Date;
/**
 *
 * @author Nukero
 * @version 1.0
 */
public class TICKET {
    
    private int codigo_ticket;
    private Date fecha_impresion;
    private String hora_vig_inicio;
    private String hora_vig_termino;
    private int estado;
    private int valor;
    private String comentario;
    private int fk_codigo_emp;
    private int fk_tipo_ticket;

    public TICKET() {
    }

    public TICKET(int codigo_ticket, Date fecha_impresion, String hora_vig_inicio, String hora_vig_termino, int estado, int valor, String comentario, int fk_codigo_emp, int fk_tipo_ticket) {
        this.codigo_ticket = codigo_ticket;
        this.fecha_impresion = fecha_impresion;
        this.hora_vig_inicio = hora_vig_inicio;
        this.hora_vig_termino = hora_vig_termino;
        this.estado = estado;
        this.valor = valor;
        this.comentario = comentario;
        this.fk_codigo_emp = fk_codigo_emp;
        this.fk_tipo_ticket = fk_tipo_ticket;
    }

    public int getCodigo_ticket() {
        return codigo_ticket;
    }

    public void setCodigo_ticket(int codigo_ticket) {
        this.codigo_ticket = codigo_ticket;
    }

    public Date getFecha_impresion() {
        return fecha_impresion;
    }

    public void setFecha_impresion(Date fecha_impresion) {
        this.fecha_impresion = fecha_impresion;
    }

    public String getHora_vig_inicio() {
        return hora_vig_inicio;
    }

    public void setHora_vig_inicio(String hora_vig_inicio) {
        this.hora_vig_inicio = hora_vig_inicio;
    }

    public String getHora_vig_termino() {
        return hora_vig_termino;
    }

    public void setHora_vig_termino(String hora_vig_termino) {
        this.hora_vig_termino = hora_vig_termino;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getFk_codigo_emp() {
        return fk_codigo_emp;
    }

    public void setFk_codigo_emp(int fk_codigo_emp) {
        this.fk_codigo_emp = fk_codigo_emp;
    }

    public int getFk_tipo_ticket() {
        return fk_tipo_ticket;
    }

    public void setFk_tipo_ticket(int fk_tipo_ticket) {
        this.fk_tipo_ticket = fk_tipo_ticket;
    }
    
    
}
