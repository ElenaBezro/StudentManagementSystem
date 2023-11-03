import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
    String name;
    int id;
    private List<Student> studentList = new ArrayList<>();
    private Map<Student, Integer> studentGrades = new HashMap<>();

    public int getId() {
        return id;
    }

    public void addStudentToCourse(Student student) {
        studentList.add(student);
    }

    public void updateStudentList(List<Student> newList) {
        studentList = newList;
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

    public boolean findStudent(Student student) {
        return studentList.contains(student);
    }
}
