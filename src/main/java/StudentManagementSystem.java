import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    //List<User> userList = new ArrayList<>();
    List<String[]> usersPasswords = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private static boolean isOpen = true;
    private static boolean isLoggedIn = false;


    public void startSystem(){
        System.out.println("Welcome to the Student Management System!");

        while (isOpen && !isLoggedIn) {
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
        isLoggedIn = true;
        //check if user in system --> check role (use some preinstalled password?) --> enter system with the role

    }

    public void registerUser() {
        //add a new user to the system. Check permission and assign a role.
        //login(newUser data);
    }

    public void exit() {
        isOpen = false;
        System.out.println("Goodbye!");
        sc.close();
    }
}
