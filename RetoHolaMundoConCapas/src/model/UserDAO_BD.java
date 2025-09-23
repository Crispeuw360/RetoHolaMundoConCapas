/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 2dami
 */
public class UserDAO_BD{
        private final ImplementsBD dbImpl;

    public UserDAO_BD() {
        this.dbImpl = new ImplementsBD(); // Usa tu ResourceBundle interno
    }

    /**
     * Verifica si un usuario existe y su contraseña es correcta
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return true si las credenciales son correctas, false en caso contrario
     */
    public boolean checkUser(String username, String password) {
        return dbImpl.checkUser(username, password);
    }

    /**
     * Obtiene la información de un usuario
     * @param username Nombre de usuario a buscar
     * @return Objeto User con la información del usuario, o null si no existe
     */
    public User showUser(String username) {
        return dbImpl.showUser(username);
    }
}
