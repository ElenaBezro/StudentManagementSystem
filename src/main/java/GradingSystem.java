import CustomExceptions.StudentNotInTheCourseException;

import java.util.HashMap;
import java.util.Map;

public class GradingSystem {
    private static final Map<Student, Map<Course, Integer>>  studentsCoursesWithGradesMap = new HashMap<>();

    public static void addGrade(Student student, Course course, int grade) throws StudentNotInTheCourseException {
        if (!studentsCoursesWithGradesMap.get(student).containsKey(course)) {
            throw new StudentNotInTheCourseException();
        }

        Map<Course, Integer> studentGrades = studentsCoursesWithGradesMap.get(student);
        studentGrades.put(course, grade);

        studentsCoursesWithGradesMap.put(student, studentGrades);
    }
}
