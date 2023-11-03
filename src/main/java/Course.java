import java.util.ArrayList;
import java.util.List;

public class Course {
    String name;
    int id;
    List<Student> studentList = new ArrayList<>();

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
}
