package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Map<Long, Faculty> facultyMap = new HashMap<>();
    Long count = 0L;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++count);
        facultyMap.put(count, faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long id) {
        return facultyMap.get(id);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        facultyMap.put(id, faculty);
        return faculty;
    }

    public Faculty removeFaculty(Long id) {
        Faculty faculty = facultyMap.get(id);
        facultyMap.remove(id);
        return faculty;
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyMap.values();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyMap.values().stream()
                .filter(x -> x.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public void clearFaculty() {
        facultyMap.clear();
        count = 0L;
    }
}
