package studentManagement;

import courseManagement.Course;
import userManagement.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends User implements Comparable<Student> {
    private List<Course> courseList = new ArrayList<>();

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

    public List<Course> getCourseList() {
        return courseList;
    }

    @Override
    public int compareTo(Student o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(this.getName(), student.getName()) && Objects.equals(this.getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName() + this.getId());
    }
}
