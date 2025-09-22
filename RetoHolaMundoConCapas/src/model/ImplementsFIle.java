package model;

import java.io.*;

/**
 * Implementación de DAO para manejar usuarios usando Object Input/Output
 * @author 2dami
 */
public class ImplementsFIle implements DAO {
    private static final String USERS_FILE = "users.dat";
    
    @Override
    public boolean checkUser(String username, String password) {
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
}
