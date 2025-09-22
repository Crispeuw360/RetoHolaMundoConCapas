package model;

/**
 * Clase que proporciona acceso a los datos de usuarios almacenados en archivos
 * Utiliza la clase ImplementsFIle para la implementación real
 * @author 2dami
 */
public class UserDAO_FILES {
    private ImplementsFIle fileImpl;
    
    public UserDAO_FILES() {
        this.fileImpl = new ImplementsFIle();
    }
    
    /**
     * Verifica si un usuario existe y su contraseña es correcta
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return true si las credenciales son correctas, false en caso contrario
     */
    public boolean checkUser(String username, String password) {
        return fileImpl.checkUser(username, password);
    }
    
    /**
     * 
     * Obtiene la información de un usuario
     * @param username Nombre de usuario a buscar
     * @return Objeto User con la información del usuario, o null si no existe
     */
    public User showUser(String username) {
        return fileImpl.showUser(username);
    }
}
