package CustomExceptions;

public class AdministerCourseDenyException extends Exception {
    public AdministerCourseDenyException() {
        super("Access to administer this course is denied.");
    }
}
