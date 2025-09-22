/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import model.UserDAO_FILES;

/**
 *
 * @author 2dami
 */
public class Controller {
    private UserDAO_FILES userDAO;
    
    public Controller() {
        this.userDAO = new UserDAO_FILES();
    }
    
    /**
     * Verifica si un usuario existe y su contraseña es correcta
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return true si las credenciales son correctas, false en caso contrario
     */
    public boolean checkUserFile(String username, String password) {
        return userDAO.checkUser(username, password);
    }
    
    /**
     * Obtiene la información de un usuario
     * @param username Nombre de usuario a buscar
     * @return Objeto User con la información del usuario, o null si no existe
     */
    public User showUserFile(String username) {
        return userDAO.showUser(username);
    }
    
    public void visualizarPantalla() {
      /*  WindowLogin ven = new WindowLogin(this);
        ven.setVisible(true);*/
    }
}
