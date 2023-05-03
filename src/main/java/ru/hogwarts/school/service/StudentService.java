package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.*;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method createStudent");
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        logger.info("Was invoked method getStudentById");
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method editStudent");
        return studentRepository.save(student);
    }

    public Student removeStudent(Long id) {
        logger.info("Was invoked method removeStudent");
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return student;
    }

    public Collection<Student> getAllStudent() {
        logger.info("Was invoked method getAllStudent");
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentByAge(Integer age) {
        logger.info("Was invoked method getStudentByAge");
//        return studentMap.values().stream()
//                .filter(x -> x.getAge() == age)
//                .collect(Collectors.toList());
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getStudentByAgeBetween(Integer min, Integer max) {
        logger.info("Was invoked method getStudentByAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public void clearStudent() {
        logger.info("Was invoked method clearStudent");
        studentRepository.deleteAll();
    }

    public Faculty getStudentFaculty(Long id) {
        logger.info("Was invoked method getStudentFaculty");
        return studentRepository.findById(id).get().getFaculty();
    }

    public StudentsCount getStudentCount() {
        logger.info("Was invoked method getStudentCount");
        return studentRepository.getStudentsCount();
    }

    public StudentsAverageAge getStudentAge() {
        logger.info("Was invoked method getStudentAge");
        return studentRepository.getStudentAge();
    }

    public List<StudentsOrderedByIdDescLimit> getStudentsByIdDescLimit() {
        logger.info("Was invoked method getStudentsByIdDescLimit");
        return studentRepository.getStudentsByIdDescLimit();
    }
}
