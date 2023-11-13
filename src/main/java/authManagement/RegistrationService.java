package authManagement;

import inputService.InputService;
import roleManagement.Role;
import inputService.ScannerInputService;
import userManagement.User;
import userManagement.UserManagement;
import userManagement.Utils;

public class RegistrationService {
    private final UserManagement userManagement;
    private final LoginService loginService;
    private final InputService inputService;

    public RegistrationService(UserManagement userManagement, LoginService loginService) {
        this.userManagement = userManagement;
        this.loginService = loginService;
        this.inputService = ScannerInputService.getInstance();
    }

    public void registerUser() {
        LoginPasswordPair data = getAvailableLoginPassword();
        String login = data.getLogin();
        String password = data.getPassword();

        setRole();

        User user = createUser();
        storeUserInSystem(user, login, password);

        loginService.login(login, password);
    }

    public void storeUserInSystem(User user, String login, String password) {
        Role role = userManagement.getCurrentRole();
        userManagement.addUser(user, login, role);
        loginService.storeLoginToPassword(login, password);
    }

    private boolean validateUserInputLoginData(LoginPasswordPair data) {
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

    private String getInputName() {
        String name = inputService.getUserInput("Enter User name: ");

        while (name.isBlank() || name.isEmpty()) {
            System.out.println("Invalid input");
            name = getInputName();
        }
        return name;
    }

    private LoginPasswordPair getValidData() {
        LoginPasswordPair data = loginService.getUserInputLoginData();
        boolean isValid = validateUserInputLoginData(data);

        while (!isValid) {
            data = loginService.getUserInputLoginData();
            isValid = validateUserInputLoginData(data);
        }
        return data;
    }

    private LoginPasswordPair getAvailableLoginPassword() {
        LoginPasswordPair data = getValidData();
        String loginToCheck = data.getLogin();
        boolean isUserLoginAvailable = loginService.isUserLoginAvailable(loginToCheck);

        while (!isUserLoginAvailable) {
            System.out.println("Login is not available");
            data = getValidData();
            loginToCheck = data.getLogin();
            isUserLoginAvailable = loginService.isUserLoginAvailable(loginToCheck);
        }
        return data;
    }

    private void setRole(){
        boolean isRoleSetted = false;

        while (!isRoleSetted) {
            isRoleSetted = userManagement.setCurrentUserRole();
        }
    }

    private User createUser(){
        String name = getInputName();
        return Utils.generateUser(name, userManagement.getUsersCount());
    }
}
