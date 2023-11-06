import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManagementSystem {
    private UserManagement userManagement;
    private RegistrationService registrationService;
    private LoginService loginService;

    private Scanner sc;
    private Map<String, Boolean> systemState = new HashMap<>();

    public StudentManagementSystem() {
        this.sc = new Scanner(System.in);
        Utils.fillSystemInitialState(systemState);
        this.userManagement = new UserManagement();
        this.loginService = new LoginService(userManagement);
        this.registrationService = new RegistrationService(userManagement, loginService);
    }

    public void startSystem() {
        System.out.println("Welcome to the Student Management System!");

        while (systemState.get("isRegistration") && !systemState.get("isLoggedIn")) {
            System.out.println("Enter 'login' or 'register' to enter system");
            System.out.println("Enter 'exit' to exit system");
            String command = sc.nextLine();
            switch (command.toLowerCase()) {
                case "login" -> loginService.login(sc, systemState);
                case "register" -> registrationService.registerUser(sc, systemState);
                case "exit" -> exit();
                default -> System.out.println("Invalid input");
            }
        }
    }

    public void exit() {
        //TODO: implement exit possibility from any point of the program
        systemState.put("isRegistration", false);
        systemState.put("isExit", true);
        userManagement.writeUserDataIntoFile();
        System.out.println("Goodbye!");
        sc.close();
    }

    public <T> void printObject(T object) {
        System.out.println(object.getClass().getName() + " = " + object);
    }
}
