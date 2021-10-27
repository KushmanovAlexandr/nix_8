package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.Arrays;
import java.util.UUID;

public class UserDB {

    private User[] users = new User[10];
    private static UserDB instance;
    int size = 0;

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        if (size == users.length) {
            users = Arrays.copyOf(users, users.length + 10);
        }
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                size++;
                break;
            }
        }
    }

    public void update(User user) {
        for (int i = 0; i < size; i++) {
            if ((users[i].getId() != null) && (users[i].getId().equals(user.getId()))) {
                users[i].setName(user.getName());
                users[i].setAge(user.getAge());
                break;
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < size; i++) {
            if (((users[i].getId() != null)) && (users[i].getId().equals(id))) {
                users[i] = null;
                for (int j = i + 1; j < users.length; j++) {
                    users[j - 1] = users[j];
                    users[j] = null;
                }
                break;
            }
        }
    }

    public User findById(String id) {
        int i;
        for (i = 0; i < users.length; i++) {
            if ((users[i].getId() != null) && (users[i].getId().equals(id))) {
                return users[i];
            }
        }
        return users[i];
    }

    public User[] findAll() {
        return users;
    }

    public String generateId() {
        String id = UUID.randomUUID().toString();
        for (User user : users) {
            if (user != null && user.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}