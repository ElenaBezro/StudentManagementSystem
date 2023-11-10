import userManagement.StudentManagementSystem;

public class App {
    private static final StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

    public static void runApp() {
        studentManagementSystem.startSystem();
    }
    public static void main(String[] args) {
        runApp();
    }
}
