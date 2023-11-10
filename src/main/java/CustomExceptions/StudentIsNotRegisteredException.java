package CustomExceptions;

public class StudentIsNotRegisteredException extends Exception {
    public StudentIsNotRegisteredException() {
            super("java.studentManagement.Student is not in the system");
    }
}
