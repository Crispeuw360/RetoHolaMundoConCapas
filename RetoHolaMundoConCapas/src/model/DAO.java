/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Interfaz DAO genérica para operaciones de usuario
 * @author 2dami
 */
public interface DAO {
    
    /**
     * Verifica si un usuario existe y su contraseña es correcta
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return true si las credenciales son correctas, false en caso contrario
     */
    boolean checkUser(String username, String password);
    
    /**
     * Obtiene la información de un usuario
     * @param username Nombre de usuario a buscar
     * @return Objeto User con la información del usuario, o null si no existe
     */
    User showUser(String username);
}
