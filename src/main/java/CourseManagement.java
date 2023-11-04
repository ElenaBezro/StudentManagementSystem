import CustomExceptions.CourseIsNotRegisteredException;
import CustomExceptions.StudentIsNotRegisteredException;

import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    List<Course> courseList = new ArrayList<>();

    public Course findCourseById(int id) throws CourseIsNotRegisteredException {
        boolean isFound = false;
        for (Course course : courseList) {
            if (course.getId() == id) {
                isFound = true;
                System.out.println(course);
                return course;
            }
        }

        if (!isFound) {
            throw new CourseIsNotRegisteredException();
        }

        return null;
    }

    public List<Course> findCourseByName(String name) throws CourseIsNotRegisteredException {
        List<Course> result = courseList.stream()
                .filter(course -> course.getName().equals(name))
                .toList();

        if (result.isEmpty()) {
            throw new CourseIsNotRegisteredException();
        }

        result.forEach(System.out::println);
        return result;
    }

    public List<Course> sortCoursesByName() {
        return courseList.stream()
                .sorted()
                .peek(System.out::println)
                .toList();
    }

}
