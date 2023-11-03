package CustomExceptions;

public class StudentIsNotRegisteredException extends Exception {
    public StudentIsNotRegisteredException() {
            super("Student is not in the system");
    }
}
