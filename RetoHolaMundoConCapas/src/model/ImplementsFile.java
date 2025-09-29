package model;

import java.io.*;

/**
 * Implementación de DAO para manejar usuarios usando Object Input/Output
 * 
 * @author 2dami
 */
public class ImplementsFile implements DAO {
    private File USERS_FILE = new File("users.dat");

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

    @Override
    public User showUser(String username) {
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
