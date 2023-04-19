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
    public Collection<Student> getStudentByAge(@PathVariable long age) {
        return studentService.getStudentByAge(age) ;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public Student editFaculty(@RequestBody Long id, Student student) {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping("{id}")
    public Student removeFaculty(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @GetMapping
    public Collection<Student> getAllFaculty() {
        return studentService.getAllStudent();
    }

}
