package com.bunsen.repository;

import com.bunsen.model.Student;

import java.util.List;

public interface StudentRepository {
    String createStudent(Student student);
    String deleteStudent(String id);
    String updateStudent(Student student);
    List<Student> getAllStudents();
    Student findStudentById(String id);
}
