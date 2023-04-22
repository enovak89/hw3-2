package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable long id) {
        if (facultyService.getFacultyById(id) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @GetMapping("studentsById")
    public ResponseEntity<Collection<Student>> getStudentsById(@RequestParam long id) {
        if (facultyService.getFacultyById(id) == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(facultyService.getStudentsByFacultyId(id));
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public Faculty removeFaculty(@PathVariable Long id) {
        return facultyService.removeFaculty(id);
    }

    @DeleteMapping
    public void removeFaculty() {
        facultyService.clearFaculty();
    }

    @GetMapping
    public Collection<Faculty> getFacultyByColorOrName(@RequestParam(required = false) String color,
                                                       @RequestParam(required = false) String name) {
        if (color == null && name == null) {
            return facultyService.getAllFaculty();
        }
        return facultyService.getFacultyByColorOrName(color, name);
    }

}
