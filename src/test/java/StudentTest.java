import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import courseManagement.Course;
import studentManagement.Student;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class StudentTest {
    @Test
    @DisplayName("Test that course has been added")
    void addCourse() {
        Student student = new Student("Lena", 101);
        Course math = new Course("Math", 1001);

        student.addCourse(math);

        assertNotNull(student.getCourseList());
        assertTrue(student.getCourseList().contains(math));
    }

    @Test
    @DisplayName("Test that courseList has been updated")
    void updateCourseList() {
        Student student = new Student("Lena", 101);
        Course math = new Course("Math", 1001);
        Course german = new Course("German", 1002);
        student.addCourse(math);
        student.addCourse(german);

        Course russian = new Course("Russian", 1003);
        Course algebra = new Course("Algebra", 1004);
        List<Course> newCourseList = Arrays.asList(russian, algebra);

        student.updateCourseList(newCourseList);

        assertNotNull(student.getCourseList());
        assertTrue(student.getCourseList().contains(algebra));
        assertTrue(student.getCourseList().contains(russian));
        assertFalse(student.getCourseList().contains(math));
        assertFalse(student.getCourseList().contains(german));
    }

    @Test
    @DisplayName("Test that course has been deleted")
    void deleteCourse() {
        Student student = new Student("Lena", 101);
        Course math = new Course("Math", 1001);
        student.addCourse(math);

        student.deleteCourse(1001);

        assertFalse(student.getCourseList().contains(math));
    }

    @Test
    @DisplayName("Test get courseList")
    void getCourseList() {
        Student student = new Student("Lena", 101);
        Course math = new Course("Math", 1001);
        Course german = new Course("German", 1002);
        student.addCourse(math);
        student.addCourse(german);

        List<Course> courses = student.getCourseList();

        assertNotNull(courses);
        assertTrue(courses.contains(math));
        assertTrue(courses.contains(german));
    }
}