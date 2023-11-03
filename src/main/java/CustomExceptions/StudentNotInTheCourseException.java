package CustomExceptions;

public class StudentNotInTheCourseException extends  Exception{
    public StudentNotInTheCourseException() {
        super("No such student on the course");
    }
}
