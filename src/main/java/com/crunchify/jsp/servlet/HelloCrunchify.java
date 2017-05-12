package com.crunchify.jsp.servlet;
 
import edu.co.sergio.mundo.dao.ObraDeArteDAO;
import edu.co.sergio.mundo.dao.UsuarioDAO;
import edu.co.sergio.mundo.vo.ArtistaVO;
import edu.co.sergio.mundo.vo.Departamento;
import edu.co.sergio.mundo.vo.ObraDeArteVO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
 
/**
 * @author Crunchify.com
 */
 
public class HelloCrunchify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre =request.getParameter("nombre");
        String artista = request.getParameter("artista");
        String descripcion=request.getParameter("descripcion");
        String estilo = request.getParameter("estilo");
        double valor = Double.parseDouble(request.getParameter("valor"));
       
        ArtistaVO art = new ArtistaVO();
        art.setNombre(artista);
        ObraDeArteVO obra= new ObraDeArteVO();
        obra.setNombre(nombre);
        obra.setDescripcion(descripcion);
        obra.setEstilo(estilo);
        obra.setValor(valor);
        obra.setArtista(art);
        
        ObraDeArteDAO dao = new ObraDeArteDAO();
        dao.insert(obra);
        request.setAttribute("departamentos", "OK");
        
        RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
        redireccion.forward(request, response);
        
        
        }public JFreeChart getChart() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        //Crear la capa de servicios que se enlace con el DAO

        boolean legend = true;
        boolean tooltips = false;
        boolean urls = false;

        ObraDeArteDAO dao = new ObraDeArteDAO();
        ArrayList datos = dao.consulta();

        int n = 0;
        for (int i = 1; i < datos.size(); i+=2) {
            
            int total = (Integer) datos.get(i);
            n += total;
        }
        for (int i = 1; i < datos.size(); i+=2) {

            String nombre = (String) datos.get(i-1);
            int total = (Integer) datos.get(i);
            
            double porcentaje = ((total*100)/n);
            
            dataset.setValue(nombre, porcentaje);

        }
        
        
        
        
        JFreeChart chart = ChartFactory.createPieChart("Recursos", dataset, legend, tooltips, urls);

        chart.setBorderPaint(Color.GREEN);
        chart.setBorderStroke(new BasicStroke(5.0f));
        chart.setBorderVisible(true);

        return chart;
    }
}
