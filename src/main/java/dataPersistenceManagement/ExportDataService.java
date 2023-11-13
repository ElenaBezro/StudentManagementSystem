package dataPersistenceManagement;

import authManagement.LoginPasswordPair;
import roleManagement.Role;
import userManagement.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExportDataService implements ExportData{
    private static final String USER_DATA_FILE = "users.txt";
    private static final String LOGIN_TO_USER_FILE = "loginToUser.txt";
    private static final String LOGIN_TO_PASSWORD_FILE = "loginToPassword.txt";
    private static final String LOGIN_TO_ROLE_FILE = "loginToRole.txt";
    private static final String SEPARATOR = ", ";

    @Override
    public void exportUserList(List<User> userList) {
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

    @Override
    public void exportLoginToUser(Map<String, User> userLoginToUserMap) {
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

    @Override
    public void exportLoginToPassword(List<LoginPasswordPair> loginToPassword) {
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

    @Override
    public void exportLoginToRole(Map<String, Role> userLoginToRoleMap) {
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
}
