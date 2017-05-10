package com.crunchify.jsp.servlet;

import edu.co.sergio.mundo.dao.DepartamentoDAO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.*;

public class ChartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        JFreeChart chart = getChart();
        int width = 500;
        int height = 350;
        ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);

    }

    public JFreeChart getChart() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        //Crear la capa de servicios que se enlace con el DAO

        boolean legend = true;
        boolean tooltips = false;
        boolean urls = false;

        DepartamentoDAO dao = new DepartamentoDAO();
        ArrayList datos = dao.recursosProyecto();

        int n = 0;
        for (int i = 1; i < datos.size(); i+=2) {
            
            int total = (Integer) datos.get(i);
            n += total;
        }
        for (int i = 1; i < datos.size(); i+=2) {

            String nombreProyecto = (String) datos.get(i-1);
            int total = (Integer) datos.get(i);
            
            double porcentaje = ((total*100)/n);
            
            dataset.setValue(nombreProyecto, porcentaje);

        }
        
        
        
        
        JFreeChart chart = ChartFactory.createPieChart("Recursos", dataset, legend, tooltips, urls);

        chart.setBorderPaint(Color.GREEN);
        chart.setBorderStroke(new BasicStroke(5.0f));
        chart.setBorderVisible(true);

        return chart;
    }

}
