package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.vo.ArtistaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.co.sergio.mundo.vo.Departamento;
import edu.co.sergio.mundo.vo.ObraDeArteVO;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObraDeArteDAO{

    
    public boolean insert(ObraDeArteVO t) 
  {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into obra_de_arte (nombre,descripcion,estilo,valor,user)" + " values (?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1,t.getNombre());
            preparedStmt.setString(2, t.getDescripcion());
            preparedStmt.setString(3, t.getEstilo());
            preparedStmt.setDouble(4, t.getValor());
            preparedStmt.setString(5, t.getArtista().getUser());
            
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList consulta() {

        String nombre = "";
        double valor = 0;
        ArrayList datos = null;

        try {
            String query = "select nombre, sum(valor) as valor from artista"
                    + " join obra_de_arte group by nombre having sum >10000;";
            Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(ObraDeArteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                datos = new ArrayList();

                nombre = rs.getString("nombre");
                valor = rs.getDouble("valor");
                datos.add(nombre);
                datos.add(valor);
            }

        } catch (SQLException e) {
        }

        return datos;
    }

    
}
