package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            // 10 Student objects
            Student s1 = new Student(101, "Devaraj", "devaraj@gmail.com", "AI & Data Science");
            Student s2 = new Student(102, "Arun", "arun@gmail.com", "Computer Science");
            Student s3 = new Student(103, "Karthik", "karthik@gmail.com", "Information Technology");
            Student s4 = new Student(104, "Vijay", "vijay@gmail.com", "Electronics");
            Student s5 = new Student(105, "Praveen", "praveen@gmail.com", "Mechanical");
            Student s6 = new Student(106, "Rahul", "rahul@gmail.com", "Civil Engineering");
            Student s7 = new Student(107, "Sanjay", "sanjay@gmail.com", "Cyber Security");
            Student s8 = new Student(108, "Surya", "surya@gmail.com", "Data Science");
            Student s9 = new Student(109, "Ajay", "ajay@gmail.com", "Artificial Intelligence");
            Student s10 = new Student(110, "Manoj", "manoj@gmail.com", "Cloud Computing");

            // Insert 10 students
            session.persist(s1);
            session.persist(s2);
            session.persist(s3);
            session.persist(s4);
            session.persist(s5);
            session.persist(s6);
            session.persist(s7);
            session.persist(s8);
            session.persist(s9);
            session.persist(s10);

            transaction.commit();

            System.out.println("10 students inserted successfully!");

            // UPDATE one student
            Transaction updateTransaction = session.beginTransaction();

            Student existingStudent = session.get(Student.class, 101);

            if (existingStudent != null) {
                existingStudent.setCourse("Data Science");
                updateTransaction.commit();

                System.out.println("Student 101 updated successfully!");
            } else {
                updateTransaction.rollback();
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}