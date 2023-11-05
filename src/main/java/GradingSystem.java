import CustomExceptions.StudentNotInTheCourseException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GradingSystem {
    private static final Map<Student, Map<Course, Integer>> studentsCoursesWithGradesMap = new HashMap<>();

    private static final int MINIMUM_PASSING_GRADE = 2;


    public static void addGrade(Student student, Course course, int grade) throws StudentNotInTheCourseException {
        if (!studentsCoursesWithGradesMap.get(student).containsKey(course)) {
            throw new StudentNotInTheCourseException();
        }

        Map<Course, Integer> studentGrades = studentsCoursesWithGradesMap.get(student);
        studentGrades.put(course, grade);

        studentsCoursesWithGradesMap.put(student, studentGrades);
    }

    public Map<Student, Map<Course, Integer>> getStudentsCoursesWithGradesMap() {
        return studentsCoursesWithGradesMap;
    }

    public Map<Student, Map<Course, Integer>> findStudentsWithBadAcademicPerformance() {
        Map<Student, Map<Course, Integer>> result;
        result = getStudentsCoursesWithGradesMap().entrySet()
                .stream()
                .filter(studentToCoursesEntry -> {
                    return !studentToCoursesEntry
                            .getValue()
                            .entrySet()
                            .stream()
                            .filter(courseToGradeEntry -> courseToGradeEntry.getValue() <= MINIMUM_PASSING_GRADE)
                            .toList().isEmpty();
                })
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue));

        if (result.isEmpty()) {
            System.out.println("No student with bad academic performance");
        } else {
            printStudentsCoursesWithGradesMap();
        }

        return result;
    }

    public void printStudentsCoursesWithGradesMap() {
        StringBuilder result = new StringBuilder();
        studentsCoursesWithGradesMap.forEach((student, courseToGrade) -> {
            result.append(student.toString())
                    .append("\n");
            courseToGrade.forEach((course, grade) -> {
                result.append("    ")
                        .append(course.getName())
                        .append(": ")
                        .append(grade)
                        .append("\n");
            });
        });
        System.out.println(result);
    }
}
