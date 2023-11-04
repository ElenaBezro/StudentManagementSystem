import CustomExceptions.StudentIsNotRegisteredException;

import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    List<Student> studentList = new ArrayList<>();

    public Student findStudentById(int id) throws StudentIsNotRegisteredException {
        boolean isFound = false;
        for (Student student: studentList) {
            if(student.getId() == id) {
                isFound = true;
                System.out.println(student);
                return student;
            }
        }
        if (!isFound) {
            throw new StudentIsNotRegisteredException();
        }
        return null;
    }

    public List<Student> sortStudentsByName() {
        return studentList.stream()
                .sorted()
                .peek(System.out::println)
                .toList();
    }

}
