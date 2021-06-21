package CONTROLADOR;

import CONEXION_BD.Conexion;
import MODELOS.INFORME_TICKET;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Nukero
 * @version 1.0
 */
public class InformesDAO implements CRUD {

    //objetos SQL
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    //Objetos controladores
    private ErroresDAO error = new ErroresDAO();
    //FUNCIONES DE CONTROLADOR
    public static final int AGREGAR = 0;
    public static final int ABRIR = 1;
    //FUNCIONES DE CONTROLADOR ADICIONALES 
    public static final int SERVICIOS = 0;
    public static final int PRODUCTOS = 1;
    public static final int VENTAS = 2;
    public static final int TICKET = 3;

    //metodos personalizados
    private void RegistrarError(String modulo, String mensaje) {
        System.out.println("error packete " + modulo + " error : =  " + mensaje);
        Object[] O = new Object[4];
        O[2] = modulo;
        O[3] = mensaje;
        error.add(O);
    }

    //reparar para que traiga el objeto
    public INFORME_TICKET listarCodigo(int codigo) {
        INFORME_TICKET inf = new INFORME_TICKET();
        String sql = "select * from INFORME_TICKET where correlativo_inf=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                inf.setCorrelativo_inf(rs.getInt("correlativo_inf"));
                inf.setFecha_informe(rs.getDate("fecha_informe"));
                inf.setRango_inicio(rs.getDate("rango_inicio"));
                inf.setRango_termino(rs.getDate("rango_termino"));
                inf.setCant_boletas(rs.getInt("cant_boletas"));
                inf.setCant_tickets(rs.getInt("cant_tickets"));
                inf.setTotal_ventas(rs.getInt("total_ventas"));
                inf.setTipo_informe(rs.getString("tipo_informe"));
            }
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.ListarCodigo()", e.getMessage());

        }
        return inf;
    }

    //metodos de interfaz
    @Override
    public List listar() {
        List<INFORME_TICKET> lista = new ArrayList<>();
        String sql = "select * from INFORME_TICKET order by CORRELATIVO_INF desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                INFORME_TICKET inf = new INFORME_TICKET();
                inf.setCorrelativo_inf(rs.getInt("correlativo_inf"));
                inf.setFecha_informe(rs.getDate("fecha_informe"));
                inf.setRango_inicio(rs.getDate("rango_inicio"));
                inf.setRango_termino(rs.getDate("rango_termino"));
                inf.setCant_boletas(rs.getInt("cant_boletas"));
                inf.setCant_tickets(rs.getInt("cant_tickets"));
                inf.setTotal_ventas(rs.getInt("total_ventas"));
                inf.setTipo_informe(rs.getString("tipo_informe"));
                lista.add(inf);
            }
            con.close();

        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.Listar()", e.getMessage());
        }
        return lista;
    }

    //sobrecarga de metodo
    public List listar(Date fecha) {
        List<INFORME_TICKET> lista = new ArrayList<>();
        String sql = "select * from INFORME_TICKET where trunc(fecha_informe)=trunc(?) order by CORRELATIVO_INF desc";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, fecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                INFORME_TICKET inf = new INFORME_TICKET();
                inf.setCorrelativo_inf(rs.getInt("correlativo_inf"));
                inf.setFecha_informe(rs.getDate("fecha_informe"));
                inf.setRango_inicio(rs.getDate("rango_inicio"));
                inf.setRango_termino(rs.getDate("rango_termino"));
                inf.setCant_boletas(rs.getInt("cant_boletas"));
                inf.setCant_tickets(rs.getInt("cant_tickets"));
                inf.setTotal_ventas(rs.getInt("total_ventas"));
                inf.setTipo_informe(rs.getString("tipo_informe"));
                lista.add(inf);
            }
            con.close();

        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.Listar(Date Fecha)", e.getMessage());
        }
        return lista;
    }

    public List generarResumenTicketEmpleado(Date rangoInicio, Date rangoTermino) {
        List<Object[]> lista = new ArrayList<>();
        String sql = "select emp.rut_emp ,emp.nom_emp||' '||emp.appaterno_emp||' '||emp.apmaterno_emp ,"
                + "tur.nombre ,count(tic.fk_codigo_emp_id),SUM(tic.valor)"
                + "from empleado emp join turno tur on tur.id_turno = emp.fk_turno_id join ticket tic on tic.fk_codigo_emp_id = emp.codigo_emp where emp.codigo_emp in (select fk_codigo_emp_id from ticket where fecha_imp BETWEEN trunc(?)and trunc(?))"
                + "and tic.estado=0"
                + "group by emp.rut_emp,emp.nom_emp,emp.appaterno_emp,emp.apmaterno_emp,tur.nombre";
        try {

            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, rangoInicio);
            ps.setDate(2, rangoTermino);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] ob = new Object[5];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getInt(4);
                ob[4] = rs.getInt(5);
                lista.add(ob);
            }
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.generarResumenTicketEmpleado(Date rangoInicio,Date rangoTermino)", e.getMessage());
        }
        return lista;
    }

    public List generarResumen(Date rangoInicio, Date rangoTermino) {
        List<Object[]> lista = new ArrayList<>();
        String sql = "select count(num_boleta),count(fk_codigo_ticket_id),sum(valor_total) from boleta where trunc(fecha_boleta) BETWEEN trunc(?) and trunc(?)";
        try {

            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, rangoInicio);
            ps.setDate(2, rangoTermino);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] ob = new Object[3];
                ob[0] = rs.getInt(1);
                ob[1] = rs.getInt(2);
                ob[2] = rs.getInt(3);
                lista.add(ob);
            }
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.generarResumen(Date rangoInicio,Date rangoTermino)", e.getMessage());
        }
        return lista;
    }

    public List ContarServicios(Date rangoInicio, Date rangoTermino, int Modalidad) {
        String contador = "";
        if (Modalidad == 0) {
            contador = "tpr.nom_tipo_producto";
        }
        if (Modalidad == 1) {
            contador = "pro.nom_producto";
        }

        List<Object[]> lista = new ArrayList<>();
        String sql = "select " + contador + ",count(dtb.fk_codigo_producto_id) from detalle_boleta dtb join producto pro on dtb.fk_codigo_producto_id = pro.codigo_producto join tipo_producto tpr on tpr.id_tipo_producto = pro.fk_tipo_producto_id"
                + "                                                                            where dtb.fk_num_boleta_id in ("
                + "                                                                            select num_boleta from boleta where fecha_boleta BETWEEN trunc(?) and trunc(?)+1"
                + "                                                                            )group by " + contador;
        if (Modalidad == 2) {
            sql = "select to_char(fecha_boleta,'dd/MM/yyyy'),count(trunc(fecha_boleta)) from boleta where fecha_boleta BETWEEN trunc(?) and trunc(? + 1)group by to_char(fecha_boleta,'dd/MM/yyyy') order by 1";
        }

        if (Modalidad == 3) {
            sql = "select to_char(fecha_boleta,'dd/MM/yyyy'),count(fk_codigo_ticket_id) from boleta where fecha_boleta BETWEEN trunc(?) and trunc(? + 1)group by to_char(fecha_boleta,'dd/MM/yyyy') order by 1";
        }
        try {

            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDate(1, rangoInicio);
            ps.setDate(2, rangoTermino);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] ob = new Object[2];
                ob[0] = rs.getString(1);
                ob[1] = rs.getInt(2);
                lista.add(ob);
            }
            con.close();

        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.ContarServicios(Date rangoInicio,Date rangoTermino)", e.getMessage());
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into informe_ticket(correlativo_inf,fecha_informe,rango_inicio,rango_termino,cant_boletas,cant_tickets,total_ventas,tipo_informe)values(ISEQ$$_79707.nextval,sysdate-4/24,?,?,?,?,?,'Personalizado')";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            //el codigo del informe es generado directamente en la base de datos por una secuencia. este dato sera omitido
            ps.setObject(1, o[0]);//rangoInicio
            ps.setObject(2, o[1]);//rangoTermino
            ps.setObject(3, o[2]);//Cant_boletas
            ps.setObject(4, o[3]);//Cant_tickets
            ps.setObject(5, o[4]);//total_ventas            

            r = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.add()", e.getMessage().toString());
        }
        return r;
    }

    private String TituloModalidad(int modalidad) {
        String titulo = "";
        if (modalidad == SERVICIOS) {
            titulo = "Servicio";
        }
        if (modalidad == PRODUCTOS) {
            titulo = "Productos";
        }
        if (modalidad == VENTAS) {
            titulo = "Ventas";
        }
        if (modalidad == TICKET) {
            titulo = "Tickets";
        }
        return titulo;
    }

    public int GenerarInformeBasicoPDF(javax.swing.JTable tabla, String titulo) {
        int r = 0;
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            //ir a escritorio + nombre de archivo
            java.util.Date fecha = new java.util.Date();
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + titulo + new SimpleDateFormat("_ddMMyyyyHHmmss").format(fecha) + ".pdf"));
            //abrir documento pdf
            documento.open();
            //Generar Encabezado
            Image logo = Image.getInstance("src/IMAGENES/logoSmall.png");
            logo.scaleToFit(80, 80);
            logo.setAlignment(Chunk.ALIGN_LEFT);
            Paragraph fechaInforme = new Paragraph();
            fechaInforme.setAlignment(Paragraph.ALIGN_CENTER);
            fechaInforme.add(Chunk.NEWLINE);
            fechaInforme.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.DARK_GRAY));
            fechaInforme.add("Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(fecha) + "\n\n");
            Paragraph tituloInforme = new Paragraph();
            tituloInforme.setAlignment(Paragraph.ALIGN_CENTER);
            tituloInforme.add(Chunk.NEWLINE);
            tituloInforme.setFont(FontFactory.getFont("Tahoma", 24, Font.BOLD, BaseColor.DARK_GRAY));
            tituloInforme.add(titulo + "\n\n\n");
            //crear tabla para encabezado
            PdfPTable encPdf = new PdfPTable(3);
            encPdf.setWidthPercentage(100);
            encPdf.setWidths(new float[]{20, 75, 30});
            encPdf.getDefaultCell().setBorder(0);
            encPdf.setHorizontalAlignment(Element.ALIGN_LEFT);
            //agregar imagen
            encPdf.addCell(logo);
            encPdf.addCell("");
            encPdf.addCell(fechaInforme);
            //agregar imagen y encabezado a documento            
            documento.add(encPdf);
            documento.add(tituloInforme);
            //algoritmo que crea numero de columnas en automatico
            PdfPTable tablaPdf = new PdfPTable(tabla.getColumnCount());
            tablaPdf.setWidthPercentage(100);
            tablaPdf.setHorizontalAlignment(Element.ALIGN_LEFT);
            //escribir nombre de columnas en pdf
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                tablaPdf.addCell(tabla.getColumnName(i));
            }
            //escribir datos de la tabla al pdf
            for (int i = 0; i < tabla.getRowCount(); i++) {
                for (int j = 0; j < tabla.getColumnCount(); j++) {
                    tablaPdf.addCell(tabla.getModel().getValueAt(i, j).toString());
                }
            }
            documento.add(tablaPdf);
            //cerrar documento
            documento.close();
            r = 1;
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.GenerarInformeBasicoPDF(javax.swing.JTtable tabla, String titulo)", e.getMessage().toString());
        }
        return r;
    }

    public int GenerarInformeCompletoPDF(javax.swing.JTable tabla, JFreeChart grafico, String titulo) {
        int r = 0;
        Document documento = new Document();

        try {
            String ruta = System.getProperty("user.home");
            //ir a escritorio + nombre de archivo
            java.util.Date fecha = new java.util.Date();
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + titulo + new SimpleDateFormat("_ddMMyyyyHHmmss").format(fecha) + ".pdf"));
            //abrir documento pdf
            documento.open();
            //Generar Encabezado
            Image logo = Image.getInstance("src/IMAGENES/logoSmall.png");
            logo.scaleToFit(80, 80);
            logo.setAlignment(Chunk.ALIGN_LEFT);
            Paragraph fechaInforme = new Paragraph();
            fechaInforme.setAlignment(Paragraph.ALIGN_CENTER);
            fechaInforme.add(Chunk.NEWLINE);
            fechaInforme.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.DARK_GRAY));
            fechaInforme.add("Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(fecha) + "\n\n");
            Paragraph tituloInforme = new Paragraph();
            tituloInforme.setAlignment(Paragraph.ALIGN_CENTER);
            tituloInforme.add(Chunk.NEWLINE);
            tituloInforme.setFont(FontFactory.getFont("Tahoma", 24, Font.BOLD, BaseColor.DARK_GRAY));
            tituloInforme.add(titulo + "\n\n\n");
            //crear tabla para encabezado
            PdfPTable encPdf = new PdfPTable(3);
            encPdf.setWidthPercentage(100);
            encPdf.setWidths(new float[]{20, 75, 30});
            encPdf.getDefaultCell().setBorder(0);
            encPdf.setHorizontalAlignment(Element.ALIGN_LEFT);
            //agregar imagen
            encPdf.addCell(logo);
            encPdf.addCell("");
            encPdf.addCell(fechaInforme);
            //agregar imagen y encabezado a documento            
            documento.add(encPdf);
            documento.add(tituloInforme);

            //crear titutlo pagina
            Paragraph tituloPagina = new Paragraph();
            tituloPagina.setAlignment(Paragraph.ALIGN_CENTER);
            tituloPagina.add(Chunk.NEWLINE);
            tituloPagina.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            tituloPagina.add(titulo + "\n\n");

            //crear tabla para pagina con grafico
            PdfPTable cuerpoPDF = new PdfPTable(3);
            cuerpoPDF.setWidthPercentage(100);
            cuerpoPDF.setWidths(new float[]{70, 10, 50});
            cuerpoPDF.getDefaultCell().setBorder(0);
            cuerpoPDF.setHorizontalAlignment(Element.ALIGN_LEFT);

            //crear imagen de grafico
            String direccionImagen = "src/IMAGENES/GRAFICOS/image.png";
            ChartUtilities.saveChartAsPNG(new File(direccionImagen), grafico, 600, 800);
            Image imgGrafico = Image.getInstance(direccionImagen);
            imgGrafico.scaleToFit(80, 80);
            imgGrafico.setAlignment(Chunk.ALIGN_LEFT);

            //algoritmo que crea numero de columnas en automatico
            PdfPTable tablaContenidoPdf = new PdfPTable(tabla.getColumnCount());
            tablaContenidoPdf.setWidthPercentage(100);
            tablaContenidoPdf.setHorizontalAlignment(Element.ALIGN_LEFT);

            //escribir nombre de columnas en pdf
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                tablaContenidoPdf.addCell(tabla.getColumnName(i));
            }

            //escribir datos de la tabla al pdf
            for (int i = 0; i < tabla.getRowCount(); i++) {
                for (int j = 0; j < tabla.getColumnCount(); j++) {
                    tablaContenidoPdf.addCell(tabla.getModel().getValueAt(i, j).toString());
                }
            }

            cuerpoPDF.addCell(imgGrafico);
            cuerpoPDF.addCell("");
            cuerpoPDF.addCell(tablaContenidoPdf);

            documento.add(cuerpoPDF);
            documento.newPage();
            //cerrar documento
            documento.close();
            r = 1;
        } catch (Exception e) {
            RegistrarError("CONTROLADOR.InformesDAO.GenerarInformePDF(javax.swing.JTtable tabla,JFreechart chart, String titulo)", e.getMessage().toString());
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        //no se crea el metodo actualizar pues , no se utilizara, aun asi puede ser implementado a futuro sin problemas.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(String id) {
        //no se crea el metodo eliminar pues , no se utilizara, aun asi puede ser implementado a futuro sin problemas.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
