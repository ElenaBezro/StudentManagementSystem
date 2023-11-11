package gradingManagement;

import courseManagement.Course;
import studentManagement.Student;

import java.util.ArrayList;
import java.util.List;

public class GradingSystem {
    private static GradingSystem instance;
    private List<StudentGrades> studentsCoursesWithGrades;

    public static final int MINIMUM_PASSING_GRADE = 3;

    private GradingSystem() {
        studentsCoursesWithGrades = new ArrayList<>();
    }

    public static GradingSystem getInstance() {
        if (instance == null) {
            instance = new GradingSystem();
        }
        return instance;
    }

    public void addGrade(Student student, Course course, int grade) {
        for (StudentGrades studentGrade : studentsCoursesWithGrades) {
            if (studentGrade.getStudent().equals(student)) {
                studentGrade.addGrade(course, grade);
                return;
            }
        }

        StudentGrades newStudentGrades = new StudentGrades(student);
        newStudentGrades.addGrade(course, grade);

        studentsCoursesWithGrades.add(newStudentGrades);
    }

    public List<StudentGrades> getStudentsCoursesWithGrades() {
        return studentsCoursesWithGrades;
    }

    public List<StudentGrades> findStudentsWithBadAcademicPerformance() {
        List<StudentGrades> result;
        result = getStudentsCoursesWithGrades()
                .stream()
                .filter(grades -> grades.hasLowThenMinimumGrades(MINIMUM_PASSING_GRADE))
                .toList();

        if (result.isEmpty()) {
            System.out.println("No student with bad academic performance");
        } else {
            System.out.println(result);
        }

        return result;
    }

    //This method is for test purposes

    public String printStudentsCoursesWithGradesMapInternally() {
        StringBuilder result = new StringBuilder();
        studentsCoursesWithGrades.forEach((studentGrades) -> {
            result.append(studentGrades.getStudent().toString())
                    .append("\n");
            studentGrades.getGrades().forEach((course, grade) -> {
                result.append("----")
                        .append(course.getName())
                        .append(": ")
                        .append(grade)
                        .append("\n");
            });
        });
        return result.toString();
    }

    public void printStudentsCoursesWithGradesMap() {
        System.out.println(printStudentsCoursesWithGradesMapInternally());
    }

    public void reset() {
        studentsCoursesWithGrades = new ArrayList<>();
    }
}
