package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)
class FacultyServiceTest {

    private final FacultyService facultyService;

    @MockBean
    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceTest(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    private static final Faculty CORRECT_FACULTY = new Faculty();

    static {
        CORRECT_FACULTY.setId(1L);
        CORRECT_FACULTY.setName("Hogwarts");
        CORRECT_FACULTY.setColor("Red");
    }

    @Test
    void createFacultyCorrect() {
        Faculty expectedResult = new Faculty();
        expectedResult.setId(1L);
        expectedResult.setName("Hogwarts");
        expectedResult.setColor("Red");

        when(facultyRepository.save(CORRECT_FACULTY)).thenReturn(CORRECT_FACULTY);

        Assertions.assertEquals(expectedResult, facultyService.createFaculty(CORRECT_FACULTY));
    }

    @Test
    void getFacultyByIdCorrect() {
        Faculty expectedResult = new Faculty();
        expectedResult.setId(1L);
        expectedResult.setName("Hogwarts");
        expectedResult.setColor("Red");

        when(facultyRepository.findById(CORRECT_FACULTY.getId())).thenReturn(Optional.of(CORRECT_FACULTY));

        Assertions.assertEquals(expectedResult, facultyService.getFacultyById(expectedResult.getId()));
    }

    @Test
    void editFacultyCorrect() {
        Faculty expectedResult = new Faculty();
        expectedResult.setId(1L);
        expectedResult.setName("Hogwarts");
        expectedResult.setColor("Blue");

        when(facultyRepository.save(expectedResult)).thenReturn(expectedResult);

        Assertions.assertEquals(expectedResult, facultyService.editFaculty(expectedResult));
    }

    @Test
    void removeFacultyCorrect() {
        Faculty expectedResult = new Faculty();
        expectedResult.setId(1L);
        expectedResult.setName("Hogwarts");
        expectedResult.setColor("Red");

        when(facultyRepository.findById(expectedResult.getId())).thenReturn(Optional.of(expectedResult));

        Assertions.assertEquals(expectedResult, facultyService.removeFaculty(expectedResult.getId()));
    }

    @Test
    void getAllFacultyCorrect() {
        List<Faculty> expectedList = new ArrayList<>();
        Faculty expectedResult = new Faculty();
        Faculty expectedResultSecond = new Faculty();
        expectedResult.setId(1L);
        expectedResult.setName("Hogwarts");
        expectedResult.setColor("Red");
        expectedResultSecond.setId(2L);
        expectedResultSecond.setName("Puff");
        expectedResultSecond.setColor("Red");
        expectedList.add(expectedResult);
        expectedList.add(expectedResultSecond);

        when(facultyRepository.findAll()).thenReturn(expectedList);

        Assertions.assertEquals(expectedList, facultyService.getAllFaculty());
    }

    @Test
    void getAllFacultyNullResult() {
        when(facultyRepository.findAll()).thenReturn(null);

        Assertions.assertEquals(null, facultyService.getAllFaculty());
    }

    @Test
    void getFacultyByColor() {
        List<Faculty> expectedList = new ArrayList<>();
        Faculty expectedResult = new Faculty();
        Faculty expectedResultSecond = new Faculty();
        expectedResult.setId(1L);
        expectedResult.setName("Hogwarts");
        expectedResult.setColor("Red");
        expectedResultSecond.setId(2L);
        expectedResultSecond.setName("Puff");
        expectedResultSecond.setColor("Red");
        expectedList.add(expectedResult);
        expectedList.add(expectedResultSecond);

        when(facultyRepository.findAllByColor(expectedResult.getColor())).thenReturn(expectedList);

        Assertions.assertEquals(expectedList, facultyService.getFacultyByColor(expectedResult.getColor()));
    }

}