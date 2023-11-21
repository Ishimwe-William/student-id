package com.bunsen;


import com.bunsen.model.Student;
import com.bunsen.service.StudentService;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        StudentService studentService = new StudentService();
        String result;

        //Creating Student 1
        Student student = new Student();
        student.setId("23722");
        student.setName("Ishimwe William");
        student.setFaculty("IT");
        result = studentService.createStudent(student);
        System.out.println(result);

        //Creating Student 2
        Student student1 = new Student();
        student1.setId("24722");
        student1.setName("Bunsen Wilhelm");
        student1.setFaculty("Education");
        result = studentService.createStudent(student1);
        System.out.println(result);

        //Creating Student 3
        Student student2 = new Student();
        student2.setId("26726");
        student2.setName("New Student");
        student2.setFaculty("Theology");
        result = studentService.createStudent(student2);
        System.out.println(result);

        //get all students
        printStudents();

        //delete student
        result= studentService.deleteStudent("24722");
        System.out.println(result);
        printStudents();

        // Update student
        Student studentToUpdate = new Student();
        studentToUpdate.setId("23722");
        studentToUpdate.setName("Ishimwe William");
        studentToUpdate.setFaculty("Information Technology");
        result = studentService.updateStudent(studentToUpdate);
        System.out.println(result);
        printStudents();

        // Find student by ID
        String studentIdToFind = "23722";
        Student foundStudent = studentService.findStudentById(studentIdToFind);
        if (foundStudent != null) {
            System.out.println("Found Student by ID: " + foundStudent.getName());
        } else {
            System.out.println("No student found with ID: " + studentIdToFind);
        }
    }
    public static void printStudents(){
        StudentService studentService =new StudentService();
        List<Student> students = studentService.getAllStudents();
        if (!students.isEmpty()) {
            System.out.println("Student List\n" +
                    "------------");
            for (Student stud : students) {
                System.out.printf("%s\t%s\t%s%n", stud.getId(), stud.getName(), stud.getFaculty());
            }
            System.out.println();
        }
    }
}