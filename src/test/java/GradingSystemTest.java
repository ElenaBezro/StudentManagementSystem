import courseManagement.Course;
import gradingManagement.GradingSystem;
import gradingManagement.StudentGrades;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import studentManagement.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradingSystemTest {
    GradingSystem gradingSystem = GradingSystem.getInstance();

    @AfterEach
    void tearDown() {
        gradingSystem.reset();
    }

    @Test
    @DisplayName("Test that the result contains students with low grades")
    void findStudentsWithBadAcademicPerformancePositive() {
        //arrange
        Student student1 = new Student("Lena", 1001);
        Student student2 = new Student("Lola", 1002);

        Course course1 = new Course("Math", 101);
        Course course2 = new Course("German", 102);

        gradingSystem.addGrade(student1, course1, 1);
        gradingSystem.addGrade(student1, course2, 5);
        gradingSystem.addGrade(student2, course1, 4);
        gradingSystem.addGrade(student2, course2, 4);

        //act
        List<StudentGrades> resultPositive = gradingSystem.findStudentsWithBadAcademicPerformance();

        //assert
        assertEquals(1, resultPositive.size());
        assertEquals(resultPositive.get(0).getStudent(), student1);
        assertEquals((int)resultPositive.get(0).getGrades().get(course1), 1);
    }

    @Test
    @DisplayName("Test that the result is empty when all students have high grades")
    void findStudentsWithBadAcademicPerformanceEmpty() {
        //arrange
        Student student1 = new Student("Lena", 1001);
        Student student2 = new Student("Lola", 1002);

        Course course1 = new Course("Math", 101);
        Course course2 = new Course("German", 102);

        gradingSystem.addGrade(student1, course1, 5);
        gradingSystem.addGrade(student1, course2, 4);
        gradingSystem.addGrade(student2, course1, 4);
        gradingSystem.addGrade(student2, course2, 4);

        //act
        List<StudentGrades> resultEmpty = gradingSystem.findStudentsWithBadAcademicPerformance();

        //assert
        assertEquals(0, resultEmpty.size());
    }

    @Test
    @DisplayName("Test that the result is empty if grades = minimum passing grade")
    void findStudentsWithBadAcademicPerformanceEdgeCase() {
        //arrange
        int edgeCaseValue = GradingSystem.MINIMUM_PASSING_GRADE;

        Student student1 = new Student("Lena", 1001);
        Student student2 = new Student("Lola", 1002);

        Course course1 = new Course("Math", 101);
        Course course2 = new Course("German", 102);

        gradingSystem.addGrade(student1, course1, edgeCaseValue);
        gradingSystem.addGrade(student1, course2, 5);
        gradingSystem.addGrade(student2, course1, 4);
        gradingSystem.addGrade(student2, course2, 4);

        //act
        List<StudentGrades> result = gradingSystem.findStudentsWithBadAcademicPerformance();
        System.out.println(gradingSystem.printStudentsCoursesWithGradesMapInternally());

        //assert
        assertEquals(0, result.size());
    }

    @Test
    void printStudentsCoursesWithGradesMap() {
        //arrange
        Student student1 = new Student("Lena", 1001);
        Student student2 = new Student("Lola", 1002);

        Course course1 = new Course("Math", 101);
        Course course2 = new Course("German", 102);

        gradingSystem.addGrade(student1, course1, 1);
        gradingSystem.addGrade(student1, course2, 5);
        gradingSystem.addGrade(student2, course1, 4);
        gradingSystem.addGrade(student2, course2, 4);

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