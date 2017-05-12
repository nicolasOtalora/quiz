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
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArtistaDAO{

    
    public boolean insert(ArtistaVO t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into artista (user,curriculum,distinciones)" + " values (?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1,t.getUser());
            preparedStmt.setString(2, t.getCurriculum());
            preparedStmt.setString(3, t.getDistinciones());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    
}
