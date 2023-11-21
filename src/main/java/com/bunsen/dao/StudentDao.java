package com.bunsen.dao;

import com.bunsen.HibernateUtil;
import com.bunsen.model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDao{

    private Session session = null;
    private Transaction transaction = null;

    public String createStudent(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if(findStudentById(student.getId())==null){
                session.save(student);
                transaction.commit();
                return "Student saved successfully!";
            }else {
                return "User with "+student.getId()+" already exist";
            }
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e; // Let the exception propagate
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public String deleteStudent(String id) {
        try {
            Student student = findStudentById(id);
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            if (student != null) {
                session.delete(student);
                transaction.commit();
                return "Student deleted successfully";
            } else {
                return "No student found with ID: " + id;
            }
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting student with ID: " + id, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public String updateStudent(Student student) {
        try {
            if(findStudentById(student.getId())!=null){
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.merge(student);
            transaction.commit();
            return "Student updated successfully";
            }else {
                return "No student found with ID: " + student.getId();
            }
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public List<Student> getAllStudents() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Student student");
            List<Student> students = query.getResultList();
            return students;
        } catch (RuntimeException e) {
            if (session != null && session.isOpen()) {
                session.close();
            }
            throw e; // Rethrow the exception to notify the caller
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Student findStudentById(String id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Student> query = session.createQuery("from Student where id = :stId", Student.class);
            query.setParameter("stId", id);
            return query.uniqueResult();
        } catch (RuntimeException e) {
            if (session != null && session.isOpen()) {
                session.close();
            }
            throw e; // Rethrow the exception to notify the caller
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
