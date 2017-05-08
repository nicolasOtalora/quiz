package edu.co.sergio.mundo.dao;

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

public class DepartamentoDAO implements IBaseDatos<Departamento> {

    public ArrayList recursosProyecto() {

        String nombreProyecto = "";
        int total = 0;
        ArrayList datos = null;

        try {
            String query = "select nom_proy,count(*)as Q from Proyecto left join Recurso "
                    + "using (id_proyecto) group by nom_proy;";
            Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                datos = new ArrayList();

                nombreProyecto = rs.getString("nom_proy");
                total = rs.getInt("Q");
                datos.add(nombreProyecto);
                datos.add(total);
            }

        } catch (SQLException e) {
        }

        return datos;
    }

    public ArrayList proyectosPorDepto() {

        String nombreDepto = "";
        int total = 0;
        ArrayList datos = null;

        try {
            String query = "select nom_depto, count(*) as Num from Depto join DeptoProyecto"
                    + " using(id_depto) join Proyecto using(id_proyecto) group by nom_depto;";
            Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                datos = new ArrayList();

                nombreDepto = rs.getString("nom_depto");
                total = rs.getInt("Num");
                datos.add(nombreDepto);
                datos.add(total);
            }

        } catch (SQLException e) {
        }

        return datos;

    }

    public ArrayList empleadosYContratos() {

        String nombreDepto = "";
        String tipo = "";
        int total = 0;
        ArrayList datos = null;

        try {
            String query = "select nom_depto,tipo_contrato,count(*) as total from (Depto join Empleado"
                    + " using (id_depto)) group by nom_depto,tipo_contrato having count(*)>=1;";
            Connection connection = null;
            try {
                connection = Conexion.getConnection();
            } catch (URISyntaxException ex) {
                Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                datos = new ArrayList();

                nombreDepto = rs.getString("nom_depto");
                tipo = rs.getString("tipo_contrato");
                total = rs.getInt("total");
                datos.add(nombreDepto);
                datos.add(tipo);
                datos.add(total);
            }

        } catch (SQLException e) {
        }
        return datos;
    }

    /**
     * Funcion que permite realizar la insercion de un nuevo registro en la
     * tabla Departamento
     *
     * @param Departamento recibe un objeto de tipo Departamento
     * @return boolean retorna true si la operacion de insercion es exitosa.
     */
    public boolean insert(Departamento t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = " insert into Depto (id_depto,nom_depto)" + " values (?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_departamento());
            preparedStmt.setString(2, t.getNom_departamento());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Funcion que permite realizar la actualizacion de un nuevo registro en la
     * tabla Departamento
     *
     * @param Departamento recibe un objeto de tipo Departamento
     * @return boolean retorna true si la operacion de actualizacion es exitosa.
     */
    public boolean update(Departamento t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "update Depto set nom_depto = ? where id_depto = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNom_departamento());
            preparedStmt.setInt(2, t.getId_departamento());
            if (preparedStmt.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Funcion que permite realizar la eliminario de registro en la tabla
     * Departamento
     *
     * @param Departamento recibe un objeto de tipo Departamento
     * @return boolean retorna true si la operacion de borrado es exitosa.
     */
    public boolean delete(Departamento t) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query = "delete from Depto where id_depto = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_departamento());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Funcion que permite obtener una lista de los departamentos existentes en
     * la base de datos
     *
     * @return List<Departamento> Retorna la lista de Departamentos existentes
     * en la base de datos
     */
    public List<Departamento> findAll() {
        List<Departamento> departamentos = null;
        String query = "SELECT * FROM Depto";
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
        } catch (URISyntaxException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null;

            while (rs.next()) {
                if (departamentos == null) {
                    departamentos = new ArrayList<Departamento>();
                }

                Departamento registro = new Departamento();
                id = rs.getInt("id_depto");
                registro.setId_departamento(id);

                nombre = rs.getString("nom_depto");
                registro.setNom_departamento(nombre);

                departamentos.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Departamentos");
            e.printStackTrace();
        }

        return departamentos;
    }
}
