package authManagement;

import userManagement.InputService;
import userManagement.User;
import userManagement.UserManagement;
import userManagement.Utils;

import java.util.Map;
import java.util.Scanner;

public class RegistrationService {
    //TODO: move operations with scanner, systemState, validation, isUserLoginAvailable  to a RegistrationController

    UserManagement userManagement;
    LoginService loginService;
    private Scanner sc;

    public RegistrationService(UserManagement userManagement, LoginService loginService) {
        this.userManagement = userManagement;
        this.loginService = loginService;
        this.sc = InputService.getScanner();
    }

    public void registerUser(Map<String, Boolean> systemState) {
        LoginPasswordPair data = loginService.getUserInputLoginData();

        boolean isValid = validateUserInputLoginData(data);
        while (!isValid) {
            data = loginService.getUserInputLoginData();
            isValid = validateUserInputLoginData(data);
        }

        String login = data.getLogin();

        boolean isUserLoginAvailable = loginService.isUserLoginAvailable(login);
        while (!isUserLoginAvailable) {
            System.out.println("Login is not available");
            login = loginService.getUserInputLoginData().getLogin();
            isUserLoginAvailable = loginService.isUserLoginAvailable(login);
        }

        String password = data.getPassword();

        boolean isRoleSetted = false;
        while (!isRoleSetted) {
            isRoleSetted = userManagement.setUsersRole();
        }

        //TODO: get user input for user name
        User user = Utils.generateUser(login, userManagement.getUsersCount());
        storeUserInSystem(user, login, password);

        loginService.login(login, password, systemState);
    }

    public void storeUserInSystem(User user, String login, String password) {
        userManagement.addUser(user, login, password);
        loginService.storeLoginToPassword(login, password);
    }

    public boolean validateUserInputLoginData(LoginPasswordPair data) {
        boolean isValid = true;
        if (data.getLogin().trim().isEmpty()) {
            isValid = false;
            System.out.println("Empty login are not allowed.");
        }
        if (data.getPassword().length() < 4) {
            isValid = false;
            System.out.println("The password must consist of a minimum of four characters or digits.");
        }
        return isValid;
    }
}
