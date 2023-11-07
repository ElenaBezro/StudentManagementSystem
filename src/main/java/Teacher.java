import CustomExceptions.AdministerCourseDenyException;
import CustomExceptions.StudentNotInTheCourseException;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

    List<Course> coursesToAdminister = new ArrayList<>();

    public Teacher(String name, int id) {
        super(name, id);
    }

    public void setGrade(Course course, Student student, int grade) throws AdministerCourseDenyException {
        //try {
            if (coursesToAdminister.contains(course)) {
                GradingSystem.addGrade(student, course, grade);
            } else {
                throw new AdministerCourseDenyException();
            }
//        } catch (StudentNotInTheCourseException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
