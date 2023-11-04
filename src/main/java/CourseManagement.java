import CustomExceptions.CourseIsNotRegisteredException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CourseManagement {
    List<Course> courseList = new ArrayList<>();

    @MethodWithReflection
    public void methodWithReflection() {
        Course course = new Course();
        for (Method method : course.getClass().getDeclaredMethods()) {
            if (method.getName().equals("methodWithReflection")) {
                System.out.println("Method with reflection");
            }
        }
    }

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
