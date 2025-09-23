/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author 2dami
 */
public class ImplementsBD implements DAO{        
    	private Connection con;
	private PreparedStatement stmt;
        
        private ResourceBundle configFile;
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String passwordBD;
        
        
       final String SQLGETUSER ="SELECT USERNAME_, PASSWORD_, EDAD_, RESIDENCIA_ FROM USUARIO WHERE USERNAME_ = ?";
       final String SQLCHECK ="SELECT 1 FROM USUARIO WHERE USERNAME_=? AND PASSWORD_=?";
        
        
        public ImplementsBD() {
		this.configFile = ResourceBundle.getBundle("model.configClase");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}
        
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
        
        public boolean checkUser(String username, String password) {
            boolean exists = false;
            this.openConnection();

            try {
                stmt = con.prepareStatement(SQLCHECK);
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                exists = rs.next(); // si hay al menos un resultado → existe

                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al comprobar usuario: " + e.getMessage());
            }

            return exists;
        }
        
        public User showUser(String username) {
            User u = null;
            this.openConnection();

            try {
                stmt = con.prepareStatement("SELECT USERNAME_, PASSWORD_, EDAD_, RESIDENCIA_ FROM USUARIO WHERE USERNAME_=?");
                stmt.setString(1, username);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    u = new User(
                        rs.getString("USERNAME_"),
                        rs.getString("PASSWORD_"),
                        rs.getInt("EDAD_"),
                        rs.getString("RESIDENCIA_")
                    );
                }

                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al mostrar usuario: " + e.getMessage());
            }

            return u; // puede ser null si no existe
        }  

}
