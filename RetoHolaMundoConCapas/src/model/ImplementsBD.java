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
 * Implementación de {@link DAO} que accede a los datos de usuarios mediante JDBC.
 * Gestiona la conexión a la base de datos y ejecuta consultas para validar
 * credenciales y recuperar la información de un usuario.
 *
 * Los parámetros de conexión se leen desde el fichero de propiedades
 * {@code configClase.properties} disponible en el classpath.
 *
 * Campos relevantes:
 * - {@code urlBD}, {@code userBD}, {@code passwordBD}: configuración de conexión
 * - {@code SQLCHECK}: consulta para validar credenciales
 * - {@code SQLGETUSER}: consulta para recuperar un usuario por username
 *
 * Nota: Esta clase no gestiona un pool de conexiones; abre y cierra conexiones
 * por operación.
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
        
        /** Consulta para recuperar un usuario por nombre. */
       final String SQLGETUSER ="SELECT USERNAME_, PASSWORD_, EDAD_, RESIDENCIA_ FROM USUARIO WHERE USERNAME_ = ?";
       /** Consulta para validar credenciales de usuario. */
       final String SQLCHECK ="SELECT 1 FROM USUARIO WHERE USERNAME_=? AND PASSWORD_=?";
        
        
        /**
         * Constructor que inicializa la configuración de conexión a BD
         * leyendo valores del fichero {@code configClase.properties}.
         */
        public ImplementsBD() {
		this.configFile = ResourceBundle.getBundle("configClase");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}
        
        /**
         * Abre una conexión JDBC con la base de datos usando la configuración cargada.
         * En caso de error, escribe el detalle en la salida estándar.
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
        
        /**
         * Verifica si existe un usuario con las credenciales proporcionadas.
         *
         * @param username Nombre de usuario a comprobar
         * @param password Contraseña a comprobar
         * @return {@code true} si las credenciales son válidas; {@code false} en caso contrario
         */
        @Override
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
        
        /**
         * Recupera un usuario por su nombre de usuario.
         *
         * @param username Nombre de usuario a buscar
         * @return instancia de {@link User} si existe; {@code null} si no se encuentra o si ocurre un error
         */
        @Override
        public User showUser(String username) {
            User u = null;
            this.openConnection();

            try {
                stmt = con.prepareStatement(SQLGETUSER);
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
