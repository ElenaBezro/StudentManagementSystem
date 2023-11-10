package teacherManagement;

import CustomExceptions.AdministerCourseDenyException;
import courseManagement.Course;
import gradingManagement.GradingSystem;
import studentManagement.Student;
import userManagement.User;

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
                GradingSystem.getInstance().addGrade(student, course, grade);
            } else {
                throw new AdministerCourseDenyException();
            }
//        } catch (StudentNotInTheCourseException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
