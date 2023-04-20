package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
//    private Map<Long, Student> studentMap = new HashMap<>();
//    Long count = 0L;

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    public Student addStudent(Student student) {
//        student.setId(++count);
//        studentMap.put(count, student);
//        return student;
//    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Long id, Student student) {
        return studentRepository.save(studentRepository.findById(id).get());
    }

    public Student removeStudent(Long id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return student;
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentByAge(Integer age) {
//        return studentMap.values().stream()
//                .filter(x -> x.getAge() == age)
//                .collect(Collectors.toList());
    return studentRepository.findAllByAge(age);
    }

    public void clearStudent() {
        studentRepository.deleteAll();
    }
}
