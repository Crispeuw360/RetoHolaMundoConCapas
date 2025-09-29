package model;

import java.io.*;

/**
 * Implementación de DAO para manejar usuarios usando Object Input/Output
 * 
 * @author 2dami
 */
 public class ImplementsFile implements DAO {
    private File USERS_FILE = new File("users.dat");

    /**
     * Verifica si un usuario con el nombre de usuario y la contraseña proporcionados existe en el archivo.
     * Si el archivo no existe, se crea con datos de ejemplo mediante {@link #addDataUser()}.
     *
     * @param username Nombre de usuario a verificar
     * @param password Contraseña a verificar
     * @return true si las credenciales coinciden con un usuario almacenado; false en caso contrario o si ocurre un error de E/S
     */
    @Override
    public boolean checkUser(String username, String password) {
        if(!USERS_FILE.exists()){
            addDataUser();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            User user = (User) ois.readObject();
            return user.getUsername().equals(username) && user.getPassword().equals(password);
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Busca y devuelve un usuario por su nombre de usuario desde el archivo de datos.
     * Si el archivo no existe, se inicializa con datos de ejemplo mediante {@link #addDataUser()}.
     *
     * @param username Nombre de usuario a buscar
     * @return El objeto {@code User} si se encuentra; {@code null} si no existe o si ocurre un error
     */
    @Override
    public User showUser(String username) {
        if(!USERS_FILE.exists()){
            addDataUser();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            User user = (User) ois.readObject();
            if (user.getUsername().equals(username)) {
                return user;
            } else {
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Inicializa el archivo de usuarios con un conjunto de datos de ejemplo.
     * Este método sobrescribe el archivo si ya existiera contenido previo.
     */
    public void addDataUser() {
        User pedro = new User("Pedro", "1234", 34, "Puerto Rico");
        User jose = new User("Jose", "4321", 27, "Santurtzi");
        User iker = new User("Iker", "1234", 31, "Barcelona");
        User patricia = new User("Patricia", "4321", 23, "Madrid");
        User adrian = new User("Adrian", "1234", 39, "Valencia");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE));

            oos.writeObject(pedro);
            oos.writeObject(jose);
            oos.writeObject(iker);
            oos.writeObject(patricia);
            oos.writeObject(adrian);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
