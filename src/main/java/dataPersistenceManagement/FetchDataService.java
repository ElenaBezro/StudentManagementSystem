package dataPersistenceManagement;

import authManagement.LoginPasswordPair;
import roleManagement.Role;
import userManagement.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchDataService implements FetchData {
    private static final String USER_DATA_FILE = "users.txt";
    private static final String LOGIN_TO_USER_FILE = "loginToUser.txt";
    private static final String LOGIN_TO_PASSWORD_FILE = "loginToPassword.txt";
    private static final String LOGIN_TO_ROLE_FILE = "loginToRole.txt";
    private static final String SEPARATOR = ", ";

    @Override
    public List<User> getUserList() {
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

    @Override
    public Map<String, User> getLoginToUser() {
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

    @Override
    public List<LoginPasswordPair> getLoginToPassword() {
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

    @Override
    public Map<String, Role> getLoginToRole() {
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
