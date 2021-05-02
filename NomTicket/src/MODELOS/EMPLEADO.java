
package MODELOS;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class EMPLEADO {
    
    private int codigo_emp; 
    private String rut_emp;
    private String nombre;
    private String appaterno;
    private String apmaterno;
    private String clave;
    private int saldo;
    private String email;
    private int fk_empresa;
    private int fk_perfil;
    private int fk_turno;

    public EMPLEADO() {
    }

    public EMPLEADO(int codigo_emp, String rut_emp, String nombre, String appaterno, String apmaterno, String clave, int saldo, String email, int fk_empresa, int fk_perfil, int fk_turno) {
        this.codigo_emp = codigo_emp;
        this.rut_emp = rut_emp;
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.clave = clave;
        this.saldo = saldo;
        this.email = email;
        this.fk_empresa = fk_empresa;
        this.fk_perfil = fk_perfil;
        this.fk_turno = fk_turno;
    }

    public int getCodigo_emp() {
        return codigo_emp;
    }

    public void setCodigo_emp(int codigo_emp) {
        this.codigo_emp = codigo_emp;
    }

    public String getRut_emp() {
        return rut_emp;
    }

    public void setRut_emp(String rut_emp) {
        this.rut_emp = rut_emp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFk_empresa() {
        return fk_empresa;
    }

    public void setFk_empresa(int fk_empresa) {
        this.fk_empresa = fk_empresa;
    }

    public int getFk_perfil() {
        return fk_perfil;
    }

    public void setFk_perfil(int fk_perfil) {
        this.fk_perfil = fk_perfil;
    }

    public int getFk_turno() {
        return fk_turno;
    }

    public void setFk_turno(int fk_turno) {
        this.fk_turno = fk_turno;
    }
    
}
