package userManagement;

import java.util.Scanner;

public class InputService {
    private static Scanner instance = null;

    private InputService() {
    }

    public static Scanner getScanner() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;
    }

    public static String getUserName() {
        System.out.println("Enter User name: ");
        String name = instance.nextLine();
        while (name.isBlank() || name.isEmpty()) {
            System.out.println("Invalid input");
            getUserName();
        }
        return name;
    }
}
