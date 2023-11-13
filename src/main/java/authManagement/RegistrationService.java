package authManagement;

import roleManagement.Role;
import userManagement.InputService;
import userManagement.User;
import userManagement.UserManagement;
import userManagement.Utils;


public class RegistrationService {
    //TODO: move operations with scanner, systemState, validation, isUserLoginAvailable  to a RegistrationController

    UserManagement userManagement;
    LoginService loginService;

    public RegistrationService(UserManagement userManagement, LoginService loginService) {
        this.userManagement = userManagement;
        this.loginService = loginService;
    }

    public void registerUser() {
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
            isRoleSetted = userManagement.setCurrentUserRole();
        }

        String name = InputService.getUserName();
        User user = Utils.generateUser(name, userManagement.getUsersCount());
        storeUserInSystem(user, login, password);

        loginService.login(login, password);
    }

    public void storeUserInSystem(User user, String login, String password) {
        Role role = userManagement.getCurrentRole();
        userManagement.addUser(user, login, role);
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
