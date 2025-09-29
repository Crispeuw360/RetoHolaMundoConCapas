/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DAO;
import model.Factory_BD;
import model.User;
import model.Factory_FILES;

/**
 *
 * @author 2dami
 */
public class Controller {
    Factory_FILES factoryFile = new Factory_FILES();
    DAO daoFile = factoryFile.abrirImplementacion();
    
    Factory_BD factoryBD = new Factory_BD();
    DAO daoBD = factoryBD.abrirImplementacion();
    
    /**
     * Verifica si un usuario existe y su contraseña es correcta
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return true si las credenciales son correctas, false en caso contrario
     */
    public boolean checkUserFile(String username, String password) {
        return daoFile.checkUser(username, password);
    }
    
    /**
     * Obtiene la información de un usuario
     * @return Objeto User con la información del usuario, o null si no existe
     */
    public User showUserFile(String username) {
        return daoFile.showUser(username);
    }
    /**
     * Obtiene la información de un usuario desde la base de datos.
     *
     * @param username Nombre de usuario a buscar
     * @return Objeto User con la información del usuario, o null si no existe
     */
    public User showUserBD (String username){
       return daoBD.showUser(username);
    }
    /**
     * Verifica si un usuario existe y su contraseña es correcta en la base de datos.
     *
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return true si las credenciales son correctas, false en caso contrario
     */
    public boolean checkUserBD(String username,String password){
        return daoBD.checkUser(username, password);
    }
}
