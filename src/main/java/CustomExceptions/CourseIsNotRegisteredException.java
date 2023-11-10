package CustomExceptions;

public class CourseIsNotRegisteredException extends Exception {
    public CourseIsNotRegisteredException() {
            super("Course is not in the system");
    }
}
