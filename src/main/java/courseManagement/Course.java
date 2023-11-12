package courseManagement;

import studentManagement.Student;

import java.util.*;

public class Course implements Comparable<Course>{
    String name;
    int id;
    private List<Student> studentList = new ArrayList<>();
    private final Map<Student, Integer> studentGrades = new HashMap<>();

    public Course(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addStudentToCourse(Student student) {
        studentList.add(student);
    }

    public void updateStudentList(List<Student> newList) {
        studentList = newList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public Map<Student, Integer> getStudentGrades() {
        return studentGrades;
    }

    public void deleteStudentFromCourse(int id) {
        studentList = studentList.stream()
                .filter(student -> student.getId() != id)
                .toList();
    }

    public void displayStudentList() {
        studentList.forEach(System.out::println);
    }

    public void addGrade(Student student, Integer grade) {
        studentGrades.put(student, grade);
    }

    public boolean findStudentOnTheCourse(Student student) {
        return studentList.contains(student);
    }

    @Override
    public String toString() {
        return "Course: name = " + name + ", id = " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public int compareTo(Course o) {
        return name.compareTo(o.getName());
    }
}
