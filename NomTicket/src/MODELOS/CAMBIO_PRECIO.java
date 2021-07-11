/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOS;

import java.sql.Date;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class CAMBIO_PRECIO {
    
    private String rut;
    private int codigo;
    private String nombre_producto;
    private int precio_anterior;
    private int precio_nuevo;
    private Date fecha;
    private String hora;

    public CAMBIO_PRECIO() {
    }

    public CAMBIO_PRECIO(String rut, int codigo, String nombre_producto, int precio_anterior, int precio_nuevo, Date fecha, String hora) {
        this.rut = rut;
        this.codigo = codigo;
        this.nombre_producto = nombre_producto;
        this.precio_anterior = precio_anterior;
        this.precio_nuevo = precio_nuevo;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getPrecio_anterior() {
        return precio_anterior;
    }

    public void setPrecio_anterior(int precio_anterior) {
        this.precio_anterior = precio_anterior;
    }

    public int getPrecio_nuevo() {
        return precio_nuevo;
    }

    public void setPrecio_nuevo(int precio_nuevo) {
        this.precio_nuevo = precio_nuevo;
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
    
}
