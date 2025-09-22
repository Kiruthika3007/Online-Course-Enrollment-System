package com.kce.course.service;

import com.kce.course.model.Student;
import java.util.*;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student s) { students.add(s); }
    public Optional<Student> findById(String id) {
        return students.stream().filter(s -> s.getStudentId().equals(id)).findFirst();
    }
    public List<Student> listStudents() { return new ArrayList<>(students); }
}
