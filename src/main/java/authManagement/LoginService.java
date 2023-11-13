package authManagement;

import roleManagement.Role;
import userManagement.InputService;
import userManagement.StudentManagementSystem;
import userManagement.UserManagement;

import java.util.*;

public class LoginService {
    //TODO: move operations with scanner, systemState to a LoginController

    private final UserManagement userManagement;
    private List<LoginPasswordPair> userLoginToPassword;
    private final Scanner sc;
    private int countWrongAttempt = 0;
    public static final int ALLOWED_WRONG_LOGIN_INPUT = 3;

    public LoginService(UserManagement userManagement, List<LoginPasswordPair> userLoginToPassword) {
        this.userManagement = userManagement;
        this.sc = InputService.getScanner();
        this.userLoginToPassword = userLoginToPassword;
    }

    public List<LoginPasswordPair> getUserLoginToPassword() {
        return userLoginToPassword;
    }

    public void login() {
        while (!StudentManagementSystem.getInstance().isLoggedIn() &&
                !StudentManagementSystem.getInstance().isExit()) {
            LoginPasswordPair data = getUserInputLoginData();
            String login = data.getLogin();
            String password = data.getPassword();

            login(login, password);
        }
    }

    public void login(String login, String password) {
        boolean isUserFound = findLoginPasswordsPair(login, password);

        if (isUserFound) {
            System.out.println("Logged in!");
            Role role  = userManagement.getRole(login);
            System.out.println("User role: " + role);
            userManagement.setCurrentUserRole(role);

            StudentManagementSystem.getInstance().setLoggedIn(true);
            userManagement.displayCommandsForUser();
        } else {
            countWrongAttempt += 1;
            System.out.println("Invalid login or password!");
            System.out.println("Attempts available: " + (ALLOWED_WRONG_LOGIN_INPUT - countWrongAttempt));
            if (countWrongAttempt >= ALLOWED_WRONG_LOGIN_INPUT) {
                StudentManagementSystem.getInstance().exit();
            }
        }
    }

    public boolean findLoginPasswordsPair(String login, String password) {
        for (LoginPasswordPair pair : userLoginToPassword) {
            if (pair.getLogin().equals(login) && pair.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserLoginAvailable(String login) {
        for (LoginPasswordPair pair: userLoginToPassword) {
            if (pair.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    public void storeLoginToPassword(String login, String password) {
        userLoginToPassword.add(new LoginPasswordPair(login, password));
    }

    public LoginPasswordPair getUserInputLoginData() {
        System.out.println("Enter login");
        String login = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        return (new LoginPasswordPair(login, password));
    }
}
