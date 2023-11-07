import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GradingSystem {
    private static final Map<Student, Map<Course, Integer>> studentsCoursesWithGradesMap = new HashMap<>();

    public static final int MINIMUM_PASSING_GRADE = 3;


    public static void addGrade(Student student, Course course, int grade) {
        Map<Course, Integer> studentGrades = new HashMap<>();
        if (studentsCoursesWithGradesMap.containsKey(student)) {
            studentGrades = studentsCoursesWithGradesMap.get(student);
            studentGrades.put(course, grade);
        } else {
            studentGrades.put(course, grade);
        }

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
                            .filter(courseToGradeEntry -> courseToGradeEntry.getValue() < MINIMUM_PASSING_GRADE)
                            .toList().isEmpty();
                })
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue));

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
        studentsCoursesWithGradesMap.forEach((student, courseToGrade) -> {
            result.append(student.toString())
                    .append("\n");
            courseToGrade.forEach((course, grade) -> {
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
}
