package dataPersistenceManagement;

import authManagement.LoginPasswordPair;
import roleManagement.Role;
import userManagement.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataPersistenceService {
    private static final String USER_DATA_FILE = "users.txt";
    private static final String LOGIN_TO_USER_FILE = "loginToUser.txt";
    private static final String LOGIN_TO_PASSWORD_FILE = "loginToPassword.txt";
    private static final String LOGIN_TO_ROLE_FILE = "loginToRole.txt";
    private static final String SEPARATOR = ", ";

    public void writeUserListIntoFile(List<User> userList) {
        try (FileWriter fileWriter = new FileWriter(USER_DATA_FILE);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (User user : userList) {
                writer.write(user.getName() + SEPARATOR + user.getId());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeLoginToUserIntoFile(Map<String, User> userLoginToUserMap) {
        try (FileWriter fileWriter = new FileWriter(LOGIN_TO_USER_FILE);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            userLoginToUserMap.forEach((login, user) -> {
                try {
                    writer.write(login + SEPARATOR + user.getName() + SEPARATOR + user.getId());
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeLoginToPasswordIntoFile(List<LoginPasswordPair> loginToPassword) {
        try (FileWriter fileWriter = new FileWriter(LOGIN_TO_PASSWORD_FILE);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (LoginPasswordPair pair : loginToPassword) {
                writer.write(pair.getLogin() + SEPARATOR + pair.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeLoginToRoleIntoFile(Map<String, Role> userLoginToRoleMap) {
        try (FileWriter fileWriter = new FileWriter(LOGIN_TO_ROLE_FILE);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            userLoginToRoleMap.forEach((login, role) -> {
                try {
                    writer.write(login + SEPARATOR + role);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> readUserListFromFile() {
        List<User> users = new ArrayList<>();

        try (FileReader fr = new FileReader(USER_DATA_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    String name = line.split(SEPARATOR)[0];
                    int id = Integer.parseInt(line.split(SEPARATOR)[1]);
                    User user = new User(name, id);
                    users.add(user);
                }
            }
            return users;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Map<String, User> readLoginToUserFromFile() {
        Map<String, User> userLoginToUserMap = new HashMap<>();

        try (FileReader fr = new FileReader(LOGIN_TO_USER_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    String login = line.split(SEPARATOR)[0];
                    String name = line.split(SEPARATOR)[1];
                    int id = Integer.parseInt(line.split(SEPARATOR)[2]);
                    User user = new User(name, id);
                    userLoginToUserMap.put(login, user);
                }
            }
            return userLoginToUserMap;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<LoginPasswordPair> readLoginToPasswordFromFile() {
        List<LoginPasswordPair> loginToPassword = new ArrayList<>();

        try (FileReader fr = new FileReader(LOGIN_TO_PASSWORD_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    String login = line.split(SEPARATOR)[0];
                    String password = line.split(SEPARATOR)[1];
                    LoginPasswordPair pair = new LoginPasswordPair(login, password);
                    loginToPassword.add(pair);
                }
            }
            return loginToPassword;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public Map<String, Role> readLoginToRoleFromFile() {
        Map<String, Role> userLoginToRoleMap = new HashMap<>();

        try (FileReader fr = new FileReader(LOGIN_TO_ROLE_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    String login = line.split(SEPARATOR)[0];
                    Role role = Role.valueOf(line.split(SEPARATOR)[1]);
                    userLoginToRoleMap.put(login, role);
                }
            }
            return userLoginToRoleMap;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
