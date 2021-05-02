
package CONTROLADOR;
import java.util.List;
/**
 *
 * @author Nukero
 * @version 1.0
 * 
 * lista de metodos necesarios para todas las clases utilizadas por el programa
 */
public interface CRUD {
    
    public List listar();
    public int add(Object[] o);
    public int actualizar(Object[] o);
    public int eliminar(String id);
    
}
    

