import CustomExceptions.StudentIsNotRegisteredException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Student> findStudentByName(String name) throws StudentIsNotRegisteredException {
        List<Student> result = studentList.stream()
                .filter(student -> student.getName().equals(name))
                .toList();

        if (result.isEmpty()) {
            throw new StudentIsNotRegisteredException();
        }

        result.forEach(System.out::println);
        return result;
    }

    public List<Student> sortStudentsByName() {
        return studentList.stream()
                .sorted()
                .peek(System.out::println)
                .toList();
    }

}
