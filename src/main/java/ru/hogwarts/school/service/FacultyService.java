package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

public class FacultyService {
    private Map<Long, Faculty> facultyMap = new HashMap<>();
    Long count = 1L;

    public Faculty addFaculty(String name, String color) {
        facultyMap.put(count, new Faculty(name, color));
    }

}
