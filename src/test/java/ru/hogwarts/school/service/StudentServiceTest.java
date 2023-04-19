package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    private final StudentService studentService;

    @Autowired
    public StudentServiceTest(StudentService studentService) {
        this.studentService = studentService;
    }

    private static final Student CORRECT_STUDENT = new Student(1L, "Petr", 20);
    private static final Student CORRECT_STUDENT_SECOND = new Student(2L, "Ivan", 20);
    private static final Student CORRECT_STUDENT_THIRD = new Student(3L, "Vasiliy", 25);
    private static final Map<Long, Student> studentMap = new HashMap<>();

    @BeforeEach
    public void clear() {
        studentService.clearStudent();
    }

    @Test
    void addStudentCorrect() {
        Student expectedResult = new Student(1L, "Petr", 20);
        Assertions.assertEquals(expectedResult, studentService.addStudent(CORRECT_STUDENT));
    }

    @Test
    void getStudentByIdCorrect() {
        studentService.addStudent(CORRECT_STUDENT);
        Student expectedResult = new Student(1L, "Petr", 20);
        Assertions.assertEquals(expectedResult, studentService.getStudentById(1L));
    }

    @Test
    void getStudentByIdResultNull() {
        Assertions.assertEquals(null, studentService.getStudentById(1L));
    }

    @Test
    void editStudentCorrect() {
        studentService.addStudent(CORRECT_STUDENT);
        Student expectedResult = new Student(5L, "Petr", 30);
        Assertions.assertEquals(expectedResult, studentService.editStudent(1L, expectedResult));
    }

    @Test
    void removeStudentCorrect() {
        studentService.addStudent(CORRECT_STUDENT);
        Student expectedResult = new Student(1L, "Petr", 20);
        Assertions.assertEquals(expectedResult, studentService.removeStudent(1L));
    }

    @Test
    void removeStudentResultNull() {
        Assertions.assertEquals(null, studentService.removeStudent(1L));
    }

    @Test
    void getAllStudentCorrect() {
        studentService.addStudent(CORRECT_STUDENT);
        studentService.addStudent(CORRECT_STUDENT_SECOND);
        studentService.addStudent(CORRECT_STUDENT_THIRD);
        studentMap.clear();
        studentMap.put(1L, CORRECT_STUDENT);
        studentMap.put(2L, CORRECT_STUDENT_SECOND);
        studentMap.put(3L, CORRECT_STUDENT_THIRD);
        Assertions.assertEquals(studentMap.values().toString(), studentService.getAllStudent().toString());
    }

    @Test
    void getAllStudentNullResult() {
        studentMap.clear();
        Assertions.assertEquals(studentMap.values().toString(), studentService.getAllStudent().toString());
    }

    @Test
    void getStudentByColor() {
        studentService.addStudent(CORRECT_STUDENT);
        studentService.addStudent(CORRECT_STUDENT_SECOND);
        studentService.addStudent(CORRECT_STUDENT_THIRD);
        studentMap.clear();
        studentMap.put(1L, CORRECT_STUDENT);
        studentMap.put(2L, CORRECT_STUDENT_SECOND);
        Assertions.assertEquals(studentMap.values().stream().toList(), studentService.getStudentByAge(20));
    }
}