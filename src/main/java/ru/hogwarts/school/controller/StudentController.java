package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentsOrderedByIdDescLimit;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
//@TestConfiguration
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        if (studentService.getStudentById(id).isEmpty())
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        return ResponseEntity.ok(studentService.getStudentById(id).get());
    }

    @GetMapping("facultyById")
    public ResponseEntity<Faculty> getStudentFacultyById(@RequestParam long id) {
        if (studentService.getStudentById(id).isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(studentService.getStudentFaculty(id));
    }

    @GetMapping("age/{age}")
    public Collection<Student> getStudentByAge(@PathVariable Integer age) {
        return studentService.getStudentByAge(age);
    }

    @GetMapping("age")
    public Collection<Student> getStudentByAgeBetween(@RequestParam Integer min, @RequestParam Integer max) {
        return studentService.getStudentByAgeBetween(min, max);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}")
    public Student removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @DeleteMapping
    public void clearStudent() {
        studentService.clearStudent();
    }

    @GetMapping
    public Collection<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/count")
    public Integer getStudentCount() {
        return studentService.getStudentCount().getCount();
    }

    @GetMapping("/average-age")
    public Double getStudentAverageAge() {
        return studentService.getStudentAge().getAvg();
    }

    @GetMapping("/five-latest")
    public List<StudentsOrderedByIdDescLimit> getStudentsByIdDescLimit() {
        return studentService.getStudentsByIdDescLimit();
    }

}
