package CustomExceptions;

public class CourseIsNotRegisteredException extends Exception {
    public CourseIsNotRegisteredException() {
            super("java.courseManagement.Course is not in the system");
    }
}
