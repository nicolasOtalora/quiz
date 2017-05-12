package edu.co.sergio.mundo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.co.sergio.mundo.vo.Departamento;
import edu.co.sergio.mundo.vo.UsuarioVO;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    /**
     * Funcion que permite realizar la insercion de un nuevo registro en la
     * tabla Departamento
     *
     * @param Departamento recibe un objeto de tipo Departamento
     * @return boolean retorna true si la operacion de insercion es exitosa.
     */
    public boolean insert(UsuarioVO t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into Depto (user,nombre,tipo)" + " values (?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getUser());
            preparedStmt.setString(2, t.getNombre());
            preparedStmt.setString(3, t.getTipo());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

   
}
