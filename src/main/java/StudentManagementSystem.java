import java.util.*;

public class StudentManagementSystem {
    UserManagement userManagement = new UserManagement();
    //TODO: move user data to UserManagement
    //TODO: maybe move login and sign up functionality into a separate file?
    private Map<String, Role> userLoginToRoleMap = new HashMap<>();
    //TODO: remove? redundant
    private static final Map<String, Role> SECRET_WORD_TO_ROLE = Utils.fillSecretWordToRoleMap();
    Scanner sc = new Scanner(System.in);

    //TODO: store all system states in the Map<State, Boolean>, where State is enum?
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
            String[] data = getUserInputLoginData();
            String login = data[0];
            String password = data[1];

            login(login, password);
        }
    }

    private void login(String login, String password) {
        boolean isUserFound = userManagement.findLoginPasswordsPair(login, password);

        if (isUserFound) {
            System.out.println("Logged in!");
            System.out.println("User role: " + userLoginToRoleMap.get(login));

            isLoggedIn = true;
            //TODO: pass user's role currentUsersRole? later (student or teacher)
            displayCommandsForUser();
        } else {
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
        userManagement.addUserToPassword(getUserInputLoginData());
    }

    private void setUserLoginToRoleMap(String login, Role currentUserRole) {
        userLoginToRoleMap.put(login, currentUserRole);
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

    public void registerUser() {
        String[] data = getUserInputLoginData();

        boolean isValid = validateUserInputLoginData(data);
        while (!isValid) {
            data = getUserInputLoginData();
        }

        String login = data[0];
        String password = data[1];

        boolean isRoleSetted = false;
        while (!isRoleSetted) {
            isRoleSetted = setUsersRole();
        }

        storeUserInSystem();
        setUserLoginToRoleMap(login, currentUserRole);
        login(login, password);
    }

    public void exit() {
        isRegistration = false;
        isExit = true;
        System.out.println("Goodbye!");
        sc.close();
    }

    public void displayCommandsForUser() {
        //TODO: display different commands for different roles
//        while (isUserMenuOpen) {
//            System.out.println("Enter 'login' or 'register' to enter system");
//            String command = sc.nextLine();
//            switch (command.toLowerCase()) {
//                case "exit" -> exit();
//                default -> System.out.println("Invalid input");
//            }
//        }
        exit();
    }

    public <T> void printObject(T object) {
        System.out.println(object.getClass().getName() + " = " + object);
    }
}
