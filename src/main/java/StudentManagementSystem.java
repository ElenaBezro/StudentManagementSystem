import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    //TODO: change later to List<User> userList = new ArrayList<>();
    List<String[]> userToPassword = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private static boolean isRegistration = true;
    private static boolean isExit = false;
    private static boolean isLoggedIn = false;
    private static boolean isUserMenuOpen = true;
    private static int countWrongAttempt = 0;
    public static final int ALLOWED_WRONG_LOGIN_INPUT = 3;

    //TODO: use enum for roles
    public String currentUserRole;


    public void startSystem() {
        System.out.println("Welcome to the Student Management System!");

        while (isRegistration && !isLoggedIn) {
            System.out.println("Enter 'login' or 'register' to enter system");
            System.out.println("Enter 'exit' to exit system");
            String command = sc.nextLine();
            switch (command.toLowerCase()) {
                case "login" -> login();
                case "register" -> registerUser();
                case "exit" -> exit();
                default -> System.out.println("Invalid input");
            }
        }
    }

    public void login() {
        while (!isLoggedIn && !isExit) {
            //TODO: add functionality to exit or exit if input was invalid 3 times
            System.out.println("Enter login");
            String login = sc.nextLine();
            System.out.println("Enter password");
            String password = sc.nextLine();
            login(login, password);
        }
    }

    private void login(String login, String password) {
        boolean isUserFound = false;

        for (String[] user : userToPassword) {
            if (user[0].equals(login) && user[1].equals(password)) {
                System.out.println("Logged in!");
                isLoggedIn = true;
                isUserFound = true;
                //TODO: pass user's role later (student or teacher)
                displayCommandsForUser();
            }
        }
        if (!isUserFound) {
            countWrongAttempt += 1;
            System.out.println("Invalid login or password!");
            System.out.println("Attempts availible: " + (ALLOWED_WRONG_LOGIN_INPUT - countWrongAttempt));
            if (countWrongAttempt >= ALLOWED_WRONG_LOGIN_INPUT) {
                exit();
            }
        }
        //TODO: check if user in system --> check role (use some preinstalled password?) --> enter system with the role
    }

    public void registerUser() {
        System.out.println("Enter new login");
        String login = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();

        // TODO: Check permission (store predefined keywords for roles?) and assign a role.
        userToPassword.add(new String[]{login, password});

        login(login, password);
    }

    public void exit() {
        isRegistration = false;
        isExit = true;
        System.out.println("Goodbye!");
        sc.close();
    }

    public void displayCommandsForUser() {
        while (isUserMenuOpen) {
            System.out.println("Enter command");
            String command = sc.nextLine();
            switch (command.toLowerCase()) {
                case "exit" -> exit();
                default -> System.out.println("Invalid input");
            }
        }
    }
}
