package com.example.android.prueba_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class JavaSort extends AppCompatActivity {

    private static final String TAG = JavaSort.class.getName();
    private EditText inputStudent1;
    private EditText studentNumber;
    private int totalStudents;
    private ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_sort);
        inputStudent1 = (EditText) findViewById(R.id.student_number_1_tv);
        studentNumber = (EditText) findViewById(R.id.students_total_tv);
        students = new ArrayList<Student>();
        studentNumber.setText("2");
    }


    public void saveStudent(View view) {
        if (!studentNumber.getText().toString().equals("")) {
            totalStudents = Integer.parseInt(studentNumber.getText().toString());
            if (totalStudents >= 2 && totalStudents <= 1000) {
                String tempString = inputStudent1.getText().toString();
                if (tempString.isEmpty()) {
                    Toast.makeText(this, "No text has been entered", Toast.LENGTH_SHORT).show();
                } else {
                    String[] words = tempString.split("\\s+");
                    inputValidation(words);
                }
            } else {
                Toast.makeText(this, R.string.student_number_range, Toast.LENGTH_SHORT).show();
            }
        }

        //String tempString = "2, Chaitanya, 26";


        //Test code
        // Se siguió el ejemplo para probar el algoritmo para organizar los estudiantes, sin embargo
        // el funcionamiento en la vista quedó incompleto.
            /*
            students.add(new Student(33, "Rumpa", 3.68));
            students.add(new Student(85, "Ashis", 3.85));
            students.add(new Student(56, "Samiha", 3.75));
            students.add(new Student(19, "Samara", 3.75));
            students.add(new Student(22, "Fahim", 3.76));
            sortStudents(students);
            */

        for (int i = 0; i < students.size(); i++) {
            Log.e("Students", students.get(i).getFirstName());
        }
    }

    public void sortStudents(View view) {
        Collections.sort(students, Student.studentComparator);
        for (Student student : students) {
            Log.v(TAG, student.getFirstName());
        }
    }


    private Boolean inputValidation(String[] inputString) {
        int id;
        String firstName;
        double cgpa;


        int inputId = Integer.parseInt(inputString[0]);
        String inputName = inputString[1];
        Double inputCgpa = Double.parseDouble(inputString[2]);
        if (inputId < 0 || inputId > 100000) {
            return false;
        } else {
            id = inputId;
        }

        if (inputName.length() < 5 || inputName.length() > 30) {
            return false;
        } else {
            firstName = inputName;
        }

        if (inputCgpa < 0 || inputCgpa > 4.00) {
            return false;
        } else {
            cgpa = inputCgpa;
        }
        students.add(new Student(id, firstName, cgpa));
        inputStudent1.setText("");
        return true;
    }
}
