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
        String[] data = loginService.getUserInputLoginData();

        boolean isValid = validateUserInputLoginData(data);
        while (!isValid) {
            data = loginService.getUserInputLoginData();
            isValid = validateUserInputLoginData(data);
        }

        boolean isUserLoginAvailable = loginService.isUserLoginAvailable(data[0]);
        while (!isUserLoginAvailable) {
            System.out.println("Login is not available");
            data = loginService.getUserInputLoginData();
            isUserLoginAvailable = loginService.isUserLoginAvailable(data[0]);
        }

        String login = data[0];
        String password = data[1];

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

    public boolean validateUserInputLoginData(String[] data) {
        boolean isValid = true;
        if (data[0].trim().isEmpty()) {
            isValid = false;
            System.out.println("Empty login are not allowed.");
        }
        if (data[1].length() < 4) {
            isValid = false;
            System.out.println("The password must consist of a minimum of four characters or digits.");
        }
        return isValid;
    }
}
