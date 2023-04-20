package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        if (studentService.getStudentById(id) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("age/{age}")
    public Collection<Student> getStudentByAge(@PathVariable Integer age) {
        return studentService.getStudentByAge(age);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Long id, Student student) {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping("{id}")
    public Student removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @DeleteMapping
    public void removeStudent() {
        studentService.clearStudent();
    }

    @GetMapping
    public Collection<Student> getAllFaculty() {
        return studentService.getAllStudent();
    }

}
