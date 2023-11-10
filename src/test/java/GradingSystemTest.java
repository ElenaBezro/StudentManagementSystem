import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradingSystemTest {

    @Test
    @DisplayName("Test that the result contains students with low grades")
    void findStudentsWithBadAcademicPerformancePositive() {
        //arrange
        GradingSystem gradingSystem = new GradingSystem();

        Student student1 = new Student("Lena", 1001);
        Student student2 = new Student("Lola", 1002);

        Course course1 = new Course("Math", 101);
        Course course2 = new Course("German", 102);

        GradingSystem.addGrade(student1, course1, 1);
        GradingSystem.addGrade(student1, course2, 5);
        GradingSystem.addGrade(student2, course1, 4);
        GradingSystem.addGrade(student2, course2, 4);

        //act
        Map<Student, Map<Course, Integer>> resultPositive = gradingSystem.findStudentsWithBadAcademicPerformance();

        //assert
        assertEquals(1, resultPositive.size());
        assertTrue(resultPositive.containsKey(student1));
    }

    @Test
    @DisplayName("Test that the result is empty when all students have high grades")
    void findStudentsWithBadAcademicPerformanceEmpty() {
        //arrange
        GradingSystem gradingSystem = new GradingSystem();

        Student student1 = new Student("Lena", 1001);
        Student student2 = new Student("Lola", 1002);

        Course course1 = new Course("Math", 101);
        Course course2 = new Course("German", 102);

        GradingSystem.addGrade(student1, course1, 5);
        GradingSystem.addGrade(student1, course2, 4);
        GradingSystem.addGrade(student2, course1, 4);
        GradingSystem.addGrade(student2, course2, 4);

        //act
        Map<Student, Map<Course, Integer>> resultEmpty = gradingSystem.findStudentsWithBadAcademicPerformance();

        //assert
        assertEquals(0, resultEmpty.size());
    }

    @Test
    @DisplayName("Test that the result is empty if grades = minimum passing grade")
    void findStudentsWithBadAcademicPerformanceEdgeCase() {
        //arrange
        GradingSystem gradingSystem = new GradingSystem();
        int edgeCaseValue = GradingSystem.MINIMUM_PASSING_GRADE;

        Student student1 = new Student("Lena", 1001);
        Student student2 = new Student("Lola", 1002);

        Course course1 = new Course("Math", 101);
        Course course2 = new Course("German", 102);

        GradingSystem.addGrade(student1, course1, edgeCaseValue);
        GradingSystem.addGrade(student1, course2, 5);
        GradingSystem.addGrade(student2, course1, 4);
        GradingSystem.addGrade(student2, course2, 4);

        //act
        Map<Student, Map<Course, Integer>> result = gradingSystem.findStudentsWithBadAcademicPerformance();
        System.out.println(gradingSystem.printStudentsCoursesWithGradesMapInternally());

        //assert
        assertEquals(0, result.size());
    }

    @Test
    void printStudentsCoursesWithGradesMap() {
        //arrange
        GradingSystem gradingSystem = new GradingSystem();

        Student student1 = new Student("Lena", 1001);
        Student student2 = new Student("Lola", 1002);

        Course course1 = new Course("Math", 101);
        Course course2 = new Course("German", 102);

        GradingSystem.addGrade(student1, course1, 1);
        GradingSystem.addGrade(student1, course2, 5);
        GradingSystem.addGrade(student2, course1, 4);
        GradingSystem.addGrade(student2, course2, 4);

        //act
        String result  = gradingSystem.printStudentsCoursesWithGradesMapInternally();
        String expected = "User: name = Lena, id = 1001\n"
                + "----Math: 1\n---- German: 5\n"
                + "User: name = Lola, id = 1002\n"
                + "----Math: 4\n----German: 4";

        //assert
        assertTrue(result.contains("User: name = Lena, id = 1001"));
        assertTrue(result.contains("----Math: 1"));
        assertTrue(result.contains("----German: 5"));
        assertTrue(result.contains("User: name = Lola, id = 1002"));
        assertTrue(result.contains("----Math: 4"));
        assertTrue(result.contains("----German: 4"));
    }
}