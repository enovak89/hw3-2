package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Map<Long, Student> studentMap = new HashMap<>();
    Long count = 0L;

    public Student addStudent(Student student) {
        student.setId(++count);
        studentMap.put(count, student);
        return student;
    }

    public Student getStudentById(Long id) {
        return studentMap.get(id);
    }

    public Student editStudent(Long id, Student student) {
        studentMap.put(id, student);
        return student;
    }

    public Student removeStudent(Long id) {
        Student student = studentMap.get(id);
        studentMap.remove(id);
        return student;
    }

    public Collection<Student> getAllStudent() {
        return studentMap.values();
    }

    public Collection<Student> getStudentByAge(long age) {
        return studentMap.values().stream()
                .filter(x -> x.getAge() == age)
                .collect(Collectors.toList());
    }

    public void clearStudent() {
        studentMap.clear();
        count = 0L;
    }
}
