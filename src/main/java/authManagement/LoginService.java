package authManagement;

import userManagement.InputService;
import userManagement.UserManagement;
import userManagement.Utils;

import java.util.*;

public class LoginService {
    //TODO: move operations with scanner, systemState to a LoginController

    private UserManagement userManagement;
    private List<LoginPasswordPair> userLoginToPassword;
    private Scanner sc;
    private int countWrongAttempt = 0;
    public static final int ALLOWED_WRONG_LOGIN_INPUT = 3;

    public LoginService(UserManagement userManagement) {
        this.userManagement = userManagement;
        this.userLoginToPassword = new ArrayList<>();
        this.sc = InputService.getScanner();
        Utils.fillUserToPassword(userLoginToPassword);
    }

    public void login(Map<String, Boolean> systemState) {
        while (!systemState.get("isLoggedIn") && !systemState.get("isExit")) {
            LoginPasswordPair data = getUserInputLoginData();
            String login = data.getLogin();
            String password = data.getPassword();

            login(login, password, systemState);
        }
    }

    public void login(String login, String password, Map<String, Boolean> systemState) {
        boolean isUserFound = findLoginPasswordsPair(login, password);

        if (isUserFound) {
            System.out.println("Logged in!");
            System.out.println("User role: " + userManagement.getRole(login));

            systemState.put("isLoggedIn", true);
            userManagement.displayCommandsForUser();
        } else {
            countWrongAttempt += 1;
            System.out.println("Invalid login or password!");
            System.out.println("Attempts available: " + (ALLOWED_WRONG_LOGIN_INPUT - countWrongAttempt));
            if (countWrongAttempt >= ALLOWED_WRONG_LOGIN_INPUT) {
                systemState.put("isRegistration", false);
                systemState.put("isExit", true);
                System.out.println("Goodbye!");
                sc.close();
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
