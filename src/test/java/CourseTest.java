import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import courseManagement.Course;
import studentManagement.Student;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    @DisplayName("Test that student has been added")
    void addStudentToCourse() {
        Course course = new Course("Math", 1001);
        Student student = new Student("Lena", 101);

        course.addStudentToCourse(student);

        assertNotNull(course.getStudentList());
        assertTrue(course.getStudentList().contains(student));
    }

    @Test
    @DisplayName("Test that studentList has been updated")
    void updateStudentList() {
        Course course = new Course("Math", 1001);
        Student student1 = new Student("Lena", 101);
        Student student2 = new Student("Lena", 102);
        course.addStudentToCourse(student1);
        course.addStudentToCourse(student2);

        Student student3 = new Student("Lena", 103);
        Student student4 = new Student("Lena", 104);
        List<Student> newStudentList = Arrays.asList(student3, student4);

        course.updateStudentList(newStudentList);

        assertNotNull(course.getStudentList());
        assertTrue(course.getStudentList().contains(student3));
        assertTrue(course.getStudentList().contains(student4));
        assertFalse(course.getStudentList().contains(student1));
        assertFalse(course.getStudentList().contains(student2));
    }

    @Test
    @DisplayName("Test that student has been deleted")
    void deleteStudentFromCourse() {
        Course course = new Course("Math", 1001);
        Student student1 = new Student("Lena", 101);
        course.addStudentToCourse(student1);

        course.deleteStudentFromCourse(101);

        assertFalse(course.getStudentList().contains(student1));
    }


    @Test
    @DisplayName("Test that grade has been added")
    void addGrade() {
        Course course = new Course("Math", 1001);
        Student student1 = new Student("Lena", 101);
        course.addStudentToCourse(student1);
        Map<Student, Integer> studentGrades = course.getStudentGrades();

        course.addGrade(student1, 3);

        assertNotNull(studentGrades.get(student1));
        assertEquals(3, (int) studentGrades.get(student1));
    }
}