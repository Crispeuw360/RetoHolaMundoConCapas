/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Implementation of the WorkerDAO interface that provides database access
 * operations using JDBC.
 * 
 * <p>This class handles all database interactions for the application,
 including CRUD operations for workers, units, clients, and dealerships.</p>
 *
 * @author All
 * @version 1.0
 */
public class ImplementsBD implements DAO {
    // Atributos
    private Connection con;
    private PreparedStatement stmt;

    // Los siguientes atributos se utilizan para recoger los valores del fich de
    // configuraci n
    private ResourceBundle configFile;
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;

    // SQL queries
    final String SQLINSERTUNIT = "INSERT INTO unit (id, acronim, title, evaluation, description) VALUES(?,?,?,?,?);";
    final String SQLINSERTSESSION = "INSERT INTO sessionE  (Esession, descripcion, Edate, course) VALUES  (?,?,?,?);";
    final String SQLINSERTSTATEMENT = "INSERT INTO statement  (id,description,Dlevel,available,path,Esession) VALUES  (?,?,?,?,?,?);";
    final String SQLINSERTUNIT_STATEMENT = "INSERT INTO unit_statement(idU,idS)   VALUES  (?,?);";
    
    final String SQLVIEWSTATEMENTBYID = "SELECT * FROM statement WHERE id IN(SELECT idS FROM unit_statement WHERE idU IN(SELECT id FROM unit WHERE id=?));";
    final String SQLGETSESSIONFROMSTATEMENT = "SELECT Esession FROM statement WHERE id=?;";


    /**
     * Constructs a new ImplementsBD instance and loads database configuration.
     */
    public ImplementsBD() {
        this.configFile = ResourceBundle.getBundle("model.configClase");
        this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    /**
     * Opens a connection to the database using configured parameters.
     */
    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
        } catch (SQLException e) {
            System.out.println("Error al intentar abrir la BD");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean checkUser(String username, String password) {
        return false;
    }

    @Override
    public User showUser(String username) {
        return null;
    }
}
