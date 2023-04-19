package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)
class FacultyServiceTest {

    private final FacultyService facultyService;

    @Autowired
    public FacultyServiceTest(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    private static final Faculty CORRECT_FACULTY = new Faculty(1L, "Hogwarts", "Red");
    private static final Faculty CORRECT_FACULTY_SECOND = new Faculty(2L, "Puff", "Red");
    private static final Faculty CORRECT_FACULTY_THIRD = new Faculty(3L, "Sliz", "Green");
    private static final Map<Long, Faculty> facultyMap = new HashMap<>();

    @BeforeEach
    public void clear() {
        facultyService.clearFaculty();
    }

    @Test
    void addFacultyCorrect() {
        Faculty expectedResult = new Faculty(1L, "Hogwarts", "Red");
        Assertions.assertEquals(expectedResult, facultyService.addFaculty(CORRECT_FACULTY));
    }

    @Test
    void getFacultyByIdCorrect() {
        facultyService.addFaculty(CORRECT_FACULTY);
        Faculty expectedResult = new Faculty(1L, "Hogwarts", "Red");
        Assertions.assertEquals(expectedResult, facultyService.getFacultyById(1L));
    }

    @Test
    void getFacultyByIdResultNull() {
        Assertions.assertEquals(null, facultyService.getFacultyById(1L));
    }

    @Test
    void editFacultyCorrect() {
        facultyService.addFaculty(CORRECT_FACULTY);
        Faculty expectedResult = new Faculty(5L, "Hogwarts", "Blue");
        Assertions.assertEquals(expectedResult, facultyService.editFaculty(1L, expectedResult));
    }

    @Test
    void removeFacultyCorrect() {
        facultyService.addFaculty(CORRECT_FACULTY);
        Faculty expectedResult = new Faculty(1L, "Hogwarts", "Red");
        Assertions.assertEquals(expectedResult, facultyService.removeFaculty(1L));
    }

    @Test
    void removeFacultyResultNull() {
        Assertions.assertEquals(null, facultyService.removeFaculty(1L));
    }

    @Test
    void getAllFacultyCorrect() {
        facultyService.addFaculty(CORRECT_FACULTY);
        facultyService.addFaculty(CORRECT_FACULTY_SECOND);
        facultyService.addFaculty(CORRECT_FACULTY_THIRD);
        facultyMap.clear();
        facultyMap.put(1L, CORRECT_FACULTY);
        facultyMap.put(2L, CORRECT_FACULTY_SECOND);
        facultyMap.put(3L, CORRECT_FACULTY_THIRD);
        Assertions.assertEquals(facultyMap.values().toString(), facultyService.getAllFaculty().toString());
    }

    @Test
    void getAllFacultyNullResult() {
        facultyMap.clear();
        Assertions.assertEquals(facultyMap.values().toString(), facultyService.getAllFaculty().toString());
    }

    @Test
    void getFacultyByColor() {
        facultyService.addFaculty(CORRECT_FACULTY);
        facultyService.addFaculty(CORRECT_FACULTY_SECOND);
        facultyService.addFaculty(CORRECT_FACULTY_THIRD);
        facultyMap.clear();
        facultyMap.put(1L, CORRECT_FACULTY);
        facultyMap.put(2L, CORRECT_FACULTY_SECOND);
        Assertions.assertEquals(facultyMap.values().stream().toList(), facultyService.getFacultyByColor("Red"));
    }
}