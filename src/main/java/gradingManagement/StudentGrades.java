package gradingManagement;

import courseManagement.Course;
import studentManagement.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentGrades {
    private Student student;
    private Map<Course, Integer> grades;

    public StudentGrades(Student student) {
        this.student = student;
        this.grades = new HashMap<>();
    }

    public void addGrade(Course course, Integer grade) {
        grades.put(course, grade);
    }

    public Student getStudent() {
        return student;
    }

    public Map<Course, Integer> getGrades() {
        return grades;
    }

    public boolean hasLowThenMinimumGrades(Integer minimumPassingGrade) {
        return true;
    }
}
