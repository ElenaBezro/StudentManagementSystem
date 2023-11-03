import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    List<Course> courseList = new ArrayList<>();

    public Student(String name, int id) {
        super(name, id);
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void updateCourseList(List<Course> newList) {
        courseList = newList;
    }

    public void deleteCourse(int id) {
        courseList = courseList.stream()
                .filter(course -> course.getId() != id)
                .toList();
    }
    public void displayCourseList() {
        courseList.forEach(System.out::println);
    }
}
