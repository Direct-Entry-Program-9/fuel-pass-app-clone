package db;

import java.util.ArrayList;

public class InMemoryDB {
    private static ArrayList<User> userDatabase = new ArrayList();

    public static void registerUser(User user){
        userDatabase.add(user);
    }

    public static User findUser(String nic){
        for (User user : userDatabase) {
            if (user.getNic().equalsIgnoreCase(nic)){
                return user;
            }
        }
        return null;
    }

    public static void removeUser(String nic){
        for (User user : userDatabase) {
            if (user.getNic().equals(nic)){
                userDatabase.remove(user);
                return;
            }
        }
    }

    public static ArrayList<User> getUserDatabase(){
        return userDatabase;
    }
}
