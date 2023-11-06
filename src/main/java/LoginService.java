import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginService {
    private UserManagement userManagement;
    private Map<String, String> userLoginToPassword = new HashMap<>();
    private int countWrongAttempt = 0;
    public static final int ALLOWED_WRONG_LOGIN_INPUT = 3;

    public LoginService(UserManagement userManagement) {
        this.userManagement = userManagement;
        Utils.fillUserToPassword(userLoginToPassword);
    }

    public void login(Scanner sc, Map<String, Boolean> systemState) {
        while (!systemState.get("isLoggedIn") && !systemState.get("isExit")) {
            String[] data = getUserInputLoginData(sc);
            String login = data[0];
            String password = data[1];

            login(login, password, systemState, sc);
        }
    }

    public void login(String login, String password, Map<String, Boolean> systemState, Scanner sc) {
        boolean isUserFound = findLoginPasswordsPair(login, password);

        if (isUserFound) {
            System.out.println("Logged in!");
            System.out.println("User role: " + userManagement.getRole(login));

            systemState.put("isLoggedIn", true);
            userManagement.displayCommandsForUser(sc);
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
        boolean isUserFound = false;
        for (Map.Entry<String, String> user : userLoginToPassword.entrySet()) {
            if (user.getKey().equals(login) && user.getValue().equals(password)) {
                isUserFound = true;
            }
        }
        return isUserFound;
    }

    public boolean isUserLoginAvailable(String login) {
        return userLoginToPassword.get(login) == null;
    }

    public void storeLoginToPassword(String login, String password) {
        userLoginToPassword.put(login, password);
    }

    public void addLoginToPassword(String[] userInputLoginData) {
        String login = userInputLoginData[0];
        String password = userInputLoginData[1];
        userLoginToPassword.put(login, password);
    }

    public String[] getUserInputLoginData(Scanner sc) {
        System.out.println("Enter login");
        String login = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        return (new String[]{login, password});
    }
}
