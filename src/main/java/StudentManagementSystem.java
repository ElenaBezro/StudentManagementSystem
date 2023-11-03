import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentManagementSystem {
    //TODO: change later to List<User> userList = new ArrayList<>();
    List<String[]> userToPassword = new ArrayList<>();
    private static final Map<String, Role> SECRET_WORD_TO_ROLE = Utils.fillSecretWordToRoleMap();
    Scanner sc = new Scanner(System.in);
    private static boolean isRegistration = true;
    private static boolean isExit = false;
    private static boolean isLoggedIn = false;
    private static boolean isUserMenuOpen = true;
    private static int countWrongAttempt = 0;
    public static final int ALLOWED_WRONG_LOGIN_INPUT = 3;

    public Role currentUserRole;

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

    private String[] getUserInputLoginData() {
        System.out.println("Enter login");
        String login = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();
        return (new String[]{login, password});
    }


    public void login() {
        while (!isLoggedIn && !isExit) {
            String login = getUserInputLoginData()[0];
            String password = getUserInputLoginData()[1];

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
                //TODO: pass user's role currentUsersRole? later (student or teacher)
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

    private boolean setUsersRole() {
        boolean isSet = true;
        System.out.println("Enter a SECRET WORD for your role. Tip: student/teacher/admin");
        String role = sc.nextLine();
        switch (role.toLowerCase()) {
            case "student" -> currentUserRole = Role.STUDENT;
            case "teacher" -> currentUserRole = Role.TEACHER;
            case "admin" -> currentUserRole = Role.ADMIN;
            default -> {
                System.out.println("Invalid input");
                isSet = false;
            }
        }
        return isSet;
    }

    private void storeUserInSystem() {
        userToPassword.add(getUserInputLoginData());
    }


    public void registerUser() {
        String login = getUserInputLoginData()[0];
        String password = getUserInputLoginData()[1];
        boolean isRoleSetted = false;
        while (!isRoleSetted) {
            isRoleSetted = setUsersRole();
        }
        storeUserInSystem();
        login(login, password);
    }

    public void exit() {
        isRegistration = false;
        isExit = true;
        System.out.println("Goodbye!");
        sc.close();
    }

    public void displayCommandsForUser() {
//        while (isUserMenuOpen) {
//            System.out.println("Enter 'login' or 'register' to enter system");
//            String command = sc.nextLine();
//            switch (command.toLowerCase()) {
//                case "exit" -> exit();
//                default -> System.out.println("Invalid input");
//            }
//        }
    }
}
