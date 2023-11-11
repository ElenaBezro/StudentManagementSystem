import userManagement.StudentManagementSystem;

public class App {
    public static void runApp() {
        StudentManagementSystem.getInstance().startSystem();
    }
    public static void main(String[] args) {
        runApp();
    }
}
