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
        this.sc = InputService.getScanner();
        Utils.fillSystemInitialState(systemState);
        //TODO: move DataPersistenceService in this file and execute:
        // initialize userManagement with new UserManagement(dataPersistenceService.readUserDataIntoFile())?
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
                //TODO: how not to pass shared systemState here, but to have access to
                // it from different parts? For example, systemState.get("isLoggedIn") used
                // here and also in LoginService
                case "login" -> loginService.login(systemState);
                case "register" -> registrationService.registerUser(systemState);
                case "exit" -> exit();
                default -> System.out.println("Invalid input");
            }
        }
    }

    public void exit() {
        //TODO: implement exit possibility from any point of the program. But how?
        systemState.put("isRegistration", false);
        systemState.put("isExit", true);
        userManagement.writeUserDataIntoFile();
        //TODO: move DataPersistenceService in this file and execute:
        // dataPersistenceService.writeUserDataIntoFile(userList);
        System.out.println("Goodbye!");
        sc.close();
    }

    public <T> void printObject(T object) {
        System.out.println(object.getClass().getName() + " = " + object);
    }
}
