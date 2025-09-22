/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

/**
 *
 * @author 2dami
 */
public class DBImplementation implements UserDAO{
    	private Connection con;
	private PreparedStatement stmt;
        
        private ResourceBundle configFile;
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String passwordBD;
        
        
        public DBImplementation() {
		this.configFile = ResourceBundle.getBundle("configClase");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

    @Override
    public void example_method() {
        System.out.println("Prueba");; //To change body of generated methods, choose Tools | Templates.
    }
        
    
    
}
