package com.example.android.prueba_android;

import java.util.Comparator;

public class Student {
    private int id;
    private String firstName;
    private double cgpa;
    public Student(int id, String firstName, double cgpa) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.cgpa = cgpa;
    }
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }

    public static Comparator<Student> studentComparator = new Comparator<Student>() {

        public int compare(Student s1, Student s2) {
            Double studentCgpa1 = s1.getCgpa();
            Double studentCgpa2 = s2.getCgpa();
            String studentName1 = s1.getFirstName();
            String studentName2 = s2.getFirstName();
            int studentId1 = s1.getId();
            int studentId2 = s2.getId();

            if(studentCgpa1.equals(studentCgpa2)){
                return studentName1.compareTo(studentName2);
            }

            if(studentName1.equals(studentName2)){
                return studentId1-studentId2;
            }

            return studentCgpa2.compareTo(studentCgpa1);
        }};
}
